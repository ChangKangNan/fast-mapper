package cn.ft.ckn.fastmapper.util;

import cn.ft.ckn.fastmapper.component.ColumnParam;
import cn.hutool.core.util.ReflectUtil;

import javax.persistence.Column;
import javax.persistence.Id;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ckn
 * @date 2022/8/5
 */
public class ValueUtil {
    public static <T> List<ColumnParam> getColumns(T t) {
        Class<T> aClass = (Class<T>) t.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
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
            ColumnParam columnParam = new ColumnParam();
            if (id == null) {
                Object invoke = null;
                try {
                    invoke = ReflectUtil.getMethodByName(aClass, getterName).invoke(t);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                columnParam.setVal(invoke);
                boolean b = invoke != null;
                columnParam.setHaveValue(b);
            }
            columnParam.setType(field.getType());
            columnParam.setColumnName(columnName);
            columnParam.setFieldName(fieldName);
            columnParams.add(columnParam);
        }
        return columnParams;
    }
}
