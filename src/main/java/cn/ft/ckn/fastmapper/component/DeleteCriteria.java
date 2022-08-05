package cn.ft.ckn.fastmapper.component;

/**
 * @author ckn
 * @date 2022/7/28
 */
public class DeleteCriteria<T> {
    SplicingParam splicingParam;
    public String currentFieldName;

    public DeleteCriteria(SplicingParam splicingParam, String fieldName) {
        this.splicingParam = splicingParam;
        this.currentFieldName = fieldName;
    }

    public DeleteCriteria<T> in(Object... value) {
        this.splicingParam.whereCondition.add(new SplicingParam.WhereCondition(this.currentFieldName, value, Expression.In.expression));
        return this;
    }

    public DeleteCriteria<T> notIn(Object... value) {
        this.splicingParam.whereCondition.add(new SplicingParam.WhereCondition(this.currentFieldName, value, Expression.NotIn.expression));
        return this;
    }

    public DeleteCriteria<T> equal(Object value) {
        this.splicingParam.whereCondition.add(new SplicingParam.WhereCondition(this.currentFieldName, value, Expression.Equal.expression));
        return this;
    }

    public DeleteCriteria<T> notEqual(Object value) {
        this.splicingParam.whereCondition.add(new SplicingParam.WhereCondition(this.currentFieldName, value, Expression.NotEqual.expression));
        return this;
    }

    public DeleteCriteria<T> greater(Object value) {
        this.splicingParam.whereCondition.add(new SplicingParam.WhereCondition(this.currentFieldName, value, Expression.Greater.expression));
        return this;
    }

    public DeleteCriteria<T> greaterOrEqual(Object value) {
        this.splicingParam.whereCondition.add(new SplicingParam.WhereCondition(this.currentFieldName, value, Expression.GreaterOrEqual.expression));
        return this;
    }

    public DeleteCriteria<T> less(Object value) {
        this.splicingParam.whereCondition.add(new SplicingParam.WhereCondition(this.currentFieldName, value, Expression.Less.expression));
        return this;
    }

    public DeleteCriteria<T> lessOrEqual(Object value) {
        this.splicingParam.whereCondition.add(new SplicingParam.WhereCondition(this.currentFieldName, value, Expression.LessOrEqual.expression));
        return this;
    }

    public DeleteCriteria<T> like(Object value) {
        this.splicingParam.whereCondition.add(new SplicingParam.WhereCondition(this.currentFieldName, value, Expression.Like.expression));
        return this;
    }

    public DeleteCriteria<T> notLike(Object value) {
        this.splicingParam.whereCondition.add(new SplicingParam.WhereCondition(this.currentFieldName, value, Expression.NotLike.expression));
        return this;
    }

    public DeleteCriteria<T> isNull() {
        this.splicingParam.whereCondition.add(new SplicingParam.WhereCondition(this.currentFieldName, null, Expression.IsNull.expression));
        return this;
    }

    public DeleteCriteria<T> isNotNull() {
        this.splicingParam.whereCondition.add(new SplicingParam.WhereCondition(this.currentFieldName, null, Expression.IsNotNull.expression));
        return this;
    }

    public EasyDao<T> dao() {
        return null;
    }
}
