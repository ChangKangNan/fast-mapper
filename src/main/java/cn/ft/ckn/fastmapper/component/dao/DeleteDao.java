package cn.ft.ckn.fastmapper.component.dao;

import cn.ft.ckn.fastmapper.aspect.MapperActuatorAspect;
import cn.ft.ckn.fastmapper.bean.DaoActuator;
import cn.ft.ckn.fastmapper.bean.SearchParam;
import cn.ft.ckn.fastmapper.bean.TableMapper;
import cn.ft.ckn.fastmapper.component.dao.jdbc.DataSourceConnection;
import cn.hutool.aop.ProxyUtil;

/**
 * @author ckn
 * @date 2022/7/28
 */
public class DeleteDao<T,R>{
    private Class<R> r;
    private final DaoActuator<T> daoActuator;

    public DeleteDao(Class<R> r,Class<T> classObj) {
        TableMapper.init(classObj);
        this.r=r;
        daoActuator = ProxyUtil.proxy(DataSourceConnection.getDaoActuator(), MapperActuatorAspect.class);
    }

    public R closeDeletedProtect(){
        SearchParam.get().setCloseDeleteProtect(true);
        return (R) this;
    }

    public Integer delete(){
      return daoActuator.delete();
    }
}
