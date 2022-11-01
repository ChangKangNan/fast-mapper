package cn.ft.ckn.fastmapper.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import io.netty.util.concurrent.FastThreadLocal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterUtils;
import org.springframework.jdbc.core.namedparam.ParsedSql;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.*;
import java.util.Date;
import java.util.*;

import static java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE;

@Slf4j
public class JDBCUtils {
    private static Map<String, Connection> dataSourceToConnection = new HashMap<>();
    private static FastThreadLocal<String> currentDataSourceKey = new FastThreadLocal<>();
    private static FastThreadLocal<DataSource> dataSourceFastThreadLocal = new FastThreadLocal<>();
    private static FastThreadLocal<Stack<Connection>> transactionTreadLocal = new FastThreadLocal<>();
    private static FastThreadLocal<Map<String, Connection>> transactionMapTreadLocal = new FastThreadLocal<>();

    public JDBCUtils(DataSource dataSource) throws Exception {
        Class<? extends DataSource> sourceClass = dataSource.getClass();
        Method getPassword = sourceClass.getMethod("getPassword");
        Method getUsername = sourceClass.getMethod("getUsername");
        Method getUrl = sourceClass.getMethod("getUrl");
        Method getDriverClassName = sourceClass.getMethod("getDriverClassName");
        String pwd = (String) getPassword.invoke(dataSource);
        String username = (String) getUsername.invoke(dataSource);
        String url = (String) getUrl.invoke(dataSource);
        String driverClassName = (String) getDriverClassName.invoke(dataSource);
        String key = "key_" + driverClassName + "_" + url + "_" + username + "_" + pwd;
        currentDataSourceKey.set(key);
        Connection connection = dataSourceToConnection.get(key);
        if (connection == null) {
            dataSourceToConnection.putIfAbsent(key, dataSource.getConnection());
        }
        dataSourceFastThreadLocal.set(dataSource);
    }

    /**
     * 获取全局事务栈
     *
     * @return
     */
    public static Stack<Connection> getCurrentGlobalTransactionStack() {
        return transactionTreadLocal.get() == null ? new Stack<>() : transactionTreadLocal.get();
    }

    /**
     * 全局事务连接关闭
     *
     * @throws SQLException
     */
    public static void clearThreadLocalTransaction() throws SQLException {
        Stack<Connection> connections = transactionTreadLocal.get();
        for (Connection conn : connections) {
            conn.close();
        }
        transactionTreadLocal.remove();
        transactionMapTreadLocal.remove();
        TransactionSwitch.GLOBAL_TRANSACTION_SWITCH_STATUS.remove();
    }

