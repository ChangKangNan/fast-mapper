package cn.ft.ckn.fastmapper.support.criteria;


import cn.ft.ckn.fastmapper.bean.em.Expression;
import cn.ft.ckn.fastmapper.bean.FastMapperParam;
import cn.hutool.core.util.ArrayUtil;

/**
 * @author ckn
 */
public class DeletedCriteria<T, R> {
    String fieldName;
    Class<T> classObj;
    R returnObj;

    public DeletedCriteria(R r,String fieldName, Class<T> classObj) {
        this.fieldName = fieldName;
        this.classObj = classObj;
        this.returnObj = r;
    }

    void init() {
        FastMapperParam.get().isAnd = Boolean.TRUE;
    }

    public R in(Object... value) {
        Object[] wrap = CriteriaValueUtil.normalizeInValues(value);
        if(ArrayUtil.isEmpty(wrap)){
            init();
            return returnObj;
        }
        FastMapperParam.get().getWhereCondition().add(new FastMapperParam.WhereCondition(this.fieldName, wrap, Expression.In.expression, FastMapperParam.get().isAnd));
        init();
        return returnObj;
    }

    public R notIn(Object... value) {
        Object[] wrap = CriteriaValueUtil.normalizeInValues(value);
        if(ArrayUtil.isEmpty(wrap)){
            init();
            return returnObj;
        }
        FastMapperParam.get().getWhereCondition().add(new FastMapperParam.WhereCondition(this.fieldName, wrap, Expression.NotIn.expression, FastMapperParam.get().isAnd));
        init();
        return returnObj;
    }

    public R equal(Object value) {
        FastMapperParam.get().getWhereCondition().add(new FastMapperParam.WhereCondition(this.fieldName, value, Expression.Equal.expression, FastMapperParam.get().isAnd));
        init();
        return returnObj;
    }

    public R notEqual(Object value) {
        FastMapperParam.get().getWhereCondition().add(new FastMapperParam.WhereCondition(this.fieldName, value, Expression.NotEqual.expression, FastMapperParam.get().isAnd));
        init();
        return returnObj;
    }

    public R greater(Object value) {
        FastMapperParam.get().getWhereCondition().add(new FastMapperParam.WhereCondition(this.fieldName, value, Expression.Greater.expression, FastMapperParam.get().isAnd));
        init();
        return returnObj;
    }

    public R greaterOrEqual(Object value) {
        FastMapperParam.get().getWhereCondition().add(new FastMapperParam.WhereCondition(this.fieldName, value, Expression.GreaterOrEqual.expression, FastMapperParam.get().isAnd));
        init();
        return returnObj;
    }

    public R less(Object value) {
        FastMapperParam.get().getWhereCondition().add(new FastMapperParam.WhereCondition(this.fieldName, value, Expression.Less.expression, FastMapperParam.get().isAnd));
        init();
        return returnObj;
    }

    public R lessOrEqual(Object value) {
        FastMapperParam.get().getWhereCondition().add(new FastMapperParam.WhereCondition(this.fieldName, value, Expression.LessOrEqual.expression, FastMapperParam.get().isAnd));
        init();
        return returnObj;
    }

    public R like(Object value) {
        FastMapperParam.get().getWhereCondition().add(new FastMapperParam.WhereCondition(this.fieldName, value, Expression.Like.expression, FastMapperParam.get().isAnd));
        init();
        return returnObj;
    }

    public R notLike(Object value) {
        FastMapperParam.get().getWhereCondition().add(new FastMapperParam.WhereCondition(this.fieldName, value, Expression.NotLike.expression, FastMapperParam.get().isAnd));
        init();
        return returnObj;
    }

    public R isNull() {
        FastMapperParam.get().getWhereCondition().add(new FastMapperParam.WhereCondition(this.fieldName, null, Expression.IsNull.expression, FastMapperParam.get().isAnd));
        init();
        return returnObj;
    }

    public R isNotNull() {
        FastMapperParam.get().getWhereCondition().add(new FastMapperParam.WhereCondition(this.fieldName, null, Expression.IsNotNull.expression, FastMapperParam.get().isAnd));
        init();
        return returnObj;
    }
    
    public R or() {
        FastMapperParam.get().isAnd = false;
        return returnObj;
    }
}
