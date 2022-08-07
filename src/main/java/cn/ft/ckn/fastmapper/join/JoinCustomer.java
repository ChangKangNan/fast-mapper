package cn.ft.ckn.fastmapper.join;

import cn.ft.ckn.fastmapper.component.SFunction;
import cn.ft.ckn.fastmapper.util.ColumnUtil;
import cn.hutool.core.util.StrUtil;

import javax.persistence.Table;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JoinCustomer<T> {
    Class<T> mainClass;
    Map<String,Object> where;
    String mainTable;
    @SafeVarargs
    public JoinCustomer(Class<T> main, SFunction<T, ?>... fields) {
        where=new HashMap<>();
        this.mainClass = main;
        Table mainClassAnnotation = mainClass.getAnnotation(Table.class);
        mainTable=mainClassAnnotation.name();
    }

    public JoinCustomer<T> where(Map<SFunction<T, ?>, Object> where) {
        if(where !=null && where.size()>0){
            for (SFunction<T, ?> function : where.keySet()) {
                Object o = where.get(function);
                String fieldName = ColumnUtil.getFieldName(function);
                this.where.put(mainTable + StrUtil.DOT + fieldName,o);
            }
        }
        return this;
    }

    private void where(String tableName, Map<String, Object> linkWhere, List<String> fields,String mark) {

    }

    public <L> JoinCustomer<T> leftJoin(Class<L> joinClass, Map<SFunction<T, ?>, SFunction<L, ?>> link, SFunction<L, ?>... fields) {
        return this;
    }

    public <R> JoinCustomer<T> rightJoin(Class<R> joinClass, Map<SFunction<T, ?>, SFunction<R, ?>> link, SFunction<R, ?>... fields) {
        return this;
    }

    public <I> JoinCustomer<T> innerJoin(Class<I> joinClass, Map<SFunction<T, ?>, SFunction<I, ?>> link, SFunction<I, ?>... fields) {
        return this;
    }

    public void query() {
    }


}
