package cn.ft.ckn.fastmapper.join;

import cn.ft.ckn.fastmapper.component.PageInfo;
import cn.ft.ckn.fastmapper.component.SFunction;
import cn.ft.ckn.fastmapper.util.ColumnUtil;
import cn.hutool.core.util.StrUtil;

import java.util.List;
import java.util.Map;

/**
 * @author ckn
 * @date 2022/8/11
 */
public class WhereJoin<T,R> {
    private final JoinParams params;

    public WhereJoin(JoinParams params) {
        this.params = params;
    }

    public  WhereJoin<T,R> where(SFunction<R, ?> column, Object o) {
        String fieldName = ColumnUtil.getFieldName(column);
        String className = ColumnUtil.getClassName(column);
        this.params.where.put(className + StrUtil.DOT + fieldName, o);
        return this;
    }

    public <L> FieldJoin<T,L> leftJoin(Class<L> joinClass, SFunction<T, ?> mainKey, SFunction<L, ?> joinKey){
        return new JoinCustomer<T>(params).leftJoin(joinClass,mainKey,joinKey);
    }
    public <R> FieldJoin<T,R> rightJoin(Class<R> joinClass, SFunction<T, ?> mainKey, SFunction<R, ?> joinKey) {
        return new JoinCustomer<T>(params).leftJoin(joinClass,mainKey,joinKey);
    }
    public <I> FieldJoin<T,I> innerJoin(Class<I> joinClass , SFunction<T, ?> mainKey, SFunction<I, ?> joinKey) {
        return new JoinCustomer<T>(params).leftJoin(joinClass,mainKey,joinKey);
    }
    public <X> JoinCustomer<T> leftJoinGroup(JoinCustomer<X> leftGroup
            , SFunction<T, ?> mainKey, SFunction<X, ?> joinKey) {
        return new JoinCustomer<T>(params).innerJoinGroup(leftGroup,mainKey,joinKey);
    }
    public <Y> JoinCustomer<T> rightJoinGroup(JoinCustomer<Y> rightGroup
            , SFunction<T, ?> mainKey, SFunction<Y, ?> joinKey){
        return new JoinCustomer<T>(params).innerJoinGroup(rightGroup,mainKey,joinKey);
    }

    public <Z> JoinCustomer<T> innerJoinGroup(JoinCustomer<Z> innerGroup
            , SFunction<T, ?> mainKey, SFunction<Z, ?> joinKey){
        return new JoinCustomer<T>(params).innerJoinGroup(innerGroup,mainKey,joinKey);
    }


    public PageInfo<Map<String, Object>> findPage(Integer pageNumber, Integer pageSize){
        return new JoinCustomer<T>(params).findPage(pageNumber,pageSize);
    }

    public  <R> List<R> findAll(Class<R> returnObj){
        return new JoinCustomer<T>(params).findAll(returnObj);
    }
}
