package cn.ft.ckn.fastmapper.util;

import cn.ft.ckn.fastmapper.component.MapperDataSourceManger;
import cn.ft.ckn.fastmapper.component.PageInfo;
import cn.ft.ckn.fastmapper.component.SplicingParam;
import cn.ft.ckn.fastmapper.config.FastMapperConfig;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
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

    public static FastCustomer create() {
        return new FastCustomer(new SplicingParam());
    }

    public PageInfo<Map<String, Object>> findPage(StringBuilder sql, Integer pageNumber, Integer pageSize, Map<String, Object> params) {
        NamedParameterJdbcTemplate jdbcTemplate = getJdbcTemplate();
        StringBuilder countSQL = new StringBuilder("SELECT count(*) ");
        int indexOf = sql.toString().indexOf("from");
        if (indexOf < 0) {
            indexOf = sql.toString().indexOf("From");
        }
        countSQL.append(sql.substring(indexOf));
        Integer totalCount = jdbcTemplate.queryForObject(countSQL.toString(), new HashMap<>(), Integer.class);
        if (pageNumber != null && pageSize != null) {
            sql.append(System.lineSeparator());
            sql.append("LIMIT");
            sql.append(StrUtil.SPACE);
            int pageNum = pageNumber - 1;
            sql.append(pageNum * pageSize);
            sql.append(StrUtil.C_COMMA);
            sql.append(pageSize);
        }
        try {
            DataSource dataSource = getDataSource();
            JDBCUtils jdbcUtils = new JDBCUtils(dataSource);
            List<Map<String, Object>> mapList = jdbcUtils.queryForMap(sql.toString(), params);
            PageInfo<Map<String, Object>> mapPageInfo = new PageInfo<>(mapList, pageNumber, pageSize, totalCount);
            if (FastMapperConfig.isOpenSQLPrint) {
                SQLUtil.print(SQLUtil.printSql(sql.toString(), params)
                        , SQLUtil.printResult(JSONUtil.toJsonStr(mapPageInfo)));
            }
            return mapPageInfo;
        } catch (Exception e) {
            if (FastMapperConfig.isOpenSQLPrint) {
                SQLUtil.print(SQLUtil.printSql(sql.toString(), params)
                        , SQLUtil.printResult(""));
            }
            return new PageInfo<>(new ArrayList<>(), pageNumber, pageSize, totalCount);
        }
    }

    public <R> List<R> findAll(StringBuilder sql, Class<R> returnObj) {
        try {
            DataSource dataSource = getDataSource();
            try {
                JDBCUtils jdbcUtils = new JDBCUtils(dataSource);
                List<R> list = jdbcUtils.queryForList(sql.toString(), new HashMap<>(), returnObj);
                if (FastMapperConfig.isOpenSQLPrint) {
                    SQLUtil.print(SQLUtil.printSql(sql.toString(), new HashMap<>())
                            , SQLUtil.printResult(JSONUtil.toJsonStr(list)));
                }
                return list;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            if (FastMapperConfig.isOpenSQLPrint) {
                SQLUtil.print(SQLUtil.printSql(sql.toString(), new HashMap<>())
                        , SQLUtil.printResult(""));
            }
            return new ArrayList<>();
        }
        return new ArrayList<>();
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
        DataSource dataSource = getDataSource();
        try {
            JDBCUtils jdbcUtils = new JDBCUtils(dataSource);
            List<E> query = jdbcUtils.queryForList(sql, parameters, rowMapperClass);
            return query;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
