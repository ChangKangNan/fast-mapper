package cn.ft.ckn.fastmapper.component.criteria;


import cn.ft.ckn.fastmapper.bean.Expression;
import cn.ft.ckn.fastmapper.bean.SearchParam;
import cn.hutool.core.util.ArrayUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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
        SearchParam.get().isAnd = Boolean.TRUE;
    }

    public R in(Object... value) {
        if (value == null || ArrayUtil.isEmpty(value)) {
            init();
            return returnObj;
        }
        List<Object> values = new ArrayList<>();
        for (Object o : value) {
            if (o instanceof Collection) {
                values.addAll((Collection) o);
            } else {
                values.add(o);
            }
        }
        values = values.stream().distinct().collect(Collectors.toList());
        Object[] wrap = ArrayUtil.wrap(values.toArray());
        if(ArrayUtil.isEmpty(wrap)){
            init();
            return returnObj;
        }
        SearchParam.get().getWhereCondition().add(new SearchParam.WhereCondition(this.fieldName, wrap, Expression.NotIn.expression, SearchParam.get().isAnd));
        init();
        return returnObj;
    }

    public R notIn(Object... value) {
        if (value == null || ArrayUtil.isEmpty(value)) {
            init();
            return returnObj;
        }
        List<Object> values = new ArrayList<>();
        for (Object o : value) {
            if (o instanceof Collection) {
                values.addAll((Collection) o);
            } else {
                values.add(o);
            }
        }
        values = values.stream().distinct().collect(Collectors.toList());
        Object[] wrap = ArrayUtil.wrap(values.toArray());
        if(ArrayUtil.isEmpty(wrap)){
            init();
            return returnObj;
        }
        SearchParam.get().getWhereCondition().add(new SearchParam.WhereCondition(this.fieldName, wrap, Expression.NotIn.expression, SearchParam.get().isAnd));
        init();
        return returnObj;
    }

    public R equal(Object value) {
        SearchParam.get().getWhereCondition().add(new SearchParam.WhereCondition(this.fieldName, value, Expression.Equal.expression, SearchParam.get().isAnd));
        init();
        return returnObj;
    }

    public R notEqual(Object value) {
        SearchParam.get().getWhereCondition().add(new SearchParam.WhereCondition(this.fieldName, value, Expression.NotEqual.expression, SearchParam.get().isAnd));
        init();
        return returnObj;
    }

    public R greater(Object value) {
        SearchParam.get().getWhereCondition().add(new SearchParam.WhereCondition(this.fieldName, value, Expression.Greater.expression, SearchParam.get().isAnd));
        init();
        return returnObj;
    }

    public R greaterOrEqual(Object value) {
        SearchParam.get().getWhereCondition().add(new SearchParam.WhereCondition(this.fieldName, value, Expression.GreaterOrEqual.expression, SearchParam.get().isAnd));
        init();
        return returnObj;
    }

    public R less(Object value) {
        SearchParam.get().getWhereCondition().add(new SearchParam.WhereCondition(this.fieldName, value, Expression.Less.expression, SearchParam.get().isAnd));
        init();
        return returnObj;
    }

    public R lessOrEqual(Object value) {
        SearchParam.get().getWhereCondition().add(new SearchParam.WhereCondition(this.fieldName, value, Expression.LessOrEqual.expression, SearchParam.get().isAnd));
        init();
        return returnObj;
    }

    public R like(Object value) {
        SearchParam.get().getWhereCondition().add(new SearchParam.WhereCondition(this.fieldName, value, Expression.Like.expression, SearchParam.get().isAnd));
        init();
        return returnObj;
    }

    public R notLike(Object value) {
        SearchParam.get().getWhereCondition().add(new SearchParam.WhereCondition(this.fieldName, value, Expression.NotLike.expression, SearchParam.get().isAnd));
        init();
        return returnObj;
    }

    public R isNull() {
        SearchParam.get().getWhereCondition().add(new SearchParam.WhereCondition(this.fieldName, null, Expression.IsNull.expression, SearchParam.get().isAnd));
        init();
        return returnObj;
    }

    public R isNotNull() {
        SearchParam.get().getWhereCondition().add(new SearchParam.WhereCondition(this.fieldName, null, Expression.IsNotNull.expression, SearchParam.get().isAnd));
        init();
        return returnObj;
    }
    
    public R or() {
        SearchParam.get().isAnd = false;
        return returnObj;
    }
}
