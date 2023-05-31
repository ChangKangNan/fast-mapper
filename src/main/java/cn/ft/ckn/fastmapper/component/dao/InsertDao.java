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
public class InsertDao<T, R> {
    private R r;
    private final DaoActuator<T> daoActuator;

    public InsertDao(R r,Class<T> classObj) {
        TableMapper.init(classObj);
        this.r = r;
        daoActuator = ProxyUtil.proxy(DataSourceConnection.getDaoActuator(), MapperActuatorAspect.class);
    }

    public T insert(T t) {
        SearchParam.get().setInsertList(ListUtil.of(t));
        return daoActuator.insert().get(0);
    }

    public void insertBatch(List<T> collection) {
        SearchParam.get().setInsertList((List<Object>) collection);
        daoActuator.insert();
    }

    public R setSlaveDataSource(DataSource dataSource) {
        DataSourceConnection.setSlaveDataSource(dataSource);
        return r;
    }
}
