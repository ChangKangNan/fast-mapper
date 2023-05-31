package cn.ft.ckn.fastmapper.component.provider;

import cn.ft.ckn.fastmapper.bean.SearchParam;
import cn.ft.ckn.fastmapper.util.PackageSqlUtil;
import cn.hutool.core.text.StrBuilder;

/**
 * @author ckn
 */
public class MapperSelectProvider {
    public static void findAll(SearchParam searchParam){
        StrBuilder sql = PackageSqlUtil.selectSql(searchParam);
        PackageSqlUtil.whereSql(sql,searchParam);
        PackageSqlUtil.orderBySql(sql,searchParam);
        searchParam.setExecuteSql(sql.toString());
    }

    public static void findCount(SearchParam searchParam){
        StrBuilder sql = PackageSqlUtil.countSql(searchParam);
        PackageSqlUtil.whereSql(sql,searchParam);
        searchParam.setExecuteSql(sql.toString());
    }
}
