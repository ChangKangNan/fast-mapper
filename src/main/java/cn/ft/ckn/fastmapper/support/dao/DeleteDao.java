package cn.ft.ckn.fastmapper.support.dao;

import cn.ft.ckn.fastmapper.bean.FastMapperParam;
import cn.ft.ckn.fastmapper.bean.FastTableMapper;
import cn.ft.ckn.fastmapper.config.FastMapperConfig;
import cn.ft.ckn.fastmapper.support.dao.jdbc.DataSourceConnection;
import cn.ft.ckn.fastmapper.util.sql.PackageSqlUtil;
import cn.hutool.core.util.StrUtil;

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
        //是否包含逻辑删除字段
        Class objClass = FastMapperParam.get().getTableMapper().getObjClass();
        if((!FastMapperParam.get().getCloseDeleteProtect()) && StrUtil.isNotBlank(FastMapperConfig.logicDeletedColumn) && PackageSqlUtil.hasField(objClass,FastMapperConfig.logicDeletedColumn)){
            FastMapperParam.get().getUpdateValueList().add(new FastMapperParam.Value(FastMapperConfig.logicDeletedColumn, FastMapperConfig.logicDeletedColumnDeletedValue));
            return daoActuator.update();
        }
      return daoActuator.delete();
    }


}
