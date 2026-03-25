package cn.ft.ckn.fastmapper.aspect.base;

import cn.ft.ckn.fastmapper.bean.FastMapperParam;
import cn.ft.ckn.fastmapper.bean.em.ExpanderOccasion;
import cn.ft.ckn.fastmapper.config.FastMapperConfig;
import cn.ft.ckn.fastmapper.ex.MapperExpander;
import cn.ft.ckn.fastmapper.util.log.LogUtil;
import cn.ft.ckn.fastmapper.util.sql.PackageSqlUtil;
import cn.hutool.core.collection.ListUtil;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author ckn
 */
public class SqlPrintActuatorAspect implements MapperExpander {

    @Override
    public boolean before(FastMapperParam param, Method method) {
        return true;
    }

    @Override
    public void after(FastMapperParam param, Method method) {
        if (!FastMapperConfig.isOpenSQLPrint) {
            return;
        }
        String printSql = LogUtil.printSql(PackageSqlUtil.sqlConversion(param.getExecuteSql()), param.getParamMap());
        LogUtil.print(printSql, LogUtil.printResult(param.getReturnVal()));
    }


    @Override
    public void afterException(FastMapperParam param, Method method) {
        if (!FastMapperConfig.isOpenSQLPrint) {
            return;
        }
        String sqlConversion = PackageSqlUtil.sqlConversion(param.getExecuteSql());
        LogUtil.print(LogUtil.printSql(sqlConversion, param.getParamMap())
                , LogUtil.printResult("exception occurred during the query!"));
    }

    @Override
    public List<ExpanderOccasion> occasion() {
        return ListUtil.of(ExpanderOccasion.INSERT, ExpanderOccasion.DELETE, ExpanderOccasion.UPDATE, ExpanderOccasion.SELECT);
    }
}
