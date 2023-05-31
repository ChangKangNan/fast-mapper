package cn.ft.ckn.fastmapper.aspect;

import cn.ft.ckn.fastmapper.annotation.GlobalTransactionalLocal;
import cn.ft.ckn.fastmapper.bean.SearchParam;
import cn.ft.ckn.fastmapper.expander.ExpanderOccasion;
import cn.ft.ckn.fastmapper.expander.MapperExpander;
import cn.ft.ckn.fastmapper.transaction.TransactionManager;
import cn.ft.ckn.fastmapper.transaction.TransactionSwitch;
import cn.hutool.core.collection.ListUtil;
import io.netty.util.concurrent.FastThreadLocal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Stack;

/**
 * @author ckn
 */
//public class TransactionalActuatorAspect implements MapperExpander {
//    Logger log = LoggerFactory.getLogger(TransactionalActuatorAspect.class);
//    private static FastThreadLocal<Boolean> isReturn = new FastThreadLocal<>();
//
//    @Override
//    public boolean before(SearchParam param, Method method) {
//        GlobalTransactionalLocal annotation = method.getAnnotation(GlobalTransactionalLocal.class);
//        if (annotation != null) {
//            log.info("--------------开启全局事务---------------");
//            TransactionSwitch.GLOBAL_TRANSACTION_SWITCH_STATUS.set(true);
//            TransactionSynchronizationManager.initSynchronization();
//        }
//        return false;
//    }
//
//    @Override
//    public void after(SearchParam param, Method method) {
//        Boolean aBoolean = isReturn.get();
//        if(aBoolean!=null && (aBoolean)){
//            return;
//        }
//        GlobalTransactionalLocal annotation = method.getAnnotation(GlobalTransactionalLocal.class);
//        if (annotation == null) {
//            return;
//        }
//        Stack<Connection> stack = TransactionManager.getCurrentGlobalTransactionStack();
//        for (Connection connection : stack) {
//            try {
//                connection.commit();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        try {
//            TransactionManager.clearThreadLocalTransaction();
//            TransactionSynchronizationManager.clear();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        isReturn.set(true);
//        log.info("--------------全局事务执行完毕---------------");
//    }
//
//    @Override
//    public void afterException(SearchParam param, Method method) {
//        GlobalTransactionalLocal annotation = method.getAnnotation(GlobalTransactionalLocal.class);
//        if (annotation == null) {
//            return;
//        }
//        Stack<Connection> stack = TransactionManager.getCurrentGlobalTransactionStack();
//        for (Connection connection : stack) {
//            try {
//                connection.rollback();
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//            }
//        }
//        try {
//            TransactionManager.clearThreadLocalTransaction();
//            TransactionSynchronizationManager.clear();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        isReturn.set(true);
//        log.info("--------------全局事务遇错回滚完毕---------------");
//    }
//
//    @Override
//    public List<ExpanderOccasion> occasion() {
//        return ListUtil.of(ExpanderOccasion.INSERT, ExpanderOccasion.DELETE, ExpanderOccasion.UPDATE, ExpanderOccasion.SELECT);
//    }
//}
