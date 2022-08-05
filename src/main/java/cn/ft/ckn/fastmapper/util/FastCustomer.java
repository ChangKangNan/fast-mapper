package cn.ft.ckn.fastmapper.util;

import cn.ft.ckn.fastmapper.component.MapperDataSourceManger;
import cn.ft.ckn.fastmapper.component.PageInfo;
import cn.ft.ckn.fastmapper.component.SplicingParam;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ckn
 * @date 2022/8/5
 */
public class FastCustomer extends MapperDataSourceManger<FastCustomer> {

    public FastCustomer(SplicingParam splicingParam) {
        super(FastCustomer.class, splicingParam);
    }
    public static FastCustomer create(){
        return new FastCustomer(new SplicingParam());
    }

    public PageInfo<Map<String,Object>> findPage(StringBuilder sql, Integer pageNumber, Integer pageSize) {
        NamedParameterJdbcTemplate jdbcTemplate = getJdbcTemplate();
        StringBuilder countSQL=new StringBuilder("SELECT count(*) ");
        int lastIndexOf = sql.toString().lastIndexOf("from");
        if(lastIndexOf<0){
            lastIndexOf = sql.toString().lastIndexOf("From");
        }
        countSQL.append(sql.substring(lastIndexOf));
        Integer totalCount = jdbcTemplate.queryForObject(countSQL.toString(), new HashMap<>(), Integer.class);
        if (pageNumber != null && pageSize != null) {
            sql.append(System.lineSeparator());
            sql.append("LIMIT");
            sql.append(StrUtil.SPACE);
            int pageNum = pageNumber - 1;
            sql.append(pageNum*pageSize);
            sql.append(StrUtil.C_COMMA);
            sql.append(pageSize);
        }
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(sql.toString(),new HashMap<>());
        return new PageInfo<>(mapList,pageNumber,pageSize,totalCount);
    }

}
