package cn.ft.ckn.fastmapper.util;

import cn.ft.ckn.fastmapper.component.MapperDataSourceManger;
import cn.ft.ckn.fastmapper.component.PageInfo;
import cn.ft.ckn.fastmapper.component.SplicingParam;
import cn.ft.ckn.fastmapper.config.FastMapperConfig;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 自定义sql查询与更新
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

    public PageInfo<Map<String,Object>> findPage(String prepareSql, Integer pageNumber, Integer pageSize,Map<String,Object> params) {
        StringBuilder sql =new StringBuilder(prepareSql);
        NamedParameterJdbcTemplate jdbcTemplate = getJdbcTemplate();
        StringBuilder countSQL=new StringBuilder("SELECT count(*) ");
        int indexOf = sql.toString().toLowerCase().indexOf("from");
        countSQL.append(sql.substring(indexOf));
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
        try {
            List<Map<String, Object>> mapList = jdbcTemplate.queryForList(sql.toString(),params);
            PageInfo<Map<String, Object>> mapPageInfo = new PageInfo<>(mapList, pageNumber, pageSize, totalCount);
            if (FastMapperConfig.isOpenSQLPrint) {
                SQLUtil.print(SQLUtil.printSql(sql.toString(),params)
                        , SQLUtil.printResult(JSONUtil.toJsonStr(mapPageInfo)));
            }
            return mapPageInfo;
        }catch (Exception e){
            if (FastMapperConfig.isOpenSQLPrint) {
                SQLUtil.print(SQLUtil.printSql(sql.toString(),params)
                        , SQLUtil.printResult(""));
            }
            return new PageInfo<>(new ArrayList<>(),pageNumber,pageSize,totalCount);
        }
    }

    public <R>List<R> findAll(String sql,Map<String,Object> params,Class<R> returnObj){
        NamedParameterJdbcTemplate jdbcTemplate = getJdbcTemplate();
        try {
            List<R> list = jdbcTemplate.query(sql,params, new BeanPropertyRowMapper<>(returnObj));
            if (FastMapperConfig.isOpenSQLPrint) {
                SQLUtil.print(SQLUtil.printSql(sql,params)
                        , SQLUtil.printResult(JSONUtil.toJsonStr(list)));
            }
            return list;
        }catch (Exception e){
            if (FastMapperConfig.isOpenSQLPrint) {
                SQLUtil.print(SQLUtil.printSql(sql.toString(),new HashMap<>())
                        , SQLUtil.printResult(""));
            }
            return new ArrayList<>();
        }
    }

    public <R>List<R> findAll(String sql,Class<R> returnObj){
        NamedParameterJdbcTemplate jdbcTemplate = getJdbcTemplate();
        try {
            List<R> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(returnObj));
            if (FastMapperConfig.isOpenSQLPrint) {
                SQLUtil.print(SQLUtil.printSql(sql,new HashMap<>())
                        , SQLUtil.printResult(JSONUtil.toJsonStr(list)));
            }
            return list;
        }catch (Exception e){
            if (FastMapperConfig.isOpenSQLPrint) {
                SQLUtil.print(SQLUtil.printSql(sql.toString(),new HashMap<>())
                        , SQLUtil.printResult(""));
            }
            return new ArrayList<>();
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
        return jdbcTemplate.query(sql,parameters,new BeanPropertyRowMapper<>(rowMapperClass));
    }

    /**
     * 自定义sql执行
     * @param sql
     * @param parameters
     * @return
     */
    public int execute(String sql,Map<String,Object> parameters){
        NamedParameterJdbcTemplate jdbcTemplate = getJdbcTemplate();
        int update = jdbcTemplate.update(sql, parameters);
        if (FastMapperConfig.isOpenSQLPrint) {
            SQLUtil.print(SQLUtil.printSql(sql,parameters)
                    , SQLUtil.printResult(update));
        }
        return update;
    }

    /**
     * 自定义sql执行
     * @param sql
     * @return
     */
    public int execute(String sql){
        NamedParameterJdbcTemplate jdbcTemplate = getJdbcTemplate();
        int update = jdbcTemplate.update(sql,new HashMap<>());
        if (FastMapperConfig.isOpenSQLPrint) {
            SQLUtil.print(sql
                    , SQLUtil.printResult(update));
        }
        return update;
    }
}
