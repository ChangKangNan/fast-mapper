package cn.ft.ckn.fastmapper.component.dao;

import cn.ft.ckn.fastmapper.bean.DaoActuator;
import cn.ft.ckn.fastmapper.bean.SearchParam;
import cn.ft.ckn.fastmapper.bean.TableMapper;
import cn.ft.ckn.fastmapper.component.dao.jdbc.DataSourceConnection;
import cn.ft.ckn.fastmapper.aspect.MapperActuatorAspect;
import cn.hutool.aop.ProxyUtil;
import cn.hutool.core.collection.ListUtil;

import javax.sql.DataSource;
import java.util.List;

/**
 * @author ckn
 */
public class InsertDao<T,R>{
    private Class<R> r;
    private final DaoActuator<T> daoActuator;

    public InsertDao(Class<R> r,Class<T> classObj) {
        TableMapper.init(classObj);
        this.r=r;
        daoActuator = ProxyUtil.proxy(DataSourceConnection.getDaoActuator(), MapperActuatorAspect.class);
    }

    public T insert(T t) {
        SearchParam.get().setInsertList(ListUtil.of(t));
        return daoActuator.insert().get(0);
    }

    public List<T> insertBatch(List<T> collection) {
        SearchParam.get().setInsertList((List<Object>) collection);
        return daoActuator.insert();
    }

    public R setSlaveDataSource(DataSource dataSource) {
        DataSourceConnection.setSlaveDataSource(dataSource);
        return (R)this;
    }

    public R setSalveDataSource(DataSource dataSource){
        SearchParam searchParam = SearchParam.get();
        searchParam.setMaster(false);
        DataSourceConnection.setSlaveDataSource(dataSource);
        return (R)this;
    }
}
