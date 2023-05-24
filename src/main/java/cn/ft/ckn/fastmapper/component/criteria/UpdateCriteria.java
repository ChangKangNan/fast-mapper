package cn.ft.ckn.fastmapper.component.criteria;

import cn.ft.ckn.fastmapper.bean.Expression;
import cn.ft.ckn.fastmapper.bean.FastMapperParam;
import lombok.SneakyThrows;

/**
 * @author ckn
 */
public class UpdateCriteria<T, R> {
    FastMapperParam FastMapperParam;
    String fieldName;
    Class<T> classObj;
    Class<R> returnObj;

    public UpdateCriteria(FastMapperParam FastMapperParam, String fieldName, Class<T> classObj, Class<R> returnObj) {
        this.FastMapperParam = FastMapperParam;
        this.fieldName = fieldName;
        this.returnObj = returnObj;
        this.classObj = classObj;
    }


    void init() {
        this.FastMapperParam.isAnd = Boolean.TRUE;
    }

    @SneakyThrows
    public R in(Object... value) {
        this.FastMapperParam.whereCondition.add(new FastMapperParam.WhereCondition(this.fieldName, value, Expression.In.expression, FastMapperParam.isAnd));
        init();
        return returnObj.getDeclaredConstructor(FastMapperParam.class).newInstance(FastMapperParam);
    }

    @SneakyThrows
    public R notIn(Object... value) {
        this.FastMapperParam.whereCondition.add(new FastMapperParam.WhereCondition(this.fieldName, value, Expression.NotIn.expression, FastMapperParam.isAnd));
        init();
        return returnObj.getDeclaredConstructor(FastMapperParam.class).newInstance(FastMapperParam);
    }

    @SneakyThrows
    public R equal(Object value) {
        this.FastMapperParam.whereCondition.add(new FastMapperParam.WhereCondition(this.fieldName, value, Expression.Equal.expression, FastMapperParam.isAnd));
        init();
        return returnObj.getDeclaredConstructor(FastMapperParam.class).newInstance(FastMapperParam);
    }

    @SneakyThrows
    public R notEqual(Object value) {
        this.FastMapperParam.whereCondition.add(new FastMapperParam.WhereCondition(this.fieldName, value, Expression.NotEqual.expression, FastMapperParam.isAnd));
        init();
        return returnObj.getDeclaredConstructor(FastMapperParam.class).newInstance(FastMapperParam);
    }

    @SneakyThrows
    public R greater(Object value) {
        this.FastMapperParam.whereCondition.add(new FastMapperParam.WhereCondition(this.fieldName, value, Expression.Greater.expression, FastMapperParam.isAnd));
        init();
        return returnObj.getDeclaredConstructor(FastMapperParam.class).newInstance(FastMapperParam);
    }

    @SneakyThrows
    public R greaterOrEqual(Object value) {
        this.FastMapperParam.whereCondition.add(new FastMapperParam.WhereCondition(this.fieldName, value, Expression.GreaterOrEqual.expression, FastMapperParam.isAnd));
        init();
        return returnObj.getDeclaredConstructor(FastMapperParam.class).newInstance(FastMapperParam);
    }

    @SneakyThrows
    public R less(Object value) {
        this.FastMapperParam.whereCondition.add(new FastMapperParam.WhereCondition(this.fieldName, value, Expression.Less.expression, FastMapperParam.isAnd));
        init();
        return returnObj.getDeclaredConstructor(FastMapperParam.class).newInstance(FastMapperParam);
    }

    @SneakyThrows
    public R lessOrEqual(Object value) {
        this.FastMapperParam.whereCondition.add(new FastMapperParam.WhereCondition(this.fieldName, value, Expression.LessOrEqual.expression, FastMapperParam.isAnd));
        init();
        return returnObj.getDeclaredConstructor(FastMapperParam.class).newInstance(FastMapperParam);
    }

    @SneakyThrows
    public R like(Object value) {
        this.FastMapperParam.whereCondition.add(new FastMapperParam.WhereCondition(this.fieldName, value, Expression.Like.expression, FastMapperParam.isAnd));
        init();
        return returnObj.getDeclaredConstructor(FastMapperParam.class).newInstance(FastMapperParam);
    }

    @SneakyThrows
    public R notLike(Object value) {
        this.FastMapperParam.whereCondition.add(new FastMapperParam.WhereCondition(this.fieldName, value, Expression.NotLike.expression, FastMapperParam.isAnd));
        init();
        return returnObj.getDeclaredConstructor(FastMapperParam.class).newInstance(FastMapperParam);
    }

    @SneakyThrows
    public R isNull() {
        this.FastMapperParam.whereCondition.add(new FastMapperParam.WhereCondition(this.fieldName, null, Expression.IsNull.expression, FastMapperParam.isAnd));
        init();
        return returnObj.getDeclaredConstructor(FastMapperParam.class).newInstance(FastMapperParam);
    }

    @SneakyThrows
    public R isNotNull() {
        this.FastMapperParam.whereCondition.add(new FastMapperParam.WhereCondition(this.fieldName, null, Expression.IsNotNull.expression, FastMapperParam.isAnd));
        init();
        return returnObj.getDeclaredConstructor(FastMapperParam.class).newInstance(FastMapperParam);
    }
}
