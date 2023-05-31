package cn.ft.ckn.fastmapper.component.dao;

import cn.ft.ckn.fastmapper.bean.DaoActuator;
import cn.ft.ckn.fastmapper.bean.SearchParam;
import cn.ft.ckn.fastmapper.bean.TableMapper;
import cn.ft.ckn.fastmapper.component.dao.jdbc.DataSourceConnection;
import cn.ft.ckn.fastmapper.expander.MapperActuatorAspect;
import cn.hutool.aop.ProxyUtil;

/**
 * @author ckn
 * @date 2022/7/28
 */
public class DeleteDao<T,R>{
    private R r;
    private final DaoActuator<T> daoActuator;

    public DeleteDao(R r,Class<T> classObj) {
        TableMapper.init(classObj);
        this.r = r;
        daoActuator = ProxyUtil.proxy(DataSourceConnection.getDaoActuator(), MapperActuatorAspect.class);
    }

    public R closeDeletedProtect(){
        SearchParam.get().setCloseDeleteProtect(true);
        return r;
    }

    public void delete(){
        daoActuator.delete();
    }
}
