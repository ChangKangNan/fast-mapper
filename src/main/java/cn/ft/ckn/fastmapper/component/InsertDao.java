package cn.ft.ckn.fastmapper.component;

import cn.ft.ckn.fastmapper.config.FastMapperConfig;
import cn.ft.ckn.fastmapper.util.SQLUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author ckn
 * @date 2022/7/28
 */
public class InsertDao<T,R>  extends MapperDataSourceManger<R>{
    Class<T> objClass;

    public InsertDao(SplicingParam splicingParam,Class<T> classObj, Class<R> returnClass) {
        super(returnClass,splicingParam);
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
        String deletedColumn = FastMapperConfig.logicDeletedColumn;
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
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getterName = "get" + firstLetter + fieldName.substring(1);
            String setterName = "set" + firstLetter + fieldName.substring(1);
            if(setterName.equals("setCreateTime")){
                methodCreateTime=ReflectUtil.getMethodByName(objClass, setterName);
            }
            if(setterName.equals("setUpdateTime")){
                methodUpdateTime=ReflectUtil.getMethodByName(objClass, setterName);
            }
            if(StrUtil.isNotBlank(deletedColumn)&&setterName.equals("set" + deletedColumn.substring(0, 1).toUpperCase() + deletedColumn.substring(1))){
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
        NamedParameterJdbcTemplate jdbcTemplate = getJdbcTemplate();
        try {
            if(FastMapperConfig.isOpenLogicDeletedAuto && methodDeleted !=null){
                methodDeleted.invoke(t,FastMapperConfig.logicDeletedColumnDefaultValue);
                params.put(FastMapperConfig.logicDeletedColumn,FastMapperConfig.logicDeletedColumnDefaultValue);
            }
            Date date = new Date();
            if(FastMapperConfig.isOpenCreateTimeAuto && methodCreateTime != null){
                methodCreateTime.invoke(t, date);
                params.put("createTime",date);
            }
            if(FastMapperConfig.isOpenUpdateTimeAuto && methodUpdateTime != null){
                methodUpdateTime.invoke(t, date);
                params.put("updateTime",date);
            }
        }catch (Exception e){throw new RuntimeException("默认字段设置异常");}
        if(FastMapperConfig.isOpenSQLPrint) {
            SQLUtil.print(insertSQL, params, "INSERT");
        }
        int update = jdbcTemplate.update(insertSQL, new BeanPropertySqlParameterSource(t), keyHolder);
        if(FastMapperConfig.isOpenSQLPrint) {
            SQLUtil.printResult(JSONUtil.toJsonStr(update));
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
        String deletedColumn = FastMapperConfig.logicDeletedColumn;
        for (Field field : declaredFields) {
            Column annotation = field.getAnnotation(Column.class);
            if (annotation == null || field.getName().equals("class")) {
                continue;
            }
            String fieldName = field.getName();
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String setterName = "set" + firstLetter + fieldName.substring(1);
            if(setterName.equals("setCreateTime")){
                methodCreateTime=ReflectUtil.getMethodByName(objClass, setterName);
            }
            if(setterName.equals("setUpdateTime")){
                methodUpdateTime=ReflectUtil.getMethodByName(objClass, setterName);
            }
            if(StrUtil.isNotBlank(deletedColumn)&&setterName.equals("set" + deletedColumn.substring(0, 1).toUpperCase() + deletedColumn.substring(1))){
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
        NamedParameterJdbcTemplate jdbcTemplate = getJdbcTemplate();
        jdbcTemplate.batchUpdate(insertSQL, sqlParameterSources);
    }
}
