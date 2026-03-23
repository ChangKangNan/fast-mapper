package cn.ft.ckn.fastmapper.support.dao;

import cn.ft.ckn.fastmapper.bean.FastMapperParam;
import cn.ft.ckn.fastmapper.support.dao.jdbc.DataSourceConnection;

import javax.sql.DataSource;

public class BaseDao<R> {

    public R setSalveDataSource(DataSource dataSource){
        FastMapperParam fastMapperParam = FastMapperParam.get();
        fastMapperParam.setMaster(false);
        DataSourceConnection.setSlaveDataSource(dataSource);
        return (R)this;
    }
}
