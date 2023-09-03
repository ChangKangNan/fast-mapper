package cn.ft.ckn.fastmapper.util;


import cn.ft.ckn.fastmapper.bean.ColumnInfo;
import cn.ft.ckn.fastmapper.bean.GenerateTemplateConfig;
import cn.ft.ckn.fastmapper.bean.TableInfo;
import cn.hutool.core.util.StrUtil;

import java.io.File;
import java.sql.*;
import java.util.*;

/**
 * @author ckn
 * @date 2021/10/18
 */
public class DbUtil {
    private static DbUtil dbUtil = new DbUtil();

    private DbUtil() {
    }

    public static DbUtil getInstance() {
        return dbUtil;
    }

    /**
     * 获取所有表属性信息
     *
     * @param fileConfig       配置
     * @param metaData   数据库配置
     * @param tableNames 生成的表信息
     * @return 获取到的表属性映射
     * @throws Exception 异常
     */
    public List<TableInfo> getAllTables(GenerateTemplateConfig fileConfig, DatabaseMetaData metaData, Set<String> tableNames)
            throws Exception {
        String dataSourceName = metaData.getConnection().getCatalog();
        boolean isIgnore = fileConfig.getIgnorePrefix();
        ResultSet tables = metaData.getTables(dataSourceName, null, null, new String[]{"TABLE"});
        List<TableInfo> tableInfoList = new ArrayList<>();
        while (tables.next()){
            String table_name = tables.getString("TABLE_NAME");
            String tableDesc = tables.getString("REMARKS");
            for (String tableName : tableNames) {
                if (StrUtil.equalsAny(tableName,table_name,"all")) {
                    TableInfo tableInfo = new TableInfo();
                    String beanName = "";
                    if (isIgnore) {
                        beanName = table_name.substring(table_name.indexOf("_") + 1);
                    } else {
                        beanName = table_name;
                    }
                    tableInfo.setBeanName(StrUtil.toCamelCase(beanName));
                    tableInfo.setTableName(table_name);
                    tableInfo.setTableDesc(tableDesc);
                    String pojoName = StrUtil.toCamelCase("_" + beanName);
                    tableInfo.setPojoName(pojoName);
                    setAllColumns(tableInfo, metaData);
                    setPackPath(fileConfig, tableInfo);
                    tableInfoList.add(tableInfo);
                }
            }
        }
        return tableInfoList;
    }
    public  void setAllColumns(TableInfo tableInfo,DatabaseMetaData metaData) throws SQLException {
        String catalog = metaData.getConnection().getCatalog();
        String keyColumnName = primaryKeyColumnName(metaData, tableInfo.getTableName());
        ResultSet columns = metaData.getColumns(catalog, null, tableInfo.getTableName(), "%");
        String columnName;
        String columnType;
        String remarks;
        List<ColumnInfo> columnInfoList = new ArrayList<>();
        Set<String> importPackages=new HashSet<>();
        while (columns.next()){
            columnName = columns.getString("COLUMN_NAME");
            columnType = columns.getString("TYPE_NAME");
            remarks = columns.getString("remarks");
            remarks = remarks.replaceAll("\\s*|\r\n|\r|\n|\t", "");
            ColumnInfo info = new ColumnInfo();
            info.setColumnName(columnName);
            info.setColumnType(columnType);
            info.setColumnRemarks(remarks);
            String propertyName = StrUtil.toCamelCase(columnName);
            String fieldType = getFieldType(columnType, importPackages);
            info.setPropertyName(propertyName);
            info.setPropertyType(fieldType);
            info.setKey(columnName.equals(keyColumnName));
            columnInfoList.add(info);
        }
        tableInfo.setColumns(columnInfoList);
        tableInfo.setPackages(importPackages);
    }


    /**
     * 只做单主键代码的生成
     *
     * @param metaData  参数
     * @param tableName 表名称
     * @return 主键
     * @throws SQLException 错误
     */
    public String primaryKeyColumnName(DatabaseMetaData metaData, String tableName) throws SQLException {
        String primaryKeyColumnName = null;
        String catalog = metaData.getConnection().getCatalog();
        ResultSet primaryKeyResultSet = metaData.getPrimaryKeys(catalog, null, tableName);
        while (primaryKeyResultSet.next()) {
            primaryKeyColumnName = primaryKeyResultSet.getString("COLUMN_NAME");
            break;
        }
        if (primaryKeyColumnName == null) {
            primaryKeyColumnName = "id";
        }
        return primaryKeyColumnName;
    }