    /**
     * 以map形式更新数据
     *
     * @param sql
     * @param paramsMap
     * @return
     * @throws DataAccessException
     * @throws SQLException
     * @throws IllegalAccessException
     */
    public int update(String sql, Map<String, Object> paramsMap) throws DataAccessException, SQLException, IllegalAccessException {
        Connection connection = getConnection();
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(paramsMap);
        PreparedStatementCreator statementCreator = this.getPreparedStatementCreator(sql, sqlParameterSource);
        String final_sql = getSql(statementCreator);
        log.info(final_sql);
        List params = searchParams(statementCreator);
        PreparedStatement prepareStatement = connection.prepareStatement(final_sql, TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
        if (CollUtil.isNotEmpty(params)) {
            int i = 1;
            for (Object param : params) {
                prepareStatement.setObject(i, param);
                i++;
            }
        }
        return prepareStatement.executeUpdate();
    }

    /**
     * 以map形式插入
     *
     * @param sql
     * @param paramsMap
     * @return
     * @throws DataAccessException
     * @throws SQLException
     * @throws IllegalAccessException
     */
    public int insert(String sql, Map<String, Object> paramsMap) throws DataAccessException, SQLException, IllegalAccessException {
        Connection connection = getConnection();
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(paramsMap);
        PreparedStatementCreator statementCreator = this.getPreparedStatementCreator(sql, sqlParameterSource);
        String final_sql = getSql(statementCreator);
        log.info(final_sql);
        List params = searchParams(statementCreator);
        PreparedStatement prepareStatement = connection.prepareStatement(final_sql,TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
        if (CollUtil.isNotEmpty(params)) {
            int i = 1;
            for (Object param : params) {
                prepareStatement.setObject(i, param);
                i++;
            }
        }
        return prepareStatement.executeUpdate();
    }

    /**
     * 根据实体类进行插入
     *
     * @param sql
     * @param sqlParameterSource
     * @param generatedKeyHolder
     * @return
     * @throws DataAccessException
     * @throws SQLException
     * @throws IllegalAccessException
     */
    public int insert(String sql, SqlParameterSource sqlParameterSource, KeyHolder generatedKeyHolder) throws DataAccessException, SQLException, IllegalAccessException {
        Connection connection = getConnection();
        PreparedStatementCreator statementCreator = this.getPreparedStatementCreator(sql, sqlParameterSource);
        String final_sql = getSql(statementCreator);
        log.info(final_sql);
        List params = searchParams(statementCreator);
        PreparedStatement prepareStatement = connection.prepareStatement(final_sql, Statement.RETURN_GENERATED_KEYS);
        if (CollUtil.isNotEmpty(params)) {
            int i = 1;
            for (Object param : params) {
                prepareStatement.setObject(i, param);
                i++;
            }
        }
        int rows = prepareStatement.executeUpdate();
        ResultSet keys = prepareStatement.getGeneratedKeys();
        List<Map<String, Object>> generatedKeys = generatedKeyHolder.getKeyList();
        generatedKeys.clear();
        while (keys.next()) {
            Object generated_keys = keys.getObject("GENERATED_KEY");
            generatedKeys.add(new HashMap<String, Object>() {{
                put("GENERATED_KEY", generated_keys);
            }});
        }
        closeStatement(prepareStatement);
        return rows;
    }

    /**
     * 批量更新
     *
     * @param sql
     * @param sqlParameterSources
     * @throws DataAccessException
     * @throws SQLException
     * @throws IllegalAccessException
     */
    public void updateBatch(String sql, SqlParameterSource[] sqlParameterSources) throws DataAccessException, SQLException, IllegalAccessException {
        Connection connection = getConnection();
        PreparedStatementCreator statementCreator = getPreparedStatementCreator(sql, sqlParameterSources[0]);
        String final_sql = getSql(statementCreator);
        PreparedStatement prepareStatement = connection.prepareStatement(final_sql,TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
        for (int j = 0; j < sqlParameterSources.length; j++) {
            PreparedStatementCreator preparedStatementCreator = getPreparedStatementCreator(sql, sqlParameterSources[j]);
            List params = searchParams(preparedStatementCreator);
            if (CollUtil.isNotEmpty(params)) {
                int i = 1;
                for (Object param : params) {
                    prepareStatement.setObject(i, param);
                    i++;
                }
            }
            prepareStatement.addBatch();
        }
        prepareStatement.executeBatch();
        closeStatement(prepareStatement);
    }

    /**
     * 获取连接
     *
     * @return
     * @throws SQLException
     */
    protected Connection getConnection() throws SQLException {
        Connection connection = null;
        boolean transactionFlag = TransactionSwitch.GLOBAL_TRANSACTION_SWITCH_STATUS.get() != null && TransactionSwitch.GLOBAL_TRANSACTION_SWITCH_STATUS.get();
        if (transactionFlag) {
            Map<String, Connection> stringConnectionMap = transactionMapTreadLocal.get();
            if (stringConnectionMap == null) {
                Map<String, Connection> news = new HashMap<>();
                Connection connectionCopy = dataSourceFastThreadLocal.get().getConnection();
                connectionCopy.setAutoCommit(false);
                connection = connectionCopy;
                news.put(currentDataSourceKey.get(), connectionCopy);
                transactionMapTreadLocal.set(news);
                Stack<Connection> connectionStack = new Stack<>();
                connectionStack.push(connection);
                transactionTreadLocal.set(connectionStack);
            } else {
                Connection connectionTrans = stringConnectionMap.get(currentDataSourceKey.get());
                if (connectionTrans == null) {
                    Connection connectionCopy = dataSourceFastThreadLocal.get().getConnection();
                    connectionCopy.setAutoCommit(false);
                    connection = connectionCopy;
                    stringConnectionMap.put(currentDataSourceKey.get(), connectionCopy);
                    transactionMapTreadLocal.set(stringConnectionMap);
                    Stack<Connection> connectionStack = transactionTreadLocal.get();
                    connectionStack.push(connection);
                    transactionTreadLocal.set(connectionStack);
                } else {
                    connection = connectionTrans;
                }
            }
        } else {
            connection = dataSourceToConnection.get(currentDataSourceKey.get());
        }
        return connection;
    }

    /**
     * 获取一个结果
     *
     * @param sql
     * @param paramsMap
     * @param type
     * @param <T>
     * @return
     * @throws DataAccessException
     * @throws SQLException
     * @throws NoSuchFieldException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     */
    public <T> T queryForObject(String sql, Map<String, Object> paramsMap, Class<T> type) throws DataAccessException, SQLException, IllegalAccessException {
        Connection connection = dataSourceToConnection.get(currentDataSourceKey.get());
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(paramsMap);
        PreparedStatementCreator statementCreator = this.getPreparedStatementCreator(sql, sqlParameterSource);
        String final_sql = getSql(statementCreator);
        log.info(final_sql);
        List params = searchParams(statementCreator);
        PreparedStatement prepareStatement = connection.prepareStatement(final_sql, TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        if (CollUtil.isNotEmpty(params)) {
            int i = 1;
            for (Object param : params) {
                prepareStatement.setObject(i, param);
                i++;
            }
        }
        ResultSet resultSet = prepareStatement.executeQuery();
        resultSet.first();
        int count = 0;
        while (resultSet.next()) {
            count++;
        }
        if (count > 1) {
            log.info("acquire one but return many!");
            return null;
        }
        resultSet.first();
        BeanPropertyRowMapper<T> propertyRowMapper = new BeanPropertyRowMapper<>(type);
        T t = propertyRowMapper.mapRow(resultSet, 1);
        closeStatement(prepareStatement);
        return t;
    }

    /**
     * 获取单一属性值
     *
     * @param sql
     * @param paramsMap
     * @param type
     * @param <T>
     * @return
     * @throws DataAccessException
     * @throws SQLException
     * @throws NoSuchFieldException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     */
    public <T> T queryForUniqueProperty(String sql, Map<String, Object> paramsMap, Class<T> type) throws DataAccessException, SQLException, IllegalAccessException {
        Connection connection = dataSourceToConnection.get(currentDataSourceKey.get());
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(paramsMap);
        PreparedStatementCreator statementCreator = this.getPreparedStatementCreator(sql, sqlParameterSource);
        String final_sql = getSql(statementCreator);
        log.info(final_sql);
        List params = searchParams(statementCreator);
        PreparedStatement prepareStatement = connection.prepareStatement(final_sql, TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        if (CollUtil.isNotEmpty(params)) {
            int i = 1;
            for (Object param : params) {
                prepareStatement.setObject(i, param);
                i++;
            }
        }
        ResultSet resultSet = prepareStatement.executeQuery();
        resultSet.first();
        int count = 0;
        while (resultSet.next()) {
            count++;
        }
        if (count > 1) {
            log.info("acquire one but return many!");
            return null;
        }
        resultSet.first();
        Object object = null;
        if (type == Long.class) {
            object = resultSet.getLong(1);
        } else if (type == String.class) {
            object = resultSet.getString(1);
        } else if (type == Boolean.class) {
            object = resultSet.getBoolean(1);
        } else if (type == Byte.class) {
            object = resultSet.getByte(1);
        } else if (type == BigDecimal.class) {
            object = resultSet.getBigDecimal(1);
        } else if (type == Integer.class) {
            object = resultSet.getInt(1);
        } else if (type == Date.class) {
            object = resultSet.getDate(1);
        }
        closeStatement(prepareStatement);
        return (T) object;
    }


    /**
     * 获取List
     *
     * @param sql
     * @param paramsMap
     * @param type
     * @param <T>
     * @return
     * @throws DataAccessException
     * @throws SQLException
     * @throws NoSuchFieldException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     */
    public <T> List<T> queryForList(String sql, Map<String, Object> paramsMap, Class<T> type) throws DataAccessException, SQLException, IllegalAccessException {
        Connection connection = dataSourceToConnection.get(currentDataSourceKey.get());
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(paramsMap);
        PreparedStatementCreator statementCreator = this.getPreparedStatementCreator(sql, sqlParameterSource);
        String final_sql = getSql(statementCreator);
        log.info(final_sql);
        List params = searchParams(statementCreator);
        PreparedStatement prepareStatement = connection.prepareStatement(final_sql, TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        if (CollUtil.isNotEmpty(params)) {
            int i = 1;
            for (Object param : params) {
                prepareStatement.setObject(i, param);
                i++;
            }
        }
        ResultSet resultSet = prepareStatement.executeQuery();
        resultSet.first();
        int count = 0;
        while (resultSet.next()) {
            count++;
        }
        if (count < 1) {
            return new ArrayList<>();
        }
        resultSet.first();
        BeanPropertyRowMapper<T> propertyRowMapper = new BeanPropertyRowMapper<>(type);
        List<T> returnList = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            T t = propertyRowMapper.mapRow(resultSet, i);
            returnList.add(t);
            if (i != count) {
                resultSet.next();
            }
        }
        closeStatement(prepareStatement);
        return returnList;
    }


    /**
     * 获取Map
     *
     * @return
     * @throws IllegalAccessException
     */
    public List<Map<String, Object>> queryForMap(String sql, Map<String, Object> paramsMap) throws DataAccessException, SQLException, IllegalAccessException {
        Connection connection = dataSourceToConnection.get(currentDataSourceKey.get());
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(paramsMap);
        PreparedStatementCreator statementCreator = this.getPreparedStatementCreator(sql, sqlParameterSource);
        String final_sql = getSql(statementCreator);
        log.info(final_sql);
        List params = searchParams(statementCreator);
        PreparedStatement prepareStatement = connection.prepareStatement(final_sql, TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        if (CollUtil.isNotEmpty(params)) {
            int i = 1;
            for (Object param : params) {
                prepareStatement.setObject(i, param);
                i++;
            }
        }
        ResultSet resultSet = prepareStatement.executeQuery();
        resultSet.first();
        int count = 0;
        while (resultSet.next()) {
            count++;
        }
        if (count < 1) {
            return new ArrayList<>();
        }
        resultSet.first();
        ResultSetMetaData metaData = resultSet.getMetaData();

        int columnCount = metaData.getColumnCount();
        List<Map<String, Object>> returnList = new ArrayList<>();
        while (resultSet.next()) {
            Map<String, Object> stringObjectMap = new HashMap<>();
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i);
                String columnLabel = metaData.getColumnLabel(i);
                String columnTypeName = metaData.getColumnTypeName(i);
                String fieldType = getFieldType(columnTypeName);
                switch (fieldType) {
                    case "String":
                        stringObjectMap.put(columnName, resultSet.getString(columnLabel));
                        break;
                    case "byte[]":
                        stringObjectMap.put(columnName, resultSet.getByte(columnLabel));
                        break;
                    case "Date":
                        stringObjectMap.put(columnName, resultSet.getDate(columnLabel));
                        break;
                    case "Boolean":
                        stringObjectMap.put(columnName, resultSet.getBoolean(columnLabel));
                        break;
                    case "Integer":
                        stringObjectMap.put(columnName, resultSet.getInt(columnLabel));
                        break;
                    case "Long":
                        stringObjectMap.put(columnName, resultSet.getLong(columnLabel));
                        break;
                    case "Float":
                        stringObjectMap.put(columnName, resultSet.getFloat(columnLabel));
                        break;
                    case "Double":
                        stringObjectMap.put(columnName, resultSet.getDouble(columnLabel));
                        break;
                    case "BigDecimal":
                        stringObjectMap.put(columnName, resultSet.getBigDecimal(columnLabel));
                        break;
                }
            }
            returnList.add(stringObjectMap);
        }
        closeStatement(prepareStatement);
        return returnList;
    }

    public String getFieldType(String columnType) {
        columnType = columnType.toLowerCase();
        if (StrUtil.equalsAny(columnType, "varchar", "nvarchar", "char", "text", "mediumtext")) {
            return "String";
        } else if (StrUtil.equalsAny(columnType, "tinyblob", "blob", "mediumblob", "longblob")) {
            return "byte[]";
        } else if (StrUtil.equalsAny(columnType, "datetime", "date", "timestamp", "time", "year")) {
            return "Date";
        } else if (StrUtil.equalsAny(columnType, "bit", "tinyint", "tinyint unsigned")) {
            return "Boolean";
        } else if (StrUtil.equalsAny(columnType, "int", "smallint", "smallint unsigned")) {
            return "Integer";
        } else if (StrUtil.equalsAny(columnType, "bigint", "int unsigned")) {
            return "Long";
        } else if (StrUtil.equalsAny(columnType, "float")) {
            return "Float";
        } else if (StrUtil.equalsAny(columnType, "double")) {
            return "Double";
        } else if (StrUtil.equalsAny(columnType, "decimal", "decimal unsigned")) {
            return "BigDecimal";
        }
        return "ErrorType";
    }


    public List<Object> searchParams(PreparedStatementCreator preparedStatementCreator) throws IllegalAccessException {
        Field[] declaredFields = preparedStatementCreator.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            if (declaredField.getName().equals("parameters")) {
                Object o = declaredField.get(preparedStatementCreator);
                return (List<Object>) o;
            }

        }
        return null;
    }


    public static void closeStatement(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException var2) {
                log.trace("Could not close JDBC Statement", var2);
            } catch (Throwable var3) {
                log.trace("Unexpected exception on closing JDBC Statement", var3);
            }
        }

    }

    private static String getSql(Object sqlProvider) {
        return sqlProvider instanceof SqlProvider ? ((SqlProvider) sqlProvider).getSql() : null;
    }

    protected PreparedStatementCreator getPreparedStatementCreator(String sql, SqlParameterSource paramSource) {
        ParsedSql parsedSql = NamedParameterUtils.parseSqlStatement(sql);
        String sqlToUse = NamedParameterUtils.substituteNamedParameters(parsedSql, paramSource);
        List<SqlParameter> declaredParameters = NamedParameterUtils.buildSqlParameterList(parsedSql, paramSource);
        PreparedStatementCreatorFactory preparedStatementCreatorFactory = new PreparedStatementCreatorFactory(sqlToUse, declaredParameters);
        Object[] params = NamedParameterUtils.buildValueArray(parsedSql, paramSource, (List) null);
        return preparedStatementCreatorFactory.newPreparedStatementCreator(params);
    }
}
