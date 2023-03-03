package cn.ft.ckn.fastmapper.component.dao;

import cn.ft.ckn.fastmapper.bean.ColumnParam;
import cn.ft.ckn.fastmapper.component.manager.MapperDataSourceManger;
import cn.ft.ckn.fastmapper.bean.SplicingParam;
import cn.ft.ckn.fastmapper.config.FastMapperConfig;
import cn.ft.ckn.fastmapper.util.SQLUtil;
import cn.ft.ckn.fastmapper.transaction.TransactionManager;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author ckn
 * @date 2022/7/28
 */
public class InsertDao<T,R>  extends MapperDataSourceManger<R> {
    Class<T> objClass;

    public InsertDao(SplicingParam splicingParam, Class<T> classObj, Class<R> returnClass) {
        super(returnClass,splicingParam);
        this.objClass=classObj;
    }

    public InsertDao(Class<T> classObj) {
        super(null,new SplicingParam());
        this.objClass=classObj;
    }

    public T insert(T t) {
        if (t == null) {
            throw new RuntimeException("不能为空!");
        }
        Table table = objClass.getAnnotation(Table.class);
        if (table == null) {
            throw new RuntimeException("Annotation Table is necessary!");
        }
        String tableName = table.name();
        Field[] declaredFields = objClass.getDeclaredFields();
        Method setPrimaryKey = null;
        int haveCount = 0;
        boolean isHavePrimary = false;
        Method methodCreateTime=null;
        Method methodUpdateTime=null;
        Method methodDeleted = null;
        Map<String,Object> params=new HashMap<>();
        List<ColumnParam> columnParams = new ArrayList<>();
        for (Field field : declaredFields) {
            Column annotation = field.getAnnotation(Column.class);
            if (annotation == null || field.getName().equals("class")) {
                continue;
            }
            Id id = field.getAnnotation(Id.class);
            String columnName = annotation.name();
            String fieldName = field.getName();
            String getterName = "get" + StrUtil.upperFirst(fieldName);
            String setterName = "set" +  StrUtil.upperFirst(fieldName);
            if(StrUtil.isNotBlank(FastMapperConfig.createTime) && FastMapperConfig.createTime.equals(columnName)){
                methodCreateTime=ReflectUtil.getMethodByName(objClass, setterName);
            }
            if(StrUtil.isNotBlank(FastMapperConfig.updateTime) &&  FastMapperConfig.updateTime.equals(columnName)){
                methodUpdateTime=ReflectUtil.getMethodByName(objClass, setterName);
            }
            if(StrUtil.isNotBlank(FastMapperConfig.logicDeletedColumn)&& FastMapperConfig.logicDeletedColumn.equals(columnName)){
                methodDeleted=ReflectUtil.getMethodByName(objClass, setterName);
            }
            ColumnParam columnParam = new ColumnParam();
            if (id != null) {
                setPrimaryKey = ReflectUtil.getMethodByName(objClass, setterName);
                isHavePrimary = true;
                params.put(columnName,null);
            } else {
                Object invoke = null;
                try {
                    invoke = ReflectUtil.getMethodByName(objClass, getterName).invoke(t);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                params.put(fieldName,invoke);
                columnParam.setVal(invoke);
                boolean b = invoke == null;
                if (!b) {
                    haveCount++;
                }
                columnParam.setHaveValue(!b);
            }
            columnParam.setType(field.getType());
            columnParam.setColumnName(columnName);
            columnParam.setFieldName(fieldName);
            columnParams.add(columnParam);
        }
        if (haveCount == 0) {
            throw new RuntimeException("录入无效对象!");
        }
        if (!isHavePrimary) {
            throw new RuntimeException("主键缺失!");
        }
        String insertSQL = "INSERT INTO " + tableName + " ("
                + String.join(", ", columnParams.stream().map(ColumnParam::getColumnName).toArray(String[]::new))
                + ") VALUES (" + String.join(", ", columnParams.stream().map(p -> ":" + p.getFieldName()).toArray(String[]::new))
                + ")";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        DataSource dataSource = getDataSource();
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        try {
            if(FastMapperConfig.isOpenLogicDeletedAuto && methodDeleted !=null){
                methodDeleted.invoke(t,FastMapperConfig.logicDeletedColumnDefaultValue);
                params.put(FastMapperConfig.logicDeletedColumn,FastMapperConfig.logicDeletedColumnDefaultValue);
            }
            Date date = new Date();
            if(FastMapperConfig.isOpenCreateTimeAuto && methodCreateTime != null){
                methodCreateTime.invoke(t, date);
                params.put(FastMapperConfig.createTime,date);
            }
            if(FastMapperConfig.isOpenUpdateTimeAuto && methodUpdateTime != null){
                methodUpdateTime.invoke(t, date);
                params.put(FastMapperConfig.updateTime,date);
            }
        }catch (Exception e){throw new RuntimeException("默认字段设置异常");}
        TransactionManager.initTransaction(dataSource);
        int update = jdbcTemplate.update(insertSQL, new BeanPropertySqlParameterSource(t), keyHolder);
        if (FastMapperConfig.isOpenSQLPrint) {
            SQLUtil.print(SQLUtil.printSql(insertSQL,params)
                    , SQLUtil.printResult(update));
        }
        Number number = keyHolder.getKey();
        if (number != null) {
            long longValue = number.longValue();
            try {
                setPrimaryKey.invoke(t, longValue);
            } catch (Exception e) {
                throw new RuntimeException("设置主键失败!");
            }
        }
        return t;
    }

    public void insertBatch(List<T> collection) {
        Table table = objClass.getAnnotation(Table.class);
        if (table == null) {
            throw new RuntimeException("Annotation Table is necessary!");
        }
        String tableName = table.name();
        Field[] declaredFields = objClass.getDeclaredFields();
        List<ColumnParam> columnParams = new ArrayList<>();
        Method methodCreateTime=null;
        Method methodUpdateTime=null;
        Method methodDeleted = null;
        for (Field field : declaredFields) {
            Column annotation = field.getAnnotation(Column.class);
            if (annotation == null || field.getName().equals("class")) {
                continue;
            }
            String fieldName = field.getName();
            String setterName = "set" +  StrUtil.upperFirst(fieldName);

            if(StrUtil.isNotBlank(FastMapperConfig.createTime) && FastMapperConfig.createTime.equals(annotation.name())){
                methodCreateTime=ReflectUtil.getMethodByName(objClass, setterName);
            }
            if(StrUtil.isNotBlank(FastMapperConfig.updateTime) &&  FastMapperConfig.updateTime.equals(annotation.name())){
                methodUpdateTime=ReflectUtil.getMethodByName(objClass, setterName);
            }
            if(StrUtil.isNotBlank(FastMapperConfig.logicDeletedColumn)&& FastMapperConfig.logicDeletedColumn.equals(annotation.name())){
                methodDeleted=ReflectUtil.getMethodByName(objClass, setterName);
            }
            String columnName = annotation.name();
            ColumnParam columnParam = new ColumnParam();
            columnParam.setColumnName(columnName);
            columnParam.setFieldName(fieldName);
            columnParams.add(columnParam);
        }
        String insertSQL = "INSERT INTO " + tableName + " ("
                + String.join(", ", columnParams.stream().map(ColumnParam::getColumnName).toArray(String[]::new))
                + ") VALUES (" + String.join(", ", columnParams.stream().map(p -> ":" + p.getFieldName()).toArray(String[]::new))
                + ")";
        if(CollUtil.isNotEmpty(collection)){
            Method finalMethodDeleted = methodDeleted;
            Method finalMethodCreateTime = methodCreateTime;
            Method finalMethodUpdateTime = methodUpdateTime;
            collection.forEach(c->{
                try {
                    if(FastMapperConfig.isOpenLogicDeletedAuto && finalMethodDeleted !=null){
                        finalMethodDeleted.invoke(c,FastMapperConfig.logicDeletedColumnDefaultValue);
                    }
                    Date date = new Date();
                    if(FastMapperConfig.isOpenCreateTimeAuto && finalMethodCreateTime != null){
                        finalMethodCreateTime.invoke(c, date);
                    }
                    if(FastMapperConfig.isOpenUpdateTimeAuto && finalMethodUpdateTime != null){
                        finalMethodUpdateTime.invoke(c, date);
                    }
                }catch (Exception e){e.printStackTrace();}
            });
        }
        SqlParameterSource[] sqlParameterSources = SqlParameterSourceUtils.createBatch(collection);
        DataSource dataSource = getDataSource();
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        TransactionManager.initTransaction(dataSource);
        jdbcTemplate.batchUpdate(insertSQL, sqlParameterSources);
    }
}
