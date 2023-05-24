package cn.ft.ckn.fastmapper.component.criteria;


import cn.ft.ckn.fastmapper.bean.Expression;
import cn.ft.ckn.fastmapper.bean.FastMapperParam;
import cn.ft.ckn.fastmapper.constants.Operation;
import cn.hutool.core.bean.BeanUtil;

/**
 * @author ckn
 */
public class DeletedCriteria<T, R> {
    FastMapperParam fastMapperParam;
    String fieldName;
    Class<T> classObj;
    R returnObj;

    public DeletedCriteria(R r,String fieldName, Class<T> classObj) {
        this.fieldName = fieldName;
        this.classObj = classObj;
        this.returnObj = r;
        this.fastMapperParam = BeanUtil.getProperty(r, Operation.PARAM);
    }

    void init() {
        this.fastMapperParam.isAnd = Boolean.TRUE;
        BeanUtil.setProperty(returnObj,Operation.PARAM,fastMapperParam);
    }

    public R in(Object... value) {
        this.fastMapperParam.whereCondition.add(new FastMapperParam.WhereCondition(this.fieldName, value, Expression.In.expression, fastMapperParam.isAnd));
        init();
        return returnObj;
    }

    public R notIn(Object... value) {
        this.fastMapperParam.whereCondition.add(new FastMapperParam.WhereCondition(this.fieldName, value, Expression.NotIn.expression, fastMapperParam.isAnd));
        init();
        return returnObj;
    }

    public R equal(Object value) {
        this.fastMapperParam.whereCondition.add(new FastMapperParam.WhereCondition(this.fieldName, value, Expression.Equal.expression, fastMapperParam.isAnd));
        init();
        return returnObj;
    }

    public R notEqual(Object value) {
        this.fastMapperParam.whereCondition.add(new FastMapperParam.WhereCondition(this.fieldName, value, Expression.NotEqual.expression, fastMapperParam.isAnd));
        init();
        return returnObj;
    }

    public R greater(Object value) {
        this.fastMapperParam.whereCondition.add(new FastMapperParam.WhereCondition(this.fieldName, value, Expression.Greater.expression, fastMapperParam.isAnd));
        init();
        return returnObj;
    }

    public R greaterOrEqual(Object value) {
        this.fastMapperParam.whereCondition.add(new FastMapperParam.WhereCondition(this.fieldName, value, Expression.GreaterOrEqual.expression, fastMapperParam.isAnd));
        init();
        return returnObj;
    }

    public R less(Object value) {
        this.fastMapperParam.whereCondition.add(new FastMapperParam.WhereCondition(this.fieldName, value, Expression.Less.expression, fastMapperParam.isAnd));
        init();
        return returnObj;
    }

    public R lessOrEqual(Object value) {
        this.fastMapperParam.whereCondition.add(new FastMapperParam.WhereCondition(this.fieldName, value, Expression.LessOrEqual.expression, fastMapperParam.isAnd));
        init();
        return returnObj;
    }

    public R like(Object value) {
        this.fastMapperParam.whereCondition.add(new FastMapperParam.WhereCondition(this.fieldName, value, Expression.Like.expression, fastMapperParam.isAnd));
        init();
        return returnObj;
    }

    public R notLike(Object value) {
        this.fastMapperParam.whereCondition.add(new FastMapperParam.WhereCondition(this.fieldName, value, Expression.NotLike.expression, fastMapperParam.isAnd));
        init();
        return returnObj;
    }

    public R isNull() {
        this.fastMapperParam.whereCondition.add(new FastMapperParam.WhereCondition(this.fieldName, null, Expression.IsNull.expression, fastMapperParam.isAnd));
        init();
        return returnObj;
    }

    public R isNotNull() {
        this.fastMapperParam.whereCondition.add(new FastMapperParam.WhereCondition(this.fieldName, null, Expression.IsNotNull.expression, fastMapperParam.isAnd));
        init();
        return returnObj;
    }

}
