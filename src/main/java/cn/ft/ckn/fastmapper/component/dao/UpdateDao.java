package cn.ft.ckn.fastmapper.component.dao;

import cn.ft.ckn.fastmapper.bean.*;
import cn.ft.ckn.fastmapper.component.dao.jdbc.DataSourceConnection;
import cn.ft.ckn.fastmapper.component.dao.set.UpdateValue;
import cn.ft.ckn.fastmapper.aspect.MapperActuatorAspect;
import cn.ft.ckn.fastmapper.util.ValueUtil;
import cn.hutool.aop.ProxyUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ReflectUtil;

import javax.persistence.Column;
import javax.persistence.Id;
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
        this.daoActuator = ProxyUtil.proxy(DataSourceConnection.getDaoActuator(), MapperActuatorAspect.class);
    }

    public UpdateDao(Class<T> obj) {
        classObj = obj;
        this.r = null;
        this.daoActuator = ProxyUtil.proxy(DataSourceConnection.getDaoActuator(), MapperActuatorAspect.class);
    }

    public UpdateValue<T, R> value() {
        return new UpdateValue<>((R)this,classObj,daoActuator);
    }

    public void updateByPrimaryKey(T t) {
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
            return;
        }
       daoActuator.update();
    }

    public void updateOverride(T t){
        List<ColumnParam> valueParams = ValueUtil.getColumns(t);
        if(CollUtil.isNotEmpty(valueParams)){
            for (ColumnParam valueParam : valueParams) {
                SearchParam.get().getUpdateValueList().add(new SearchParam.Value(valueParam.getColumnName(),valueParam.getVal()));
            }
        }
        daoActuator.update();
    }

    public void update(T t) {
        List<ColumnParam> valueParams = ValueUtil.getColumns(t);
        List<ColumnParam> columnParams = valueParams.stream().filter(ColumnParam::getHaveValue).collect(Collectors.toList());
        if(CollUtil.isNotEmpty(columnParams)){
            for (ColumnParam valueParam : columnParams) {
                SearchParam.get().getUpdateValueList().add(new SearchParam.Value(valueParam.getColumnName(),valueParam.getVal()));
            }
        }
        daoActuator.update();
    }


    public R or() {
        SearchParam.get().isAnd = false;
        return (R)this;
    }
}
