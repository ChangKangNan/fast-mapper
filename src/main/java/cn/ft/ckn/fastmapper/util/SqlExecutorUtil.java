package cn.ft.ckn.fastmapper.util;

import cn.ft.ckn.fastmapper.component.manager.MapperDataSourceManger;
import cn.ft.ckn.fastmapper.bean.PageInfo;
import cn.ft.ckn.fastmapper.bean.SplicingParam;
import cn.ft.ckn.fastmapper.config.FastMapperConfig;
import cn.ft.ckn.fastmapper.transaction.TransactionManager;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 自定义sql查询与更新
 *
 * @author ckn
 * @date 2022/8/5
 */
public class SqlExecutorUtil extends MapperDataSourceManger<SqlExecutorUtil> {
    private static final Log log = LogFactory.getLog(SqlExecutorUtil.class);
    private static final Integer MAX_SHOW_COUNT = 1000;

    public SqlExecutorUtil(SplicingParam splicingParam) {
        super(SqlExecutorUtil.class, splicingParam);
    }

    public TimeInterval interval = new TimeInterval();

    public static SqlExecutorUtil build() {
        return new SqlExecutorUtil(new SplicingParam());
    }

    public PageInfo<Map<String, Object>> queryPage(String prepareSql, Integer pageNumber, Integer pageSize, Map<String, Object> params) {
        StringBuilder sql = new StringBuilder(prepareSql);
        DataSource dataSource = getDataSource();
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        TransactionManager.initTransaction(dataSource);
        StringBuilder countSQL = new StringBuilder("SELECT count(*) ");
        int indexOf = sql.toString().toLowerCase().indexOf("from");
        countSQL.append(sql.substring(indexOf));
        Integer totalCount = jdbcTemplate.queryForObject(countSQL.toString(), new HashMap<>(), Integer.class);
        if (pageNumber != null && pageSize != null) {
            sql.append(System.lineSeparator());
            sql.append("LIMIT");
            sql.append(StrUtil.SPACE);
            int pageNum = pageNumber - 1;
            sql.append(pageNum * pageSize);
            sql.append(StrUtil.C_COMMA);
            sql.append(pageSize);
        }
        try {
            List<Map<String, Object>> mapList = jdbcTemplate.queryForList(sql.toString(), params);
            PageInfo<Map<String, Object>> mapPageInfo = new PageInfo<>(mapList, pageNumber, pageSize, totalCount);
            if (FastMapperConfig.isOpenSQLPrint) {
                SQLUtil.print(SQLUtil.printSql(sql.toString(), params)
                        , SQLUtil.printResult(JSONUtil.toJsonStr(mapPageInfo)));
            }
            return mapPageInfo;
        } catch (Exception e) {
            if (FastMapperConfig.isOpenSQLPrint) {
                SQLUtil.print(SQLUtil.printSql(sql.toString(), params)
                        , SQLUtil.printResult(""));
            }
            return new PageInfo<>(new ArrayList<>(), pageNumber, pageSize, totalCount);
        }
    }

