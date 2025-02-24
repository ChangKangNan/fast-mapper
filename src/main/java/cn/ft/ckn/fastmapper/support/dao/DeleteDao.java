package cn.ft.ckn.fastmapper.support.dao;

import cn.ft.ckn.fastmapper.bean.FastMapperParam;
import cn.ft.ckn.fastmapper.bean.FastTableMapper;
import cn.ft.ckn.fastmapper.support.dao.jdbc.DataSourceConnection;

/**
 * @author ckn
 * @date 2022/7/28
 */
public class DeleteDao<T,R> extends BaseDao<R>{
    private Class<R> r;
    private final DaoActuator<T> daoActuator;

    public DeleteDao(Class<R> r,Class<T> classObj) {
        FastTableMapper.init(classObj);
        this.r=r;
        daoActuator = DataSourceConnection.getDaoActuator();
    }

    public R closeDeletedProtect(){
        FastMapperParam.get().setCloseDeleteProtect(true);
        return (R) this;
    }

    public Integer delete(){
      return daoActuator.delete();
    }


}
