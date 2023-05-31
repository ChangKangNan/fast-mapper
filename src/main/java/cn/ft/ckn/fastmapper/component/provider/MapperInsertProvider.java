package cn.ft.ckn.fastmapper.component.provider;

import cn.ft.ckn.fastmapper.bean.SearchParam;
import cn.ft.ckn.fastmapper.util.PackageSqlUtil;
import cn.hutool.core.text.StrBuilder;
import cn.hutool.core.util.StrUtil;

/**
 * @author ckn
 * @date 2023/5/25
 */
public class MapperInsertProvider {
    public static void insert(SearchParam searchParam){
        if(StrUtil.isNotBlank(searchParam.getExecuteSql())){
            return;
        }
        StrBuilder sql = PackageSqlUtil.insertSql(searchParam);
        searchParam.setExecuteSql(sql.toString());
    }
}
