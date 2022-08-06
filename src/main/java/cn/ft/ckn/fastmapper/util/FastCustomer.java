package cn.ft.ckn.fastmapper.util;

import cn.ft.ckn.fastmapper.component.MapperDataSourceManger;
import cn.ft.ckn.fastmapper.component.PageInfo;
import cn.ft.ckn.fastmapper.component.SplicingParam;
import cn.ft.ckn.fastmapper.config.FastMapperConfig;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.ArrayList;
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
        if(FastMapperConfig.isOpenSQLPrint) {
            SQLUtil.print(sql.toString(),new HashMap<>(),"CUSTOMER");
        }
        try {
            List<Map<String, Object>> mapList = jdbcTemplate.queryForList(sql.toString(),new HashMap<>());
            PageInfo<Map<String, Object>> mapPageInfo = new PageInfo<>(mapList, pageNumber, pageSize, totalCount);
            if(FastMapperConfig.isOpenSQLPrint) {
                SQLUtil.printResult(mapPageInfo);
            }
            return mapPageInfo;
        }catch (Exception e){
            return new PageInfo<>(new ArrayList<>(),pageNumber,pageSize,totalCount);
        }
    }

    /**
     * 通过 sql文件运行获得返回结果
     *
     * @param sqlPath
     * @param parameters
     * @param rowMapperClass
     * @return
     */
    public <E> List<E> selectByFile(String sqlPath, Map<String, Object> parameters, Class<E> rowMapperClass) {
        //处理参数
        ClassPathResource resource = new ClassPathResource(sqlPath);
        String sql = IoUtil.read(resource.getStream()).toString();
        NamedParameterJdbcTemplate jdbcTemplate = getJdbcTemplate();
        Map<String,Object> params=new HashMap<>();
        params.put("id",6);
        List<E> query = jdbcTemplate.query(sql,params,new BeanPropertyRowMapper<>(rowMapperClass));
        return query;
    }
}
