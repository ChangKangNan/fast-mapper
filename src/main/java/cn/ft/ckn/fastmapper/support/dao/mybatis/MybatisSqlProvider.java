package cn.ft.ckn.fastmapper.support.dao.mybatis;

import cn.ft.ckn.fastmapper.bean.FastMapperParam;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;

/**
 * @author ckn
 */
public class MybatisSqlProvider {

    public static String getSql(FastMapperParam param) {
        //处理参数
        String executeSql = param.getExecuteSql();
        if(StrUtil.contains(executeSql,"#{")){
            executeSql = ReUtil.replaceAll(executeSql, "#\\{", "#{paramMap.");
        }
        if(StrUtil.contains(executeSql,"${")){
            executeSql = ReUtil.replaceAll(executeSql, "\\$\\{", "#{paramMap.");
        }
        return executeSql;
    }
}
