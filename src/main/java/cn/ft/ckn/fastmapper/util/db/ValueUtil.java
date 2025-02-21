package cn.ft.ckn.fastmapper.util.db;

import cn.ft.ckn.fastmapper.bean.FastMapperColumn;
import cn.hutool.core.util.ReflectUtil;

import javax.persistence.Column;
import javax.persistence.Id;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ckn
 */
public class ValueUtil {
    public static <T> List<FastMapperColumn> getColumns(T t,Class cls) {
        Field[] declaredFields = cls.getDeclaredFields();
        List<FastMapperColumn> columnParams = new ArrayList<>();
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
            FastMapperColumn FastMapperColumn = new FastMapperColumn();
            if (id == null) {
                Object invoke = null;
                try {
                    invoke = ReflectUtil.getMethodByName(cls, getterName).invoke(t);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                FastMapperColumn.setVal(invoke);
                boolean b = invoke != null;
                FastMapperColumn.setHaveValue(b);
                FastMapperColumn.setType(field.getType());
                FastMapperColumn.setColumnName(columnName);
                FastMapperColumn.setFieldName(fieldName);
                columnParams.add(FastMapperColumn);
            }
        }
        return columnParams;
    }
}