    /**
     * 文件路径设置
     *
     * @param conf      配置信息
     * @param tableInfo 表信息
     */
    public void setPackPath(GenerateTemplateConfig conf, TableInfo tableInfo) {
        String separator = File.separator;
        String childModuleName = conf.getChildModuleName();
        StringBuilder javaBasePath =new StringBuilder();
        String dir = conf.getTest() ? "test" : "main";
        javaBasePath.append(System.getProperty("user.dir")).append(separator);
        if (StrUtil.isNotBlank(childModuleName)) {
            javaBasePath.append(childModuleName).append(separator).append("src").append(separator).append(dir).append(separator).append("java").append(separator);
        } else {
            javaBasePath.append("src").append(separator).append(dir).append(separator).append("java").append(separator);
        }
        String pojoPackagePath = conf.getBasePackage();
        tableInfo.setPojoPackagePath(pojoPackagePath);
        tableInfo.setPojoFilePath(javaBasePath + tableInfo.getPojoPackagePath().replace(".", separator)+separator+"fm"+separator +"bean"+separator+tableInfo.getPojoName() + ".java");
        tableInfo.setPojoActionName(StrUtil.upperFirst(tableInfo.getPojoName())+"Action");
        tableInfo.setPojoActionFilePath(javaBasePath + tableInfo.getPojoPackagePath().replace(".", separator)+separator+"fm"+separator+"action"+separator+tableInfo.getPojoActionName()+".java");
        tableInfo.setPojoMapperName(StrUtil.upperFirst(tableInfo.getPojoName())+"Mapper");
        tableInfo.setPojoMapperFilePath(javaBasePath + tableInfo.getPojoPackagePath().replace(".", separator)+separator+"fm"+separator+"dao"+separator+tableInfo.getPojoMapperName()+".java");
 }

    /**
     * 返回一个与特定数据库的连接
     *
     * @param fileConfig 配置
     * @return 数据库连接
     * @throws ClassNotFoundException 错误
     */
    public Connection getConnection(GenerateTemplateConfig fileConfig) throws ClassNotFoundException {
        Connection connection = null;
        try {
            String driverClass = fileConfig.getDriverClass();
            String url = fileConfig.getUrl();
            String user = fileConfig.getUser();
            String password = fileConfig.getPassword();
            Class.forName(driverClass);
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 设置字段类型 MySql数据类型
     * DATABASE TYPE -> JAVA TYPE
     * @param columnType 列类型字符串
     * @param packages   封装包信息
     * @return 数据类型
     */
    public static String getFieldType(String columnType, Set<String> packages) {
        if (StrUtil.equalsAnyIgnoreCase(columnType,"varchar","nvarchar","char","text","mediumtext","longtext"))
        {
            return "String";
        } else if (StrUtil.equalsAnyIgnoreCase(columnType,"tinyblob","blob","mediumblob","longblob")) {
            return "byte[]";
        } else if (StrUtil.equalsAnyIgnoreCase(columnType,"datetime","date","timestamp","time","year")) {
            packages.add("import java.util.Date;");
            return "Date";
        } else if (StrUtil.equalsAnyIgnoreCase(columnType,"bit","tinyint","tinyint unsigned")) {
            return "Boolean";
        } else if (StrUtil.equalsAnyIgnoreCase(columnType,"int","smallint","smallint unsigned")) {
            return "Integer";
        }  else if (StrUtil.equalsAnyIgnoreCase(columnType,"bigint","bigint unsigned","int unsigned")) {
            return "Long";
        } else if (StrUtil.equalsAnyIgnoreCase(columnType,"float")) {
            return "Float";
        } else if (StrUtil.equalsAnyIgnoreCase(columnType,"double")) {
            return "Double";
        } else if (StrUtil.equalsAnyIgnoreCase(columnType,"decimal","decimal unsigned")) {
            packages.add("import java.math.BigDecimal;");
            return "BigDecimal";
        }
        return "ErrorType";
    }
}
