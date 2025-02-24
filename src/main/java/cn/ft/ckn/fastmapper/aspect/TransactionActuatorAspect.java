package cn.ft.ckn.fastmapper.aspect;

import cn.ft.ckn.fastmapper.bean.FastMapperParam;
import cn.ft.ckn.fastmapper.support.dao.jdbc.DataSourceConnection;
import cn.ft.ckn.fastmapper.bean.em.ExpanderOccasion;
import cn.ft.ckn.fastmapper.ex.MapperExpander;
import cn.ft.ckn.fastmapper.transaction.TransactionManager;
import cn.hutool.core.collection.ListUtil;

import javax.sql.DataSource;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author ckn
 */
public class TransactionActuatorAspect implements MapperExpander {
    @Override
    public boolean before(FastMapperParam param, Method method) {
        DataSource dataSource = DataSourceConnection.getDataSource();
        TransactionManager.initTransaction(dataSource);
        return true;
    }

    @Override
    public void after(FastMapperParam param, Method method) {

    }

    @Override
    public void afterException(FastMapperParam param, Method method) {

    }

    @Override
    public List<ExpanderOccasion> occasion() {
        return ListUtil.of(ExpanderOccasion.INSERT, ExpanderOccasion.DELETE, ExpanderOccasion.UPDATE, ExpanderOccasion.SELECT);
    }
}
