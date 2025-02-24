package cn.ft.ckn.fastmapper.support.dao;

import cn.ft.ckn.fastmapper.bean.FastMapperParam;
import cn.ft.ckn.fastmapper.bean.FastTableMapper;
import cn.ft.ckn.fastmapper.support.dao.jdbc.DataSourceConnection;
import cn.hutool.core.collection.ListUtil;

import java.util.List;

/**
 * @author ckn
 */
public class InsertDao<T,R> extends BaseDao<R>{
    private Class<R> r;
    private final DaoActuator<T> daoActuator;

    public InsertDao(Class<R> r,Class<T> classObj) {
        FastTableMapper.init(classObj);
        this.r=r;
        daoActuator = DataSourceConnection.getDaoActuator();
    }

    public T insert(T t) {
        FastMapperParam.get().setInsertList(ListUtil.of(t));
        return daoActuator.insert().get(0);
    }

    public List<T> insertBatch(List<T> collection) {
        FastMapperParam.get().setInsertList((List<Object>) collection);
        return daoActuator.insert();
    }
}
