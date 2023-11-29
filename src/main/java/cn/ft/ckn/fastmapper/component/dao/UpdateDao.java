package cn.ft.ckn.fastmapper.component.dao;

import cn.ft.ckn.fastmapper.bean.*;
import cn.ft.ckn.fastmapper.component.dao.jdbc.DataSourceConnection;
import cn.ft.ckn.fastmapper.component.dao.set.UpdateValue;
import cn.ft.ckn.fastmapper.util.ValueUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ReflectUtil;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ckn
 */
public class UpdateDao<T, R>{
    private final Class<T> classObj;
    private final Class<R> r;
    private final DaoActuator<T> daoActuator;

    public UpdateDao(Class<R> r, Class<T> classObj) {
        TableMapper.init(classObj);
        this.classObj = classObj;
        this.r = r;
        this.daoActuator = DataSourceConnection.getDaoActuator();
    }

    public UpdateDao(Class<T> obj) {
        classObj = obj;
        this.r = null;
        this.daoActuator = DataSourceConnection.getDaoActuator();
    }

    public UpdateValue<T, R> value() {
        return new UpdateValue<>((R)this,classObj,daoActuator);
    }

    public Integer updateByPrimaryKey(T t) {
        Field[] fields = classObj.getDeclaredFields();
        boolean exist = false;
        for (Field field : fields) {
            Id id = field.getAnnotation(Id.class);
            if (id != null) {
                Column fieldAnnotation = field.getAnnotation(Column.class);
                exist = true;
                String pk = fieldAnnotation != null ? fieldAnnotation.name() : field.getName();
                Object value = ReflectUtil.getFieldValue(t, field.getName());
                SearchParam.get().getWhereCondition().add(new SearchParam.WhereCondition(pk, value, Expression.Equal.expression, SearchParam.get().isAnd));
                break;
            }
        }
        if (!exist) {
            return 0;
        }
      return daoActuator.update();
    }

    public Integer updateOverride(T t){
        List<ColumnParam> valueParams = ValueUtil.getColumns(t,classObj);
        if(CollUtil.isNotEmpty(valueParams)){
            for (ColumnParam valueParam : valueParams) {
                SearchParam.get().getUpdateValueList().add(new SearchParam.Value(valueParam.getColumnName(),valueParam.getVal()));
            }
        }
       return daoActuator.update();
    }

    public Integer update(T t) {
        List<ColumnParam> valueParams = ValueUtil.getColumns(t,classObj);
        List<ColumnParam> columnParams = valueParams.stream().filter(ColumnParam::getHaveValue).collect(Collectors.toList());
        if(CollUtil.isNotEmpty(columnParams)){
            for (ColumnParam valueParam : columnParams) {
                SearchParam.get().getUpdateValueList().add(new SearchParam.Value(valueParam.getColumnName(),valueParam.getVal()));
            }
        }
       return daoActuator.update();
    }


    public R or() {
        SearchParam.get().isAnd = false;
        return (R)this;
    }

    public R setSalveDataSource(DataSource dataSource){
        SearchParam searchParam = SearchParam.get();
        searchParam.setMaster(false);
        DataSourceConnection.setSlaveDataSource(dataSource);
        return (R)this;
    }

    public R bracketPrefix() {
        SearchParam.get().setBracket(SearchParam.Bracket.builder().leftIndex(SearchParam.get().getWhereCondition().size()).build());
        return (R)this;
    }

    public R bracketSuffix() {
        List<SearchParam.Bracket> brackets = SearchParam.get().getBrackets();
        for (int i = brackets.size() - 1; i >= 0; i--) {
            if (brackets.get(i).getRightIndex() != null) {
                continue;
            }
            SearchParam.Bracket bracket = brackets.get(i);
            bracket.setRightIndex(SearchParam.get().getWhereCondition().size()-1);
            SearchParam.get().setBracket(bracket, i);
        }
        return (R)this;
    }
}
