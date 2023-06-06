package cn.ft.ckn.fastmapper.join;

import cn.ft.ckn.fastmapper.annotation.SFunction;
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

    public <L,K> FieldJoin<T,L> leftJoin(Class<L> joinClass, SFunction<T, K> mainKey, SFunction<L, K> joinKey){
        return new JoinCustomer<T>(params).leftJoin(joinClass,mainKey,joinKey);
    }
    public <R,K> FieldJoin<T,R> rightJoin(Class<R> joinClass, SFunction<T, K> mainKey, SFunction<R, K> joinKey) {
        return new JoinCustomer<T>(params).leftJoin(joinClass,mainKey,joinKey);
    }
    public <I,K> FieldJoin<T,I> innerJoin(Class<I> joinClass , SFunction<T, K> mainKey, SFunction<I, K> joinKey) {
        return new JoinCustomer<T>(params).leftJoin(joinClass,mainKey,joinKey);
    }
    public <X,K> JoinCustomer<T> leftJoinGroup(JoinCustomer<X> leftGroup
            , SFunction<T, K> mainKey, SFunction<X, K> joinKey) {
        return new JoinCustomer<T>(params).innerJoinGroup(leftGroup,mainKey,joinKey);
    }
    public <Y,K> JoinCustomer<T> rightJoinGroup(JoinCustomer<Y> rightGroup
            , SFunction<T, K> mainKey, SFunction<Y, K> joinKey){
        return new JoinCustomer<T>(params).innerJoinGroup(rightGroup,mainKey,joinKey);
    }

    public <Z,K> JoinCustomer<T> innerJoinGroup(JoinCustomer<Z> innerGroup
            , SFunction<T, K> mainKey, SFunction<Z, K> joinKey){
        return new JoinCustomer<T>(params).innerJoinGroup(innerGroup,mainKey,joinKey);
    }

    /**
     * case: tb.k=:key   map:{key:"value"}
     */
    public JoinCustomer<T> lastWhere(String sql,Map<String,Object> parameters) {
        params.lastSQL=sql;
        params.lastWhereParameters=parameters;
        return new JoinCustomer<T>(params);
    }

    public  <R> List<R> find(Class<R> returnObj){
        return new JoinCustomer<T>(params).find(returnObj);
    }

    public JoinCustomer<T> getObj(){
        return new JoinCustomer<T>(params);
    }
}
