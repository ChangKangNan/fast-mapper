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
                }
            }
        }
        if (StrUtil.isBlank(primaryKey)) {
            throw new RuntimeException("Id annotation is necessary!");
        }
        stringBuilder.append(System.lineSeparator());
        stringBuilder.append(ArrayUtil.join(columns.toArray(), StrUtil.C_COMMA + System.lineSeparator()));
        stringBuilder.append(System.lineSeparator());
        stringBuilder.append("FROM");
        stringBuilder.append(StrUtil.SPACE);
        Table annotation = objClass.getAnnotation(Table.class);
        String table = annotation.name();
        stringBuilder.append(table);
        if (splicingParam.whereCondition.size() > 0 || FastMapperConfig.isOpenLogicDeletedAuto) {
            stringBuilder.append(System.lineSeparator());
            stringBuilder.append("WHERE");
            stringBuilder.append(StrUtil.SPACE);
            if(splicingParam.whereCondition.size()>=2){
                for (int i = 0; i <= splicingParam.whereCondition.size()-2; i++) {
                    splicingParam.whereCondition.get(i).isAnd= splicingParam.whereCondition.get(i+1).isAnd;
                }
                splicingParam.whereCondition.get(splicingParam.whereCondition.size()-1).isAnd=true;
            }
            if (CollUtil.isNotEmpty(splicingParam.whereCondition)) {
                for (int i = 0; i < splicingParam.whereCondition.size(); i++) {
                    stringBuilder.append(splicingParam.whereCondition.get(i).columnName);
                    stringBuilder.append(splicingParam.whereCondition.get(i).expression);
                    if(!Expression.Like.expression.equals(splicingParam.whereCondition.get(i).expression)){
                        if(Expression.In.expression.equals(splicingParam.whereCondition.get(i).expression)){
                            if (ArrayUtil.isArray(splicingParam.whereCondition.get(i).value)) {
                                stringBuilder.append(Expression.LeftBracket.expression);
                                Object[] wrap = ArrayUtil.wrap(splicingParam.whereCondition.get(i).value);
                                int j=0;
                                for (Object o : wrap) {
                                    j++;
                                    stringBuilder.append(CharPool.COLON);
                                    stringBuilder.append(splicingParam.whereCondition.get(i).columnName).append("_in_").append(j);
                                    stringBuilder.append(",");
                                    params.put(splicingParam.whereCondition.get(i).columnName + "_in_" + j, o);
                                }
                                stringBuilder=new StringBuilder(stringBuilder.substring(0,stringBuilder.length()-1));
                                stringBuilder.append(Expression.RightBracket.expression);
                            }
                        }else {
                            stringBuilder.append(CharPool.COLON);
                            stringBuilder.append(splicingParam.whereCondition.get(i).columnName).append("_").append(i);
                            params.put(splicingParam.whereCondition.get(i).columnName + "_" + i, splicingParam.whereCondition.get(i).value);
                        }
                    }else{
                        stringBuilder.append("'%").append(splicingParam.whereCondition.get(i).value).append("%'");
                    }
                    if (i != splicingParam.whereCondition.size() - 1) {
                        stringBuilder.append(System.lineSeparator());
                        if(splicingParam.whereCondition.get(i).isAnd){
                            stringBuilder.append("and");
                        }else {
                            stringBuilder.append("or");
                        }
                        stringBuilder.append(StrUtil.SPACE);
                    }
                }
                if (FastMapperConfig.isOpenLogicDeletedAuto) {
                    stringBuilder.append(StrUtil.SPACE);
                    stringBuilder.append("and");
                    stringBuilder.append(StrUtil.SPACE);
                    stringBuilder.append(FastMapperConfig.logicDeletedColumn);
                    stringBuilder.append(Expression.Equal.expression);
                    stringBuilder.append(CharPool.COLON);
                    stringBuilder.append(FastMapperConfig.logicDeletedColumn);
                    params.put(FastMapperConfig.logicDeletedColumn, FastMapperConfig.logicDeletedColumnDefaultValue);
                }
            } else {
                stringBuilder.append(StrUtil.SPACE);
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


}
