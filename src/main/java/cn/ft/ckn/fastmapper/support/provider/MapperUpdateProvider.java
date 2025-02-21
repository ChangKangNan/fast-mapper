package cn.ft.ckn.fastmapper.support.provider;

import cn.ft.ckn.fastmapper.bean.FastMapperParam;
import cn.ft.ckn.fastmapper.util.sql.PackageSqlUtil;
import cn.hutool.core.text.StrBuilder;
import cn.hutool.core.util.StrUtil;

/**
 * 装饰器模式
 * @author ckn
 * @date 2023/5/25
 */
public class MapperUpdateProvider {

    public static void update(FastMapperParam fastMapperParam){
        if(StrUtil.isNotBlank(fastMapperParam.getExecuteSql())){
            return;
        }
        StrBuilder sql = PackageSqlUtil.updateSql(fastMapperParam);
        PackageSqlUtil.whereSql(sql,fastMapperParam);
        fastMapperParam.setExecuteSql(sql.toString());
    }
}
