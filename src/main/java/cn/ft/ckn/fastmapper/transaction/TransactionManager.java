package cn.ft.ckn.fastmapper.transaction;

import io.netty.util.concurrent.FastThreadLocal;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author ckn
 * @date 2022/11/3
 */
public class TransactionManager {
    public static FastThreadLocal<Stack<Connection>> transactionTreadLocal = new FastThreadLocal<>();
    public static FastThreadLocal<Map<String, Connection>> transactionMapTreadLocal = new FastThreadLocal<>();

    /**
     * 获取全局事务栈
     *
     * @return
     */
    public static Stack<Connection> getCurrentGlobalTransactionStack() {
        return transactionTreadLocal.get() == null ? new Stack<>() : transactionTreadLocal.get();
    }

    /**
     * 全局事务连接关闭(druid自动回收连接)
     *
     * @throws SQLException
     */
    public static void clearThreadLocalTransaction() throws SQLException {
        transactionTreadLocal.remove();
        transactionMapTreadLocal.remove();
        TransactionSwitch.GLOBAL_TRANSACTION_SWITCH_STATUS.remove();
        TransactionSwitch.GLOBAL_TRANSACTION_ISOLATION.remove();
    }

    public static void initTransaction(DataSource dataSource) {
        try {
            Connection connection = DataSourceUtils.getConnection(dataSource);
            String key = getKey(dataSource);
            boolean transactionFlag = TransactionSwitch.GLOBAL_TRANSACTION_SWITCH_STATUS.get() != null && TransactionSwitch.GLOBAL_TRANSACTION_SWITCH_STATUS.get();
            if (!transactionFlag) {
                return;
            }
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(TransactionSwitch.GLOBAL_TRANSACTION_ISOLATION.get());
            Map<String, Connection> connectionMap = transactionMapTreadLocal.get();
            Stack<Connection> connections = transactionTreadLocal.get();
            if (connectionMap == null) {
                Map<String, Connection> map = new HashMap<>();
                map.put(key, connection);
                transactionMapTreadLocal.set(map);
                if (connections == null) {
                    Stack<Connection> stack = new Stack<>();
                    stack.push(connection);
                    transactionTreadLocal.set(stack);
                }
            } else {
                Connection conn = connectionMap.get(key);
                if (conn == null) {
                    connectionMap.put(key, connection);
                    connections.add(connection);
                } else {
                    connectionMap.put(key, conn);
                    connections.add(conn);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getKey(DataSource dataSource) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<? extends DataSource> sourceClass = dataSource.getClass();
        Method getPassword = sourceClass.getMethod("getPassword");
        Method getUsername = sourceClass.getMethod("getUsername");
        Method getUrl = sourceClass.getMethod("getUrl");
        Method getDriverClassName = sourceClass.getMethod("getDriverClassName");
        String pwd = (String) getPassword.invoke(dataSource);
        String username = (String) getUsername.invoke(dataSource);
        String url = (String) getUrl.invoke(dataSource);
        String driverClassName = (String) getDriverClassName.invoke(dataSource);
        return "key_" + driverClassName + "_" + url + "_" + username + "_" + pwd;
    }
}
