package cn.ft.ckn.fastmapper.component.dao;

import cn.ft.ckn.fastmapper.annotation.Pager;
import cn.ft.ckn.fastmapper.bean.DaoActuator;
import cn.ft.ckn.fastmapper.bean.SearchParam;
import cn.ft.ckn.fastmapper.bean.TableMapper;
import cn.ft.ckn.fastmapper.component.dao.jdbc.DataSourceConnection;
import cn.ft.ckn.fastmapper.aspect.MapperActuatorAspect;
import cn.hutool.aop.ProxyUtil;

import java.util.List;

/**
 * @author ckn
 */
public class SelectDao<T,R> implements Pager<R> {
    private final Class<T> classObj;
    private R r;
    private final DaoActuator<T> daoActuator;

    public SelectDao(R r, Class<T> classObj) {
        TableMapper.init(classObj);
        this.classObj = classObj;
        this.r=r;
        this.daoActuator = ProxyUtil.proxy(DataSourceConnection.getDaoActuator(), MapperActuatorAspect.class);
    }

    public T one() {
        return daoActuator.select().get(0);
    }

    public int count(){
        return daoActuator.count();
    }

    public List<T> list() {
       return daoActuator.select();
    }

    public R page(Integer page, Integer pageSize) {
        SearchParam.get().setOpenPage(true);
        SearchParam.get().setPage(page);
        SearchParam.get().setPageSize(pageSize);
        return r;
    }

    public R or() {
        SearchParam.get().isAnd = false;
        return r;
    }

}
