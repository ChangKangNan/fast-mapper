package cn.ft.ckn.fastmapper.component;

/**
 * @author ckn
 * @date 2022/7/28
 */
public class UpdateCriteria<T, R> {
    SplicingParam splicingParam;
    private final String currentFieldName;
    Class<T> classObj;
    Class<R> returnObj;

    public UpdateCriteria(SplicingParam splicingParam, String fieldName, Class<T> classObj, Class<R> returnObj) {
        this.splicingParam = splicingParam;
        this.currentFieldName = fieldName;
        this.returnObj = returnObj;
        this.classObj = classObj;
    }


    public R in(Object... value) {
        try {
            this.splicingParam.whereCondition.add(new SplicingParam.WhereCondition(this.currentFieldName, value, Expression.In.expression));
            return returnObj.getDeclaredConstructor(SplicingParam.class).newInstance(splicingParam);
        } catch (Exception e) {
            return null;
        }
    }

    public R notIn(Object... value) {
        this.splicingParam.whereCondition.add(new SplicingParam.WhereCondition(this.currentFieldName, value, Expression.NotIn.expression));
        try {
            return returnObj.getDeclaredConstructor(SplicingParam.class).newInstance(splicingParam);
        } catch (Exception e) {
            return null;
        }
    }

    public R equal(Object value) {
        this.splicingParam.whereCondition.add(new SplicingParam.WhereCondition(this.currentFieldName, value, Expression.Equal.expression));
        try {
            return returnObj.getDeclaredConstructor(SplicingParam.class).newInstance(splicingParam);
        } catch (Exception e) {
            return null;
        }
    }

    public R notEqual(Object value) {
        this.splicingParam.whereCondition.add(new SplicingParam.WhereCondition(this.currentFieldName, value, Expression.NotEqual.expression));
        try {
            return returnObj.getDeclaredConstructor(SplicingParam.class).newInstance(splicingParam);
        } catch (Exception e) {
            return null;
        }
    }

    public R greater(Object value) {
        this.splicingParam.whereCondition.add(new SplicingParam.WhereCondition(this.currentFieldName, value, Expression.Greater.expression));
        try {
            return returnObj.getDeclaredConstructor(SplicingParam.class).newInstance(splicingParam);
        } catch (Exception e) {
            return null;
        }
    }

    public R greaterOrEqual(Object value) {
        this.splicingParam.whereCondition.add(new SplicingParam.WhereCondition(this.currentFieldName, value, Expression.GreaterOrEqual.expression));
        try {
            return returnObj.getDeclaredConstructor(SplicingParam.class).newInstance(splicingParam);
        } catch (Exception e) {
            return null;
        }
    }

    public R less(Object value) {
        this.splicingParam.whereCondition.add(new SplicingParam.WhereCondition(this.currentFieldName, value, Expression.Less.expression));
        try {
            return returnObj.getDeclaredConstructor(SplicingParam.class).newInstance(splicingParam);
        } catch (Exception e) {
            return null;
        }
    }

    public R lessOrEqual(Object value) {
        this.splicingParam.whereCondition.add(new SplicingParam.WhereCondition(this.currentFieldName, value, Expression.LessOrEqual.expression));
        try {
            return returnObj.getDeclaredConstructor(SplicingParam.class).newInstance(splicingParam);
        } catch (Exception e) {
            return null;
        }
    }

    public R like(Object value) {
        this.splicingParam.whereCondition.add(new SplicingParam.WhereCondition(this.currentFieldName, value, Expression.Like.expression));
        try {
            return returnObj.getDeclaredConstructor(SplicingParam.class).newInstance(splicingParam);
        } catch (Exception e) {
            return null;
        }
    }

    public R notLike(Object value) {
        this.splicingParam.whereCondition.add(new SplicingParam.WhereCondition(this.currentFieldName, value, Expression.NotLike.expression));
        try {
            return returnObj.getDeclaredConstructor(SplicingParam.class).newInstance(splicingParam);
        } catch (Exception e) {
            return null;
        }
    }

    public R isNull() {
        this.splicingParam.whereCondition.add(new SplicingParam.WhereCondition(this.currentFieldName, null, Expression.IsNull.expression));
        try {
            return returnObj.getDeclaredConstructor(SplicingParam.class).newInstance(splicingParam);
        } catch (Exception e) {
            return null;
        }
    }

    public R isNotNull() {
        this.splicingParam.whereCondition.add(new SplicingParam.WhereCondition(this.currentFieldName, null, Expression.IsNotNull.expression));
        try {
            return returnObj.getDeclaredConstructor(SplicingParam.class).newInstance(splicingParam);
        } catch (Exception e) {
            return null;
        }
    }
}