    public <R> List<R> queryList(String sql, Map<String, Object> params, Class<R> returnObj) {
        DataSource dataSource = getDataSource();
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        TransactionManager.initTransaction(dataSource);
        try {
            List<R> list = jdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(returnObj));
            if (FastMapperConfig.isOpenSQLPrint) {
                SQLUtil.print(SQLUtil.printSql(sql, params)
                        , SQLUtil.printResult(JSONUtil.toJsonStr(list)));
            }
            return list;
        } catch (Exception e) {
            if (FastMapperConfig.isOpenSQLPrint) {
                SQLUtil.print(SQLUtil.printSql(sql.toString(), new HashMap<>())
                        , SQLUtil.printResult(""));
            }
            return new ArrayList<>();
        }
    }

    public <R> List<R> queryList(String sql, Class<R> returnObj) {
        DataSource dataSource = getDataSource();
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        TransactionManager.initTransaction(dataSource);
        try {
            List<R> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(returnObj));
            if (FastMapperConfig.isOpenSQLPrint) {
                SQLUtil.print(SQLUtil.printSql(sql, new HashMap<>())
                        , SQLUtil.printResult(JSONUtil.toJsonStr(list)));
            }
            return list;
        } catch (Exception e) {
            if (FastMapperConfig.isOpenSQLPrint) {
                SQLUtil.print(SQLUtil.printSql(sql.toString(), new HashMap<>())
                        , SQLUtil.printResult(""));
            }
            return new ArrayList<>();
        }
    }

    /**
     * 通过 sql文件运行获得返回结果
     *
     * @param filePath
     * @param parameters
     * @param rowMapperClass
     * @return
     */
    public <E> List<E> queryByFile(String filePath, Map<String, Object> parameters, Class<E> rowMapperClass) {
        //处理参数
        ClassPathResource resource = new ClassPathResource(filePath);
        String sql = IoUtil.read(resource.getStream()).toString();
        DataSource dataSource = getDataSource();
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        TransactionManager.initTransaction(dataSource);
        return jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper<>(rowMapperClass));
    }

    /**
     * 自定义sql执行
     *
     * @param sql
     * @param parameters
     * @return
     */
    public int execute(String sql, Map<String, Object> parameters) {
        DataSource dataSource = getDataSource();
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        TransactionManager.initTransaction(dataSource);
        int update = jdbcTemplate.update(sql, parameters);
        if (FastMapperConfig.isOpenSQLPrint) {
            SQLUtil.print(SQLUtil.printSql(sql, parameters)
                    , SQLUtil.printResult(update));
        }
        return update;
    }

    /**
     * 自定义sql执行
     *
     * @param sql
     * @return
     */
    public int execute(String sql) {
        DataSource dataSource = getDataSource();
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        TransactionManager.initTransaction(dataSource);
        int update = jdbcTemplate.update(sql, new HashMap<>());
        if (FastMapperConfig.isOpenSQLPrint) {
            SQLUtil.print(sql
                    , SQLUtil.printResult(update));
        }
        return update;
    }

    /**
     * 根据map数据插入
     *
     * @param tableName
     * @param dataMap
     */
    public void insert(String tableName, Map<String, Object> dataMap) {
        DataSource dataSource = getDataSource();
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        TransactionManager.initTransaction(dataSource);
        List<String> columnList = new ArrayList<>();
        Map<String, Object> mapDef = new HashMap<>();
        try {
            columnList = getAllColumns(dataSource.getConnection(), tableName, mapDef);
        } catch (Exception e) {
            log.info("连接数据库失败!");
            return;
        }
        StringBuilder insertSQLBuilder = new StringBuilder("INSERT INTO");
        insertSQLBuilder.append(StrUtil.SPACE);
        insertSQLBuilder.append(tableName);
        insertSQLBuilder.append("(");
        String join = String.join(StrUtil.COMMA, columnList);
        insertSQLBuilder.append(join);
        insertSQLBuilder.append(")");
        insertSQLBuilder.append("VALUES");
        insertSQLBuilder.append("(");
        String values = columnList.stream().map(k -> {
            Object o = dataMap.get(k);
            if (o == null) {
                o = mapDef.get(k);
            }
            if (o == null) {
                return "null";
            }
            return convertParams(o);
        }).collect(Collectors.joining(StrUtil.COMMA));
        insertSQLBuilder.append(values);
        insertSQLBuilder.append(")");
        final String finalSql = insertSQLBuilder.toString();
        int update = jdbcTemplate.update(finalSql, new HashMap<>());
        if (FastMapperConfig.isOpenSQLPrint) {
            SQLUtil.print(finalSql
                    , SQLUtil.printResult(update));
        }
    }

    /**
     * 查询map数据
     * @param prepareSql
     * @param params
     * @return
     */
    public List<Map<String,Object>> queryMap(String prepareSql,Map<String, Object> params){
        DataSource dataSource = getDataSource();
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        TransactionManager.initTransaction(dataSource);
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(prepareSql, params);
        if (FastMapperConfig.isOpenSQLPrint) {
            SQLUtil.print(SQLUtil.printSql(prepareSql, params)
                    , SQLUtil.printResult(JSONUtil.toJsonStr(mapList)));
        }
        return mapList;
    }

    /**
     * 批量插入
     *
     * @param tableName
     * @param dataMaps
     */
    public void insertBatch(String tableName, List<Map<String, Object>> dataMaps) {
        if (dataMaps.size() == 0) {
            return;
        }
        DataSource dataSource = getDataSource();
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        TransactionManager.initTransaction(dataSource);
        List<String> columnList = new ArrayList<>();
        Map<String, Object> mapDef = new HashMap<>();
        try {
            columnList = getAllColumns(dataSource.getConnection(), tableName, mapDef);
        } catch (Exception e) {
            log.info("连接数据库失败!");
            return;
        }
        log.info("开始批量插入!");
        interval.start();
        List<List<Map<String, Object>>> lists = ListUtil.split(dataMaps, MAX_SHOW_COUNT);
        for (List<Map<String, Object>> list : lists) {
            StringBuilder insertSQLBuilder = new StringBuilder("INSERT INTO");
            insertSQLBuilder.append(StrUtil.SPACE);
            insertSQLBuilder.append(tableName);
            insertSQLBuilder.append("(");
            String join = String.join(StrUtil.COMMA, columnList);
            insertSQLBuilder.append(join);
            insertSQLBuilder.append(")");
            insertSQLBuilder.append("VALUES");
            for (Map<String, Object> map : list) {
                insertSQLBuilder.append("(");
                String values = columnList.stream().map(k -> {
                    Object o = map.get(k);
                    if (o == null) {
                        o = mapDef.get(k);
                    }
                    if (o == null) {
                        return "null";
                    }
                    return convertParams(o);
                }).collect(Collectors.joining(StrUtil.COMMA));
                insertSQLBuilder.append(values);
                insertSQLBuilder.append(")");
                insertSQLBuilder.append(StrUtil.COMMA);
            }
            final String finalSql = insertSQLBuilder.substring(0, insertSQLBuilder.length() - 1);
            int update = jdbcTemplate.update(finalSql, new HashMap<>());
            if (list.size() <= MAX_SHOW_COUNT && FastMapperConfig.isOpenSQLPrint) {
                SQLUtil.print(finalSql
                        , SQLUtil.printResult(update));
            }
        }
        log.info("批量插入成功!");
        log.info("耗时:[" + interval.intervalMs() + "]ms");
    }

    /**
     * 检索数据库字段,并嵌入默认值
     *
     * @param connection
     * @param tableName
     * @return
     * @throws SQLException
     */
    private List<String> getAllColumns(Connection connection, String tableName, Map<String, Object> map) throws SQLException {
        List<String> columnList = new ArrayList<>();
        DatabaseMetaData metaData = connection.getMetaData();
        String dataSourceName = metaData.getConnection().getCatalog();
        ResultSet tables = metaData.getTables(dataSourceName, null, null, new String[]{"TABLE"});
        while (tables.next()) {
            String table_name = tables.getString("TABLE_NAME");
            if (table_name.equals(tableName)) {
                String catalog = metaData.getConnection().getCatalog();
                ResultSet columns = metaData.getColumns(catalog, null, table_name, "%");
                String columnName;
                while (columns.next()) {
                    columnName = columns.getString("COLUMN_NAME");
                    String columnType = columns.getString("TYPE_NAME");
                    boolean nullable = columns.getBoolean("IS_NULLABLE");
                    boolean autoincrement = columns.getBoolean("IS_AUTOINCREMENT");
                    columnType = columnType.toLowerCase();
                    Object columnDef = null;
                    if (!nullable) {
                        if (StrUtil.equalsAny(columnType, "varchar", "nvarchar", "char", "text", "mediumtext")) {
                            columnDef = columns.getString("COLUMN_DEF");
                        } else if (StrUtil.equalsAny(columnType, "tinyblob", "blob", "mediumblob", "longblob")) {
                            columnDef = columns.getByte("COLUMN_DEF");
                        } else if (StrUtil.equalsAny(columnType, "datetime", "date", "timestamp", "time", "year")) {
                            columnDef = columns.getDate("COLUMN_DEF");
                        } else if (StrUtil.equalsAny(columnType, "bit", "tinyint", "tinyint unsigned")) {
                            columnDef = columns.getBoolean("COLUMN_DEF");
                        } else if (StrUtil.equalsAny(columnType, "int", "smallint", "smallint unsigned")) {
                            columnDef = columns.getInt("COLUMN_DEF");
                        } else if (StrUtil.equalsAny(columnType, "bigint", "int unsigned")) {
                            columnDef = columns.getLong("COLUMN_DEF");
                        } else if (StrUtil.equalsAny(columnType, "float")) {
                            columnDef = columns.getFloat("COLUMN_DEF");
                        } else if (StrUtil.equalsAny(columnType, "double")) {
                            columnDef = columns.getDouble("COLUMN_DEF");
                        } else if (StrUtil.equalsAny(columnType, "decimal", "decimal unsigned")) {
                            columnDef = columns.getBigDecimal("COLUMN_DEF");
                        }
                        if (autoincrement) {
                            columnDef = null;
                        }
                        if (columnDef != null) {
                            map.put(columnName, columnDef);
                        }
                    }
                    columnList.add(columnName);
                }
            }
        }
        return columnList;
    }

    /**
     * 类型参数转换
     *
     * @param object
     * @return
     */
    private String convertParams(Object object) {
        if (object == null) {
            return "null";
        }
        if (object instanceof Number) {
            return object + "";
        } else if (object instanceof Date) {
            try {
                return "'" + DateUtil.format((Date) object, "yyyy-MM-dd HH:mm:ss") + "'";
            } catch (Exception e) {
                return "'" + DateUtil.format((Date) object, "yyyy-MM-dd") + "'";
            }
        } else if (object instanceof LocalDate) {
            LocalDate date = (LocalDate) object;
            int year = date.getYear();
            int month = date.getMonthValue();
            int day = date.getDayOfMonth();
            String monthVal = month >= 10 ? month + "" : "0" + month;
            String dayVal = day >= 10 ? day + "" : "0" + month;
            return "'" + year + "-" + monthVal + "-" + dayVal + "'";
        } else if (object instanceof LocalDateTime) {
            LocalDateTime dateTime = (LocalDateTime) object;
            int year = dateTime.getYear();
            int month = dateTime.getMonthValue();
            int day = dateTime.getDayOfMonth();
            int hour = dateTime.getHour();
            int minute = dateTime.getMinute();
            int second = dateTime.getSecond();
            String monthVal = month >= 10 ? month + "" : "0" + month;
            String dayVal = day >= 10 ? day + "" : "0" + month;
            String hourVal = hour >= 10 ? hour + "" : "0" + hour;
            String minuteVal = minute >= 10 ? minute + "" : "0" + minute;
            String secondVal = second >= 10 ? second + "" : "0" + second;
            return "'" + year + "-" + monthVal + "-" + dayVal + " " + hourVal + "-" + minuteVal + "-" + secondVal + "'";
        } else if (object instanceof String) {
            return "'" + object + "'";
        } else if (object instanceof Boolean) {
            return (Boolean) object ? "true" : "false";
        }
        return "";
    }

    /**
     * 单一返回值查询
     * @param sql
     * @param t
     * @param <T>
     * @return
     */
    public <T> T executeForObject(String sql,Class<T> t){
        DataSource dataSource = getDataSource();
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        TransactionManager.initTransaction(dataSource);
        return jdbcTemplate.queryForObject(sql, new HashMap<>(), t);
    }

    /**
     * 单一返回值查询
     * @param sql
     * @param params 参数集合
     * @param t
     * @param <T>
     * @return
     */
    public <T> T executeForObject(String sql,Map<String, Object> params,Class<T> t){
        DataSource dataSource = getDataSource();
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        TransactionManager.initTransaction(dataSource);
        T forObject = jdbcTemplate.queryForObject(sql, params, t);
        if (FastMapperConfig.isOpenSQLPrint) {
            SQLUtil.print(SQLUtil.printSql(sql, params)
                    , SQLUtil.printResult(JSONUtil.toJsonStr(forObject)));
        }
        return forObject;
    }
}
