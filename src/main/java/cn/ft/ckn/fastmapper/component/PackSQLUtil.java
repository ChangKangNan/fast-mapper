package cn.ft.ckn.fastmapper.component;

import cn.ft.ckn.fastmapper.config.FastMapperConfig;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Pair;
import cn.hutool.core.text.CharPool;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.ssh.JschUtil;
import cn.hutool.json.JSONUtil;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.util.*;

/**
 * @author ckn
 * @date 2022/8/1
 */
public class PackSQLUtil {

    public static <T> Pair<Map<String, Object>, StringBuilder> packageSQL(SplicingParam splicingParam, Class<T> objClass) {
        StringBuilder stringBuilder = new StringBuilder("SELECT");
        Field[] declaredFields = objClass.getDeclaredFields();
        Map<String, Object> params = new HashMap<>();
        Map<String, String> columnToProperty = new HashMap<>();
        List<String> columns = new ArrayList<>();
        String primaryKey = null;
        if (ArrayUtil.isNotEmpty(declaredFields)) {
            for (Field field : declaredFields) {
                String propertyName = field.getName();
                Column fieldAnnotation = field.getAnnotation(Column.class);
                Id id = field.getAnnotation(Id.class);
                if (fieldAnnotation != null) {
                    String columnName = fieldAnnotation.name();
                    if (id != null) {
                        primaryKey = columnName;
                    }
                    columnToProperty.put(columnName, propertyName);
                    columns.add(columnName);
                } else {
                    throw new RuntimeException("Column annotation is necessary!");
                }
            }
        }
        if (StrUtil.isBlank(primaryKey)) {
            throw new RuntimeException("Id annotation is necessary!");
        }
        stringBuilder.append(System.lineSeparator());
        stringBuilder.append(ArrayUtil.join(columns.toArray(), StrUtil.C_COMMA + System.lineSeparator()));
        stringBuilder.append(System.lineSeparator());
        stringBuilder.append("From");
        stringBuilder.append(StrUtil.SPACE);
        Table annotation = objClass.getAnnotation(Table.class);
        String table = annotation.name();
        stringBuilder.append(table);
        if (splicingParam.whereCondition.size() > 0 || FastMapperConfig.isOpenLogicDeletedAuto) {
            stringBuilder.append(System.lineSeparator());
            stringBuilder.append("WHERE");
            stringBuilder.append(StrUtil.SPACE);
            if (CollUtil.isNotEmpty(splicingParam.whereCondition)) {
                for (int i = 0; i < splicingParam.whereCondition.size(); i++) {
                    stringBuilder.append(splicingParam.whereCondition.get(i).columnName);
                    stringBuilder.append(splicingParam.whereCondition.get(i).expression);
                    params.put(splicingParam.whereCondition.get(i).columnName, splicingParam.whereCondition.get(i).value);
                    stringBuilder.append(CharPool.COLON);
                    stringBuilder.append(splicingParam.whereCondition.get(i).columnName);
                    if (i != splicingParam.whereCondition.size() - 1) {
                        stringBuilder.append(System.lineSeparator());
                        stringBuilder.append("and");
                        stringBuilder.append(StrUtil.SPACE);
                    }
                }
                if (FastMapperConfig.isOpenLogicDeletedAuto) {
                    stringBuilder.append("and");
                    stringBuilder.append(FastMapperConfig.logicDeletedColumn);
                    stringBuilder.append(Expression.Equal.expression);
                    stringBuilder.append(CharPool.COLON);
                    stringBuilder.append(FastMapperConfig.logicDeletedColumn);
                    params.put(FastMapperConfig.logicDeletedColumn, FastMapperConfig.logicDeletedColumnDefaultValue);
                }
            } else {
                stringBuilder.append(FastMapperConfig.logicDeletedColumn);
                stringBuilder.append(Expression.Equal.expression);
                stringBuilder.append(CharPool.COLON);
                stringBuilder.append(FastMapperConfig.logicDeletedColumn);
                params.put(FastMapperConfig.logicDeletedColumn, FastMapperConfig.logicDeletedColumnDefaultValue);
            }
        }
        if (CollUtil.isNotEmpty(splicingParam.orderByCondition)) {
            stringBuilder.append(System.lineSeparator());
            stringBuilder.append("ORDER BY");
            stringBuilder.append(StrUtil.SPACE);
            for (int i = 0; i < splicingParam.orderByCondition.size(); i++) {
                stringBuilder.append(splicingParam.orderByCondition.get(i).orderByName);
                stringBuilder.append(StrUtil.SPACE);
                stringBuilder.append(splicingParam.orderByCondition.get(i).sequence);
                if (i != splicingParam.orderByCondition.size() - 1) {
                    stringBuilder.append(StrUtil.C_COMMA);
                }
            }
        }
        if (splicingParam.isOpenPage) {
            stringBuilder.append(System.lineSeparator());
            stringBuilder.append("Limit");
            stringBuilder.append(StrUtil.SPACE);
            stringBuilder.append((splicingParam.getPage() - 1)*splicingParam.getPageSize());
            stringBuilder.append(StrUtil.C_COMMA);
            stringBuilder.append(splicingParam.getPageSize());
        }
        return new Pair<>(params, stringBuilder);
    }

    static Object getValue(SplicingParam.WhereCondition whereCondition) {
        Object value = whereCondition.value;
        StringBuilder stringBuilder = new StringBuilder();
        if (value instanceof String || value instanceof Date) {
            stringBuilder.append("'");
        }
        if (StrUtil.equalsAny(whereCondition.expression, Expression.Like.name, Expression.NotLike.name)) {
            stringBuilder.append("%");
        }
        if (value instanceof Date) {
            stringBuilder.append(DateUtil.format((Date) value, "yyyy-MM-dd"));
        } else {
            stringBuilder.append(value);
        }
        if (StrUtil.equalsAny(whereCondition.expression, Expression.Like.name, Expression.NotLike.name)) {
            stringBuilder.append("%");
        }
        if (value instanceof String || value instanceof Date) {
            stringBuilder.append("'");
        }
        return stringBuilder.toString();
    }
}
