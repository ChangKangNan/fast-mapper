package cn.ft.ckn.fastmapper.support.provider;

import cn.ft.ckn.fastmapper.bean.FastMapperParam;
import cn.ft.ckn.fastmapper.util.sql.PackageSqlUtil;
import cn.hutool.core.text.StrBuilder;
import cn.hutool.core.util.StrUtil;

/**
 * @author ckn
 */
public class MapperDeleteProvider {
    public static void delete(FastMapperParam FastMapperParam){
        if(StrUtil.isNotBlank(FastMapperParam.getExecuteSql())){
            return;
        }
        StrBuilder sql = PackageSqlUtil.deleteSql(FastMapperParam);
        PackageSqlUtil.whereSql(sql,FastMapperParam);
        FastMapperParam.setExecuteSql(sql.toString());
    }
}
