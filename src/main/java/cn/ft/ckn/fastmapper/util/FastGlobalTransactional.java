package cn.ft.ckn.fastmapper.util;

import cn.ft.ckn.fastmapper.inter.RunGlobal;
import cn.hutool.extra.spring.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @author ckn
 * @date 2022/8/18
 */
@Component
@Slf4j
public class FastGlobalTransactional {
    public static void run(RunGlobal runGlobal) {
        TransactionTemplate transactionTemplate = SpringUtil.getBean(TransactionTemplate.class);
        try {
            transactionTemplate.execute(new TransactionCallbackWithoutResult() {
                @Override
                protected void doInTransactionWithoutResult(TransactionStatus status) {
                    runGlobal.run();
                }
            });
        }catch (Exception e){
            log.info("报错了,事务开始回滚!");
        }
    }
}
