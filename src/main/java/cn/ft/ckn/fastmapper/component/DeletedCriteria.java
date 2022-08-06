package cn.ft.ckn.fastmapper.component;


/**
 * @author ckn
 * @date 2022/6/8
 */
public class DeletedCriteria<T,R>{
    SplicingParam splicingParam;
    private String currentFieldName;
    Class<T> classObj;
    Class<R> returnObj;

    public DeletedCriteria(SplicingParam splicingParam, String fieldName, Class<T> classObj, Class<R> returnObj) {
        this.splicingParam=splicingParam;
        this.currentFieldName = fieldName;
        this.returnObj = returnObj;
        this.classObj=classObj;
    }


    void init(){
        this.splicingParam.isAnd=Boolean.TRUE;
    }
    public R in(Object... value) {
        this.splicingParam.whereCondition.add(new SplicingParam.WhereCondition(this.currentFieldName, value, Expression.In.expression,splicingParam.isAnd));
        init();
        try {
            return returnObj.getDeclaredConstructor(SplicingParam.class).newInstance(splicingParam);
        } catch (Exception e) {
            return null;
        }
    }

    public R notIn(Object... value) {
        this.splicingParam.whereCondition.add(new SplicingParam.WhereCondition(this.currentFieldName, value, Expression.NotIn.expression,splicingParam.isAnd));
        init();
        try {
            return returnObj.getDeclaredConstructor(SplicingParam.class).newInstance(splicingParam);
        } catch (Exception e) {
            return null;
        }
    }

    public R equal(Object value) {
        this.splicingParam.whereCondition.add(new SplicingParam.WhereCondition(this.currentFieldName, value, Expression.Equal.expression,splicingParam.isAnd));
        init();
        try {
            return returnObj.getDeclaredConstructor(SplicingParam.class).newInstance(splicingParam);
        } catch (Exception e) {
            return null;
        }
    }

    public R notEqual(Object value) {
        this.splicingParam.whereCondition.add(new SplicingParam.WhereCondition(this.currentFieldName, value, Expression.NotEqual.expression,splicingParam.isAnd));
        init();
        try {
            return returnObj.getDeclaredConstructor(SplicingParam.class).newInstance(splicingParam);
        } catch (Exception e) {
            return null;
        }
    }

    public R greater(Object value) {
        this.splicingParam.whereCondition.add(new SplicingParam.WhereCondition(this.currentFieldName, value, Expression.Greater.expression,splicingParam.isAnd));
        init();
        try {
            return returnObj.getDeclaredConstructor(SplicingParam.class).newInstance(splicingParam);
        } catch (Exception e) {
            return null;
        }
    }

    public R greaterOrEqual(Object value) {
        this.splicingParam.whereCondition.add(new SplicingParam.WhereCondition(this.currentFieldName, value, Expression.GreaterOrEqual.expression,splicingParam.isAnd));
        init();
        try {
            return returnObj.getDeclaredConstructor(SplicingParam.class).newInstance(splicingParam);
        } catch (Exception e) {
            return null;
        }
    }

    public R less(Object value) {
        this.splicingParam.whereCondition.add(new SplicingParam.WhereCondition(this.currentFieldName, value, Expression.Less.expression,splicingParam.isAnd));
        init();
        try {
            return returnObj.getDeclaredConstructor(SplicingParam.class).newInstance(splicingParam);
        } catch (Exception e) {
            return null;
        }
    }

    public R lessOrEqual(Object value) {
        this.splicingParam.whereCondition.add(new SplicingParam.WhereCondition(this.currentFieldName, value, Expression.LessOrEqual.expression,splicingParam.isAnd));
        init();
        try {
            return returnObj.getDeclaredConstructor(SplicingParam.class).newInstance(splicingParam);
        } catch (Exception e) {
            return null;
        }
    }

    public R like(Object value) {
        this.splicingParam.whereCondition.add(new SplicingParam.WhereCondition(this.currentFieldName, value, Expression.Like.expression,splicingParam.isAnd));
        init();
        try {
            return returnObj.getDeclaredConstructor(SplicingParam.class).newInstance(splicingParam);
        } catch (Exception e) {
            return null;
        }
    }

    public R notLike(Object value) {
        this.splicingParam.whereCondition.add(new SplicingParam.WhereCondition(this.currentFieldName, value, Expression.NotLike.expression,splicingParam.isAnd));
        init();
        try {
            return returnObj.getDeclaredConstructor(SplicingParam.class).newInstance(splicingParam);
        } catch (Exception e) {
            return null;
        }
    }

    public R isNull() {
        this.splicingParam.whereCondition.add(new SplicingParam.WhereCondition(this.currentFieldName, null, Expression.IsNull.expression,splicingParam.isAnd));
        init();
        try {
            return returnObj.getDeclaredConstructor(SplicingParam.class).newInstance(splicingParam);
        } catch (Exception e) {
            return null;
        }
    }

    public R isNotNull() {
        this.splicingParam.whereCondition.add(new SplicingParam.WhereCondition(this.currentFieldName, null, Expression.IsNotNull.expression,splicingParam.isAnd));
        init();
        try {
            return returnObj.getDeclaredConstructor(SplicingParam.class).newInstance(splicingParam);
        } catch (Exception e) {
            return null;
        }
    }

}
