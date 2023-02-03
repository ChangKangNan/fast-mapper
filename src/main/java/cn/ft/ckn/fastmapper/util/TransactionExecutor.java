package cn.ft.ckn.fastmapper.util;

import cn.hutool.core.collection.CollUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 多线程事务处理器
 *
 * @author ckn
 * @date 2023/2/3
 */
@Slf4j
public class TransactionExecutor {
    private TransactionExecutor() {
    }

    public static TransactionExecutor build() {
        return new TransactionExecutor();
    }

    /**
     * 执行多线程事务
     *
     * @param tasks
     */
    public void execute(List<Runnable> tasks) {
        if (CollUtil.isEmpty(tasks)) {
            return;
        }
        log.info("多线程全局事务开始执行.................");
        TransactionStatus transactionStatus = TransactionStatus.build();
        int size = tasks.size();
        ExecutorService taskThreadPool = Executors.newFixedThreadPool(size);
        CountDownLatch threadLatch = new CountDownLatch(size); // 用于计算子线程提交数量
        CountDownLatch mainLatch = new CountDownLatch(1); // 用于判断主线程是否提交
        for (Runnable task : tasks) {
            taskThreadPool.execute(() -> {
                //设置开始事务开始标识
                TransactionSwitch.GLOBAL_TRANSACTION_SWITCH_STATUS.set(true);
                TransactionSynchronizationManager.initSynchronization();
                TransactionSwitch.GLOBAL_TRANSACTION_ISOLATION.set(Connection.TRANSACTION_REPEATABLE_READ);
                //处理业务
                try {
                    task.run();
                } catch (Exception e) {
                    e.printStackTrace();
                    transactionStatus.setIsError();
                } finally {
                    threadLatch.countDown();
                }
                try {
                    mainLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 判断是否有错误，如有错误 就回滚事务
                Stack<Connection> stack = TransactionManager.getCurrentGlobalTransactionStack();
                if (transactionStatus.getIsError()) {
                    for (Connection connection : stack) {
                        try {
                            connection.rollback();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                } else {
                    for (Connection connection : stack) {
                        try {
                            connection.commit();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
                try {
                    TransactionManager.clearThreadLocalTransaction();
                    TransactionSynchronizationManager.clear();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            });
        }
        // 倒计时锁设置超时时间 30s
        boolean await = false;
        try {
            await = threadLatch.await(30, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (!await) { // 等待超时，事务回滚
            transactionStatus.setIsError();
        }
        mainLatch.countDown();
        taskThreadPool.shutdown(); //关闭线程池
        if (transactionStatus.getIsError()) {
            log.info("多线程全局事务回滚完毕.................");
        } else {
            log.info("多线程全局事务执行完毕.................");
        }
    }
}
