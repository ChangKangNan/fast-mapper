//package com.example.testlocal.aop;
//
//import cn.ft.ckn.fastmapper.inter.GlobalTransactionalLocal;
//import cn.ft.ckn.fastmapper.util.TransactionManager;
//import cn.ft.ckn.fastmapper.util.TransactionSwitch;
//import io.netty.util.concurrent.FastThreadLocal;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.*;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.support.TransactionSynchronizationManager;
//
//import java.lang.reflect.Method;
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.util.Stack;
//
///**
// * @author ckn
// * @date 2022/11/2
// */
//@Aspect
//@Component
//@Slf4j
//public class TransactionalAspect {
//    private JoinPoint joinPoint;
//    private static FastThreadLocal<Boolean> isReturn = new FastThreadLocal<>();
//
//    @Pointcut("@annotation(cn.ft.ckn.fastmapper.inter.GlobalTransactionalLocal)")
//    public void transactionalAction() {
//    }
//
//    @Before("transactionalAction()")
//    public void before(JoinPoint joinPoint) throws Throwable {
//        this.joinPoint = joinPoint;
//        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//        Method method = signature.getMethod();
//        GlobalTransactionalLocal annotation = method.getAnnotation(GlobalTransactionalLocal.class);
//        if (annotation != null) {
//            log.info("--------------开启全局事务---------------");
//            TransactionSwitch.GLOBAL_TRANSACTION_SWITCH_STATUS.set(true);
//            TransactionSynchronizationManager.initSynchronization();
//        }
//    }
//
//
//    @After("transactionalAction()")
//    public void after() throws Throwable {
//        Boolean aBoolean = isReturn.get();
//        if(aBoolean!=null && (aBoolean)){
//            return;
//        }
//        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//        Method method = signature.getMethod();
//        GlobalTransactionalLocal annotation = method.getAnnotation(GlobalTransactionalLocal.class);
//        if (annotation != null) {
//            Stack<Connection> stack = TransactionManager.getCurrentGlobalTransactionStack();
//            for (Connection connection : stack) {
//                try {
//                    connection.commit();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//            try {
//                TransactionManager.clearThreadLocalTransaction();
//                TransactionSynchronizationManager.clear();
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//            }
//            log.info("--------------全局事务执行完毕---------------");
//        }
//        isReturn.set(true);
//    }
//
//    /**
//     * 切点抛出异常后
//     *
//     * @throws Throwable
//     */
//    @AfterThrowing("transactionalAction()")
//    public void afterThrowing() throws Throwable {
//        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//        Method method = signature.getMethod();
//        GlobalTransactionalLocal annotation = method.getAnnotation(GlobalTransactionalLocal.class);
//        if (annotation != null) {
//            Stack<Connection> stack = TransactionManager.getCurrentGlobalTransactionStack();
//            for (Connection connection : stack) {
//                try {
//                    connection.rollback();
//                } catch (SQLException ex) {
//                    ex.printStackTrace();
//                }
//            }
//            try {
//                TransactionManager.clearThreadLocalTransaction();
//                TransactionSynchronizationManager.clear();
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//            }
//            log.info("--------------全局事务遇错回滚完毕---------------");
//        }
//        isReturn.set(true);
//    }
//}
