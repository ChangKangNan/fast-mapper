package cn.ft.ckn.fastmapper.support.dao;

import cn.ft.ckn.fastmapper.anno.Pager;
import cn.ft.ckn.fastmapper.bean.FastMapperParam;
import cn.ft.ckn.fastmapper.bean.db.TableMapper;
import cn.ft.ckn.fastmapper.support.dao.jdbc.DataSourceConnection;
import cn.hutool.core.collection.CollUtil;

import java.util.List;

/**
 * @author ckn
 */
public class SelectDao<T,R> extends BaseDao<R> implements Pager<R> {
    private final Class<T> classObj;
    private Class<R> r;
    private final DaoActuator<T> daoActuator;

    public SelectDao(Class<R> r, Class<T> classObj) {
        TableMapper.init(classObj);
        this.classObj = classObj;
        this.r=r;
        this.daoActuator = DataSourceConnection.getDaoActuator();
    }

    public T one() {
        List<T> select = daoActuator.select();
        if(CollUtil.isEmpty(select)){
            return null;
        }
        return select.get(0);
    }

    public int count(){
        return daoActuator.count();
    }

    public List<T> list() {
       return daoActuator.select();
    }

    public R page(Integer page, Integer pageSize) {
        FastMapperParam.get().setOpenPage(true);
        FastMapperParam.get().setPage(page);
        FastMapperParam.get().setPageSize(pageSize);
        return (R)this;
    }

    public R or() {
        FastMapperParam.get().isAnd = false;
        return (R)this;
    }
}
