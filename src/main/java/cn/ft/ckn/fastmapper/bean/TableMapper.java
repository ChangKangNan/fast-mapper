package cn.ft.ckn.fastmapper.bean;

import cn.hutool.core.collection.CollUtil;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ckn
 */
public class TableMapper<T> {

    public static <T> void init(Class<T> classObj) {
        TableMapper<T> tableMapper = new TableMapper<>();
        tableMapper.setObjClass(classObj);
        String tableName = classObj.getAnnotation(Table.class) != null ? classObj.getAnnotation(Table.class).name() : classObj.getSimpleName();
        tableMapper.setTableName(tableName);
        Field[] declaredFields = classObj.getDeclaredFields();
        List<String> fields = Arrays.stream(declaredFields).map(Field::getName).collect(Collectors.toList());
        List<String> columns = Arrays.stream(declaredFields).map(field -> field.getAnnotation(Column.class) != null ? field.getAnnotation(Column.class).name() : field.getName()
        ).collect(Collectors.toList());
        List<String> types = Arrays.stream(declaredFields).map(field -> field.getType().getSimpleName()).collect(Collectors.toList());
        HashMap<String, String> fieldToColumn = new HashMap<>();
        HashMap<String, String> fieldToType = new HashMap<>();
        for (int i = 0; i < fields.size(); i++) {
            String s = fields.get(i);
            fieldToColumn.put(s, columns.get(i));
            fieldToType.put(s, types.get(i));
        }
        tableMapper.setFieldToColumn(fieldToColumn);
        tableMapper.setFieldToFieldType(fieldToType);
        tableMapper.setShowFields(fields);
        tableMapper.setTableName(tableName);
        tableMapper.setObjClass(classObj);
        tableMapper.setClassName(classObj.getSimpleName());
        List<Field> pks = Arrays.stream(declaredFields).filter(field -> field.getAnnotation(Id.class) != null).collect(Collectors.toList());
        if (CollUtil.isNotEmpty(pks)) {
            Column annotation = pks.get(0).getAnnotation(Column.class);
            if (annotation == null) {
                tableMapper.setPrimaryKey(pks.get(0).getName());
            } else {
                tableMapper.setPrimaryKey(annotation.name());
            }
        }
        SearchParam.init(tableMapper);
    }

    public TableMapper(){
        fieldToColumn=new HashMap<>();
        fieldToFieldType=new HashMap<>();
        showFields=new ArrayList<>();
    }

    /**
     * java class
     */
    private Class<T> objClass;

    public Class<T> getObjClass() {
        return objClass;
    }

    public void setObjClass(Class<T> objClass) {
        this.objClass = objClass;
    }

    /**
     * 表名
     */
    private String tableName;

    /**
     * java类名
     */
    private String className;

    /**
     * 主键
     */
    private String primaryKey;

    private List<String> columns;

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public List<String> getColumns() {
        return columns;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }

    /**
     * 字段对应数据库列名映射
     */
    private HashMap<String,String> fieldToColumn;
    /**
     * 字段对应字段类型映射
     */
    private HashMap<String,String> fieldToFieldType;

    private List<String> showFields;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public HashMap<String, String> getFieldToColumn() {
        return fieldToColumn;
    }

    public void setFieldToColumn(HashMap<String, String> fieldToColumn) {
        this.fieldToColumn = fieldToColumn;
    }

    public HashMap<String, String> getFieldToFieldType() {
        return fieldToFieldType;
    }

    public void setFieldToFieldType(HashMap<String, String> fieldToFieldType) {
        this.fieldToFieldType = fieldToFieldType;
    }

    public List<String> getShowFields() {
        return showFields;
    }

    public void setShowFields(List<String> showFields) {
        this.showFields = showFields;
    }
}
