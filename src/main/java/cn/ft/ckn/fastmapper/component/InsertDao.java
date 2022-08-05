package cn.ft.ckn.fastmapper.component;

import cn.hutool.core.util.ReflectUtil;
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
import java.util.ArrayList;
import java.util.List;

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
        Class<T> aClass = (Class<T>) t.getClass();
        Table table = aClass.getAnnotation(Table.class);
        if (table == null) {
            throw new RuntimeException("Annotation Table is necessary!");
        }
        String tableName = table.name();
        Field[] declaredFields = aClass.getDeclaredFields();
        Method setPrimaryKey = null;
        int haveCount = 0;
        boolean isHavePrimary = false;
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
            ColumnParam columnParam = new ColumnParam();
            if (id != null) {
                setPrimaryKey = ReflectUtil.getMethodByName(aClass, setterName);
                isHavePrimary = true;
            } else {
                Object invoke = null;
                try {
                    invoke = ReflectUtil.getMethodByName(aClass, getterName).invoke(t);
                } catch (Exception e) {
                    e.printStackTrace();
                }
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
        System.out.println(JSONUtil.toJsonStr(columnParams));
        if (haveCount == 0) {
            throw new RuntimeException("录入无效对象!");
        }
        if (!isHavePrimary) {
            throw new RuntimeException("主键缺失!");
        }
        String insertSQL = "INSERT INTO " + tableName + " ("
                + String.join(", ", columnParams.stream().filter(ColumnParam::getHaveValue).map(ColumnParam::getColumnName).toArray(String[]::new))
                + ") VALUES (" + String.join(", ", columnParams.stream().filter(ColumnParam::getHaveValue).map(p -> ":" + p.getFieldName()).toArray(String[]::new))
                + ")";
        System.out.println("insert:"+insertSQL);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        NamedParameterJdbcTemplate jdbcTemplate = getJdbcTemplate();
        jdbcTemplate.update(insertSQL, new BeanPropertySqlParameterSource(t), keyHolder);
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
        for (Field field : declaredFields) {
            Column annotation = field.getAnnotation(Column.class);
            if (annotation == null || field.getName().equals("class")) {
                continue;
            }
            String columnName = annotation.name();
            String fieldName = field.getName();
            ColumnParam columnParam = new ColumnParam();
            columnParam.setColumnName(columnName);
            columnParam.setFieldName(fieldName);
            columnParams.add(columnParam);
        }
        String insertSQL = "INSERT INTO " + tableName + " ("
                + String.join(", ", columnParams.stream().map(ColumnParam::getColumnName).toArray(String[]::new))
                + ") VALUES (" + String.join(", ", columnParams.stream().map(p -> ":" + p.getFieldName()).toArray(String[]::new))
                + ")";
        System.out.println(insertSQL);
        SqlParameterSource[] sqlParameterSources = SqlParameterSourceUtils.createBatch(collection);
        NamedParameterJdbcTemplate jdbcTemplate = getJdbcTemplate();
        jdbcTemplate.batchUpdate(insertSQL, sqlParameterSources);
    }
}
