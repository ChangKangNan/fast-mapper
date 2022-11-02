//package cn.ft.ckn.fastmapper.util;
//
//import cn.ft.ckn.fastmapper.component.GlobalTransactionalLocal;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.*;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.stereotype.Component;
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
//
//    @Pointcut("@annotation(cn.ft.ckn.fastmapper.component.GlobalTransactionalLocal)")
//    public void roll() {
//    }
//
//    @Before("roll()")
//    public void before(JoinPoint joinPoint) throws Throwable {
//        this.joinPoint = joinPoint;
//        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//        Method method = signature.getMethod();
//        GlobalTransactionalLocal annotation = method.getAnnotation(GlobalTransactionalLocal.class);
//        if (annotation != null) {
//            log.info("--------------开启全局事务---------------");
//            TransactionSwitch.GLOBAL_TRANSACTION_SWITCH_STATUS.set(true);
//        }
//    }
//
//    @After("roll()")
//    public void after() throws Throwable {
//        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//        Method method = signature.getMethod();
//        GlobalTransactionalLocal annotation = method.getAnnotation(GlobalTransactionalLocal.class);
//        if (annotation != null) {
//            Stack<Connection> stack = JDBCUtils.getCurrentGlobalTransactionStack();
//            for (Connection connection : stack) {
//                try {
//                    connection.commit();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//            try {
//                JDBCUtils.clearThreadLocalTransaction();
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//            }
//            log.info("--------------全局事务执行完毕---------------");
//        }
//    }
//
//    /**
//     * 切点抛出异常后
//     *
//     * @throws Throwable
//     */
//    @AfterThrowing("roll()")
//    public void afterThrowing() throws Throwable {
//        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//        Method method = signature.getMethod();
//        GlobalTransactionalLocal annotation = method.getAnnotation(GlobalTransactionalLocal.class);
//        if (annotation != null) {
//            Stack<Connection> stack = JDBCUtils.getCurrentGlobalTransactionStack();
//            for (Connection connection : stack) {
//                try {
//                    connection.rollback();
//                } catch (SQLException ex) {
//                    ex.printStackTrace();
//                }
//            }
//            try {
//                JDBCUtils.clearThreadLocalTransaction();
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//            }
//            log.info("--------------全局事务遇错回滚完毕---------------");
//        }
//    }
//}
