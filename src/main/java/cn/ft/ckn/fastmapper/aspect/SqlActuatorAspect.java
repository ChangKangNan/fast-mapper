package cn.ft.ckn.fastmapper.aspect;

import cn.ft.ckn.fastmapper.bean.SearchParam;
import cn.ft.ckn.fastmapper.config.FastMapperConfig;
import cn.ft.ckn.fastmapper.expander.ExpanderOccasion;
import cn.ft.ckn.fastmapper.expander.MapperExpander;
import cn.ft.ckn.fastmapper.util.PackageSqlUtil;
import cn.ft.ckn.fastmapper.util.SQLUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.json.JSONUtil;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author ckn
 */
public class SqlActuatorAspect  implements MapperExpander {

    @Override
    public boolean before(SearchParam param, Method method) {
        return true;
    }

    @Override
    public void after(SearchParam param, Method method) {
        if(FastMapperConfig.isOpenSQLPrint){
            SQLUtil.print(SQLUtil.printSql(PackageSqlUtil.sqlConversion(param.getExecuteSql()),param.getParamMap())
                    , SQLUtil.printResult(JSONUtil.toJsonStr(param.getReturnVal())));
        }

    }

    @Override
    public void afterException(SearchParam param, Method method) {
        if(FastMapperConfig.isOpenSQLPrint) {
            SQLUtil.print(SQLUtil.printSql(PackageSqlUtil.sqlConversion(param.getExecuteSql()), param.getParamMap())
                    , SQLUtil.printResult(""));
        }
    }

    @Override
    public List<ExpanderOccasion> occasion() {
        return ListUtil.of(ExpanderOccasion.INSERT, ExpanderOccasion.DELETE, ExpanderOccasion.UPDATE, ExpanderOccasion.SELECT);
    }
}
