package cn.ft.ckn.fastmapper.component;

import cn.ft.ckn.fastmapper.util.JDBCUtils;
import cn.ft.ckn.fastmapper.util.TransactionSwitch;
import cn.hutool.aop.aspects.SimpleAspect;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Stack;

/**
 * @author ckn
 * @date 2022/11/1
 * 全局事务处理类
 */
@Slf4j
@Component
public class GlobalTransactionalAspect extends SimpleAspect {
    @Override
    public boolean before(Object target, Method method, Object[] args) {
        GlobalTransactionalLocal annotation = method.getAnnotation(GlobalTransactionalLocal.class);
        if (annotation != null) {
            log.info("--------------开启全局事务---------------");
            TransactionSwitch.GLOBAL_TRANSACTION_SWITCH_STATUS.set(true);
        }
        return super.before(target, method, args);
    }

    @Override
    public boolean after(Object target, Method method, Object[] args, Object returnVal) {
        GlobalTransactionalLocal annotation = method.getAnnotation(GlobalTransactionalLocal.class);
        if (annotation != null) {
            Stack<Connection> stack = JDBCUtils.getCurrentGlobalTransactionStack();
            for (Connection connection : stack) {
                try {
                    connection.commit();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            try {
                JDBCUtils.clearThreadLocalTransaction();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            log.info("--------------全局事务执行完毕---------------");
        }
        return super.after(target, method, args, returnVal);
    }

    @Override
    public boolean afterException(Object target, Method method, Object[] args, Throwable e) {
        GlobalTransactionalLocal annotation = method.getAnnotation(GlobalTransactionalLocal.class);
        if (annotation != null) {
            Stack<Connection> stack = JDBCUtils.getCurrentGlobalTransactionStack();
            for (Connection connection : stack) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            try {
                JDBCUtils.clearThreadLocalTransaction();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            log.info("--------------全局事务遇错回滚完毕---------------");
        }
        return super.afterException(target, method, args, e);
    }
}
