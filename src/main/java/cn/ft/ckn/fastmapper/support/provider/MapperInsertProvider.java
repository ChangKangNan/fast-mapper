package cn.ft.ckn.fastmapper.support.provider;

import cn.ft.ckn.fastmapper.bean.FastMapperParam;
import cn.ft.ckn.fastmapper.util.sql.PackageSqlUtil;
import cn.hutool.core.text.StrBuilder;
import cn.hutool.core.util.StrUtil;

/**
 * @author ckn
 */
public class MapperInsertProvider {
    public static void insert(FastMapperParam fastMapperParam){
        if(StrUtil.isNotBlank(fastMapperParam.getExecuteSql())){
            return;
        }
        StrBuilder sql = PackageSqlUtil.insertSql(fastMapperParam);
        fastMapperParam.setExecuteSql(sql.toString());
    }
}
