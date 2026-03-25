package cn.ft.ckn.fastmapper.support.provider;

import cn.ft.ckn.fastmapper.bean.FastMapperParam;
import cn.ft.ckn.fastmapper.util.sql.PackageSqlUtil;
import cn.hutool.core.text.StrBuilder;

/**
 * @author ckn
 */
public class MapperSelectProvider {
    public static void findAll(FastMapperParam fastMapperParam){
        StrBuilder sql = PackageSqlUtil.selectSql(fastMapperParam);
        PackageSqlUtil.whereSql(sql,fastMapperParam);
        PackageSqlUtil.orderBySql(sql,fastMapperParam);
        PackageSqlUtil.limit(sql,fastMapperParam);
        fastMapperParam.setExecuteSql(sql.toString());
    }

    public static void findCount(FastMapperParam fastMapperParam){
        StrBuilder sql = PackageSqlUtil.countSql(fastMapperParam);
        PackageSqlUtil.whereSql(sql,fastMapperParam);
        fastMapperParam.setExecuteSql(sql.toString());
    }
}
