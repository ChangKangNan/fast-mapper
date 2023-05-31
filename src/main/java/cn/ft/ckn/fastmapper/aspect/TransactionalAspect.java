package cn.ft.ckn.fastmapper.aspect;

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
