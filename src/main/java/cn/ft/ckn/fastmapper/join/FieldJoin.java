package cn.ft.ckn.fastmapper.join;

import cn.ft.ckn.fastmapper.component.PageInfo;
import cn.ft.ckn.fastmapper.component.SFunction;
import cn.ft.ckn.fastmapper.util.ColumnUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;

import javax.persistence.Column;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * @author ckn
 * @date 2022/8/11
 */
public class FieldJoin<T, R> {
    private final JoinParams params;

    public FieldJoin(JoinParams params) {
        this.params = params;
    }

    public WhereJoin<T, R> select(SFunction<R, ?> field) {
        String fieldName = ColumnUtil.getFieldName(field);
        String className = ColumnUtil.getClassName(field);
        params.columns.add(className + StrUtil.DOT + fieldName);
        return new WhereJoin<>(params);
    }

    public WhereJoin<T, R> select(List<SFunction<R, ?>> fields) {
        if (CollUtil.isNotEmpty(fields)) {
            for (SFunction<R, ?> field : fields) {
                String fieldName = ColumnUtil.getFieldName(field);
                String className = ColumnUtil.getClassName(field);
                params.columns.add(className + StrUtil.DOT + fieldName);
            }
        }
        return new WhereJoin<>(params);
    }

    public <V> WhereJoin<T, R> where(SFunction<R, V> field, V o) {
        String fieldName = ColumnUtil.getFieldName(field);
        String className = ColumnUtil.getClassName(field);
        this.params.where.put(className + StrUtil.DOT + fieldName, o);
        return new WhereJoin<>(params);
    }

    public <L, K> FieldJoin<T, L> leftJoin(Class<L> joinClass, SFunction<T, K> mainKey, SFunction<L, K> joinKey) {
        return new JoinCustomer<T>(params).leftJoin(joinClass, mainKey, joinKey);
    }

    public <R, K> FieldJoin<T, R> rightJoin(Class<R> joinClass, SFunction<T, K> mainKey, SFunction<R, K> joinKey) {
        return new JoinCustomer<T>(params).leftJoin(joinClass, mainKey, joinKey);
    }

    public <I, K> FieldJoin<T, I> innerJoin(Class<I> joinClass, SFunction<T, K> mainKey, SFunction<I, K> joinKey) {
        return new JoinCustomer<T>(params).leftJoin(joinClass, mainKey, joinKey);
    }

    public PageInfo<Map<String, Object>> findPage(Integer pageNumber, Integer pageSize) {
        return new JoinCustomer<T>(params).findPage(pageNumber, pageSize);
    }

    public <X, K> JoinCustomer<T> leftJoinGroup(JoinCustomer<X> leftGroup
            , SFunction<T, K> mainKey, SFunction<X, K> joinKey) {
        return new JoinCustomer<T>(params).innerJoinGroup(leftGroup, mainKey, joinKey);
    }

    public <Y, K> JoinCustomer<T> rightJoinGroup(JoinCustomer<Y> rightGroup
            , SFunction<T, K> mainKey, SFunction<Y, K> joinKey) {
        return new JoinCustomer<T>(params).innerJoinGroup(rightGroup, mainKey, joinKey);
    }

    public <Z, K> JoinCustomer<T> innerJoinGroup(JoinCustomer<Z> innerGroup
            , SFunction<T, K> mainKey, SFunction<Z, K> joinKey) {
        return new JoinCustomer<T>(params).innerJoinGroup(innerGroup, mainKey, joinKey);
    }

    public <R> List<R> findAll(Class<R> returnObj) {
        return new JoinCustomer<T>(params).findAll(returnObj);
    }

    public JoinCustomer<T> getObj() {
        return new JoinCustomer<T>(params);
    }

    /**
     * case: tb.k=:key   map:{key:"value"}
     * @param sql
     * @param parameters
     * @return
     */
    public FieldJoin<T, R> lastWhere(String sql,Map<String,Object> parameters) {
        params.lastSQL=sql;
        params.lastWhereParameters=parameters;
        return this;
    }

    public WhereJoin<T, R> selectAll() {
        Class<R> t = params.obj;
        Table table = t.getAnnotation(Table.class);
        String name = table.name();
        Field[] fields = t.getDeclaredFields();
        for (Field field : fields) {
            Column annotation = field.getAnnotation(Column.class);
            String columnName = annotation.name();
            params.columns.add(name + StrUtil.DOT + columnName);
        }
        return new WhereJoin<>(params);
    }
}
