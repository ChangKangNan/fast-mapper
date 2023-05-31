package cn.ft.ckn.fastmapper.join;

import cn.ft.ckn.fastmapper.annotation.SFunction;
import cn.ft.ckn.fastmapper.util.ColumnUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;

import javax.persistence.Column;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JoinCustomer<T> extends JoinManager<T> {

    public JoinCustomer(Class<T> main) {
        super(new JoinParams());
        Table mainClassAnnotation = main.getAnnotation(Table.class);
        params.mainTable = mainClassAnnotation.name();
        params.obj = main;
    }

    public JoinCustomer(JoinParams joinParams) {
        super(joinParams);
    }

    public <L, K> FieldJoin<T, L> leftJoin(Class<L> joinClass
            , SFunction<T, K> mainKey, SFunction<L, K> joinKey) {
        Table annotation = joinClass.getAnnotation(Table.class);
        String tableName = annotation.name();
        String kName = ColumnUtil.getFieldName(mainKey);
        String vName = ColumnUtil.getFieldName(joinKey);
        params.obj = joinClass;
        params.joins.put(tableName, new HashMap<String, String>() {{
            put(params.mainTable + StrUtil.DOT + kName, tableName + StrUtil.DOT + vName);
        }});
        params.relation.put(tableName, "LEFT JOIN");
        params.deeps.put(tableName, 1);
        return new FieldJoin<>(params);
    }


    public <R, K> FieldJoin<T, R> rightJoin(Class<R> joinClass
            , SFunction<T, K> mainKey, SFunction<R, K> joinKey) {
        Table annotation = joinClass.getAnnotation(Table.class);
        String tableName = annotation.name();
        params.obj = joinClass;
        String kName = ColumnUtil.getFieldName(mainKey);
        String vName = ColumnUtil.getFieldName(joinKey);
        params.joins.put(tableName, new HashMap<String, String>() {{
            put(params.mainTable + StrUtil.DOT + kName, tableName + StrUtil.DOT + vName);
        }});
        params.relation.put(tableName, "RIGHT JOIN");
        params.deeps.put(tableName, 1);
        return new FieldJoin<>(params);
    }

    public <I, K> FieldJoin<T, I> innerJoin(Class<I> joinClass
            , SFunction<T, K> mainKey, SFunction<I, K> joinKey) {
        Table annotation = joinClass.getAnnotation(Table.class);
        String tableName = annotation.name();
        params.obj = joinClass;
        String kName = ColumnUtil.getFieldName(mainKey);
        String vName = ColumnUtil.getFieldName(joinKey);
        params.joins.put(tableName, new HashMap<String, String>() {{
            put(params.mainTable + StrUtil.DOT + kName, tableName + StrUtil.DOT + vName);
        }});
        params.relation.put(tableName, "INNER JOIN");
        params.deeps.put(tableName, 1);
        return new FieldJoin<>(params);
    }


    public <X, K> JoinCustomer<T> leftJoinGroup(JoinCustomer<X> leftGroup
            , SFunction<T, K> mainKey, SFunction<X, K> joinKey) {
        if (ArrayUtil.isNotEmpty(leftGroup.params.columns)) {
            this.params.columns.addAll(leftGroup.params.columns);
        }
        if (MapUtil.isNotEmpty(leftGroup.params.where)) {
            this.params.where.putAll(leftGroup.params.where);
        }
        Map<String, String> map = new HashMap<>();
        String kName = ColumnUtil.getFieldName(mainKey);
        String vName = ColumnUtil.getFieldName(joinKey);
        map.put(this.params.mainTable + StrUtil.DOT + kName, leftGroup.params.mainTable + StrUtil.DOT + vName);
        this.params.joins.put(leftGroup.params.mainTable, map);
        if (MapUtil.isNotEmpty(leftGroup.params.joins)) {
            Map<String, Integer> m = new HashMap<>();
            for (String key : leftGroup.params.deeps.keySet()) {
                Integer integer = leftGroup.params.deeps.get(key);
                m.put(key, integer + 1);
            }
            this.params.deeps.putAll(m);
            this.params.joins.putAll(leftGroup.params.joins);
        }
        if (MapUtil.isNotEmpty(leftGroup.params.relation)) {
            this.params.relation.putAll(leftGroup.params.relation);
        }
        String className = ColumnUtil.getClassName(joinKey);
        this.params.relation.put(className, "LEFT JOIN");
        params.deeps.put(className, 1);
        return this;
    }

    public <Y, K> JoinCustomer<T> rightJoinGroup(JoinCustomer<Y> rightGroup
            , SFunction<T, K> mainKey, SFunction<Y, K> joinKey) {
        if (ArrayUtil.isNotEmpty(rightGroup.params.columns)) {
            this.params.columns.addAll(rightGroup.params.columns);
        }
        if (MapUtil.isNotEmpty(rightGroup.params.where)) {
            this.params.where.putAll(rightGroup.params.where);
        }
        Map<String, String> map = new HashMap<>();
        String kName = ColumnUtil.getFieldName(mainKey);
        String vName = ColumnUtil.getFieldName(joinKey);
        map.put(this.params.mainTable + StrUtil.DOT + kName, rightGroup.params.mainTable + StrUtil.DOT + vName);
        this.params.joins.put(rightGroup.params.mainTable, map);
        if (MapUtil.isNotEmpty(rightGroup.params.joins)) {
            Map<String, Integer> m = new HashMap<>();
            for (String key : rightGroup.params.deeps.keySet()) {
                Integer integer = rightGroup.params.deeps.get(key);
                m.put(key, integer + 1);
            }
            this.params.deeps.putAll(m);
            this.params.joins.putAll(rightGroup.params.joins);
        }
        if (MapUtil.isNotEmpty(rightGroup.params.relation)) {
            this.params.relation.putAll(rightGroup.params.relation);
        }
        String className = ColumnUtil.getClassName(joinKey);
        this.params.relation.put(className, "RIGHT JOIN");
        params.deeps.put(className, 1);
        return this;
    }

    public <Z, K> JoinCustomer<T> innerJoinGroup(JoinCustomer<Z> innerGroup
            , SFunction<T, K> mainKey, SFunction<Z, K> joinKey) {
        if (ArrayUtil.isNotEmpty(innerGroup.params.columns)) {
            this.params.columns.addAll(innerGroup.params.columns);
        }
        if (MapUtil.isNotEmpty(innerGroup.params.where)) {
            this.params.where.putAll(innerGroup.params.where);
        }
        Map<String, String> map = new HashMap<>();
        String kName = ColumnUtil.getFieldName(mainKey);
        String vName = ColumnUtil.getFieldName(joinKey);
        map.put(this.params.mainTable + StrUtil.DOT + kName, innerGroup.params.mainTable + StrUtil.DOT + vName);
        this.params.joins.put(innerGroup.params.mainTable, map);
        if (MapUtil.isNotEmpty(innerGroup.params.joins)) {
            Map<String, Integer> m = new HashMap<>();
            for (String key : innerGroup.params.deeps.keySet()) {
                Integer integer = innerGroup.params.deeps.get(key);
                m.put(key, integer + 1);
            }
            this.params.deeps.putAll(m);
            this.params.joins.putAll(innerGroup.params.joins);
        }
        if (MapUtil.isNotEmpty(innerGroup.params.relation)) {
            this.params.relation.putAll(innerGroup.params.relation);
        }
        String className = ColumnUtil.getClassName(joinKey);
        this.params.relation.put(className, "INNER JOIN");
        params.deeps.put(className, 1);
        return this;
    }

    public JoinCustomer<T> select(SFunction<T, ?> field) {
        String fieldName = ColumnUtil.getFieldName(field);
        params.columns.add(params.mainTable + StrUtil.DOT + fieldName);
        return this;
    }

    public JoinCustomer<T> select(List<SFunction<T, ?>> fields) {
        if (CollUtil.isNotEmpty(fields)) {
            for (SFunction<T, ?> field : fields) {
                String fieldName = ColumnUtil.getFieldName(field);
                params.columns.add(params.mainTable + StrUtil.DOT + fieldName);
            }
        }
        return this;
    }

    public JoinCustomer<T> selectAll() {
        Class<T> t = params.obj;
        Field[] fields = t.getDeclaredFields();
        for (Field field : fields) {
            Column annotation = field.getAnnotation(Column.class);
            String columnName = annotation.name();
            params.columns.add(params.mainTable + StrUtil.DOT + columnName);
        }
        return this;
    }

    public <N> JoinCustomer<T> where(SFunction<T, N> column, N o) {
        String fieldName = ColumnUtil.getFieldName(column);
        this.params.where.put(this.params.mainTable + StrUtil.DOT + fieldName, o);
        return this;
    }
}
