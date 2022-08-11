package cn.ft.ckn.fastmapper.join;

import cn.ft.ckn.fastmapper.component.Expression;
import cn.ft.ckn.fastmapper.component.PageInfo;
import cn.ft.ckn.fastmapper.config.FastMapperConfig;
import cn.ft.ckn.fastmapper.util.SQLUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.text.StrBuilder;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ckn
 * @date 2022/8/11
 */
public class JoinManager<T> {
    public JoinParams params;

    public JoinManager(JoinParams params) {
        this.params=params;
    }

    private DataSource getMasterDataSource() {
        DataSource master = FastMapperConfig.dataSourceMaster.get();
        if (master == null) {
            master = SpringUtil.getBean(DataSource.class);
            FastMapperConfig.dataSourceMaster.set(master);
        }
        return master;
    }

    public JoinCustomer<T> setSalveDataSource(DataSource dataSource) {
        params.isMaster = false;
        params.dataSource = dataSource;
        return new JoinCustomer<T>(params);
    }

    protected NamedParameterJdbcTemplate getJdbcTemplate() {
        try {
            NamedParameterJdbcTemplate jdbcTemplate = null;
            if (params.dataSource != null) {
                if (!params.isMaster) {
                    DruidDataSource druidDataSource = (DruidDataSource) params.dataSource;
                    String key = StrBuilder.create(druidDataSource.getDriverClassName(), druidDataSource.getUrl(), druidDataSource.getUsername()
                            , druidDataSource.getPassword()).toString();
                    for (String compare : FastMapperConfig.dataSourceSalveTemplateMap.keySet()) {
                        if (StrUtil.equals(key, compare)) {
                            jdbcTemplate = FastMapperConfig.dataSourceSalveTemplateMap.get(compare);
                        }
                    }
                    if (jdbcTemplate == null) {
                        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(params.dataSource);
                        FastMapperConfig.dataSourceSalveTemplateMap.putIfAbsent(key, namedParameterJdbcTemplate);
                        return namedParameterJdbcTemplate;
                    }
                } else {
                    jdbcTemplate = FastMapperConfig.dataSourceMasterTemplate.get();
                    if (jdbcTemplate == null) {
                        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getMasterDataSource());
                        FastMapperConfig.dataSourceMasterTemplate.set(namedParameterJdbcTemplate);
                        return namedParameterJdbcTemplate;
                    }
                }
            } else {
                jdbcTemplate = FastMapperConfig.dataSourceMasterTemplate.get();
                if (jdbcTemplate == null) {
                    NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getMasterDataSource());
                    FastMapperConfig.dataSourceMasterTemplate.set(namedParameterJdbcTemplate);
                    return namedParameterJdbcTemplate;
                }
            }
            return jdbcTemplate;
        } catch (Exception e) {
            throw new RuntimeException("获取数据库连接失败!");
        }
    }

    StringBuilder getSQL(){
        StringBuilder sqlBuilder=new StringBuilder("SELECT");
        sqlBuilder.append(Expression.LineSeparator.expression);
        sqlBuilder.append(StrUtil.join(",",params.columns.toArray()));
        sqlBuilder.append(Expression.LineSeparator.expression);
        sqlBuilder.append("from");
        sqlBuilder.append(StrUtil.SPACE);
        sqlBuilder.append(params.mainTable);
        sqlBuilder.append(Expression.LineSeparator.expression);
        if(MapUtil.isNotEmpty(params.joins)){
            for (String table : params.joins.keySet()) {
                Map<String, String> map = params.joins.get(table);
                String r = params.relation.get(table);
                sqlBuilder.append(r);
                sqlBuilder.append(StrUtil.SPACE);
                sqlBuilder.append(table);
                sqlBuilder.append(StrUtil.SPACE);
                sqlBuilder.append("ON");
                sqlBuilder.append(StrUtil.SPACE);
                int i=0;
                for (String link : map.keySet()) {
                    i++;
                    if(i !=1){
                        sqlBuilder.append("and");
                    }
                    String s = map.get(link);
                    sqlBuilder.append(link);
                    sqlBuilder.append(Expression.Equal.expression);
                    sqlBuilder.append(s);
                    sqlBuilder.append(StrUtil.SPACE);
                }
                sqlBuilder.append(Expression.LineSeparator.expression);
            }
        }
        if(MapUtil.isNotEmpty(params.where)){
            sqlBuilder.append("WHERE");
            sqlBuilder.append(StrUtil.SPACE);
            int i=0;
            for (String key : params.where.keySet()) {
                i++;
                if(i !=1){
                    sqlBuilder.append(Expression.LineSeparator.expression);
                    sqlBuilder.append("and");
                    sqlBuilder.append(StrUtil.SPACE);
                }
                Object obj = params.where.get(key);
                sqlBuilder.append(key);
                sqlBuilder.append(Expression.Equal.expression);
                sqlBuilder.append(SQLUtil.getValue(obj));
                sqlBuilder.append(StrUtil.SPACE);
            }
        }
        return sqlBuilder;
    }

    public PageInfo<Map<String, Object>> findPage(Integer pageNumber, Integer pageSize){
        NamedParameterJdbcTemplate jdbcTemplate = getJdbcTemplate();
        StringBuilder sql=getSQL();
        StringBuilder countSQL=new StringBuilder("SELECT count(*) ");
        int indexOf = sql.toString().indexOf("from");
        if(indexOf<0){
            indexOf = sql.toString().indexOf("From");
        }
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
            List<Map<String, Object>> mapList = jdbcTemplate.queryForList(sql.toString(),new HashMap<>());
            PageInfo<Map<String, Object>> mapPageInfo = new PageInfo<>(mapList, pageNumber, pageSize, totalCount);
            if (FastMapperConfig.isOpenSQLPrint) {
                SQLUtil.print(SQLUtil.printSql(sql.toString(),new HashMap<>())
                        , SQLUtil.printResult(JSONUtil.toJsonStr(mapPageInfo)));
            }
            return mapPageInfo;
        }catch (Exception e){
            if (FastMapperConfig.isOpenSQLPrint) {
                SQLUtil.print(SQLUtil.printSql(sql.toString(),new HashMap<>())
                        , SQLUtil.printResult(""));
            }
            return new PageInfo<>(new ArrayList<>(),pageNumber,pageSize,totalCount);
        }
    }

    public <R>List<R> findAll(Class<R> returnObj){
        NamedParameterJdbcTemplate jdbcTemplate = getJdbcTemplate();
        StringBuilder sql=getSQL();
        try {
            List<R> list = jdbcTemplate.queryForList(sql.toString(), new HashMap<>(), returnObj);
            if (FastMapperConfig.isOpenSQLPrint) {
                SQLUtil.print(SQLUtil.printSql(sql.toString(),new HashMap<>())
                        , SQLUtil.printResult(JSONUtil.toJsonStr(list)));
            }
            return list;
        }catch (Exception e){
            e.printStackTrace();
            if (FastMapperConfig.isOpenSQLPrint) {
                SQLUtil.print(SQLUtil.printSql(sql.toString(),new HashMap<>())
                        , SQLUtil.printResult(""));
            }
            return new ArrayList<>();
        }
    }

}
