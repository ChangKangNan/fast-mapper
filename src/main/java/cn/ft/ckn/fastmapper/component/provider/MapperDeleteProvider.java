package cn.ft.ckn.fastmapper.component.provider;

import cn.ft.ckn.fastmapper.bean.SearchParam;
import cn.ft.ckn.fastmapper.util.PackageSqlUtil;
import cn.hutool.core.text.StrBuilder;
import cn.hutool.core.util.StrUtil;

/**
 * @author ckn
 */
public class MapperDeleteProvider {
    public static void delete(SearchParam searchParam){
        if(StrUtil.isNotBlank(searchParam.getExecuteSql())){
            return;
        }
        StrBuilder sql = PackageSqlUtil.deleteSql(searchParam);
        PackageSqlUtil.whereSql(sql,searchParam);
        searchParam.setExecuteSql(sql.toString());
    }
}
