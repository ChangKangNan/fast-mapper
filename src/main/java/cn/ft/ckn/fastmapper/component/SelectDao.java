package cn.ft.ckn.fastmapper.component;

import cn.ft.ckn.fastmapper.config.FastMapperConfig;
import cn.ft.ckn.fastmapper.util.SQLUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Pair;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author ckn
 * @date 2022/7/28
 */
public class SelectDao<T, R> extends MapperDataSourceManger<R> implements Pager<R> {
    private final SplicingParam splicingParam;
    private final Class<T> classObj;
    private final Class<R> returnObj;

    public SelectDao(SplicingParam splicingParam, Class<T> classObj, Class<R> returnObj) {
        super(returnObj, splicingParam);
        this.splicingParam = splicingParam;
        this.classObj = classObj;
        this.returnObj = returnObj;
    }

    public T one() {
        Pair<Map<String, Object>, StringBuilder> mapStringBuilderPair = PackSQLUtil.packageSQL(this.splicingParam, classObj);
        NamedParameterJdbcTemplate jdbcTemplate = getJdbcTemplate();
        try {
            List<T> query = jdbcTemplate.query(mapStringBuilderPair.getValue().toString(), mapStringBuilderPair.getKey(), new BeanPropertyRowMapper<T>(classObj));
            if (FastMapperConfig.isOpenSQLPrint) {
                if(CollUtil.isNotEmpty(query)){
                    SQLUtil.print(SQLUtil.printSql(mapStringBuilderPair.getValue().toString(), mapStringBuilderPair.getKey())
                            , SQLUtil.printResult(JSONUtil.toJsonStr(query.get(0))));
                }
            }
            if(CollUtil.isEmpty(query)){
                return null;
            }
            return query.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            if (FastMapperConfig.isOpenSQLPrint) {
                SQLUtil.print(SQLUtil.printSql(mapStringBuilderPair.getValue().toString(), mapStringBuilderPair.getKey())
                        , SQLUtil.printResult(""));
            }
            return null;
        }
    }

    public List<T> list() {
        Pair<Map<String, Object>, StringBuilder> mapStringBuilderPair = PackSQLUtil.packageSQL(this.splicingParam, classObj);
        NamedParameterJdbcTemplate jdbcTemplate = getJdbcTemplate();
        try {
            List<T> query = jdbcTemplate.query(mapStringBuilderPair.getValue().toString(), mapStringBuilderPair.getKey(), new BeanPropertyRowMapper<T>(classObj));
            if (FastMapperConfig.isOpenSQLPrint) {
                SQLUtil.print(SQLUtil.printSql(mapStringBuilderPair.getValue().toString(), mapStringBuilderPair.getKey())
                , SQLUtil.printResult(JSONUtil.toJsonStr(query)));
            }
            return query;
        } catch (Exception e) {
            if (FastMapperConfig.isOpenSQLPrint) {
                SQLUtil.print(SQLUtil.printSql(mapStringBuilderPair.getValue().toString(), mapStringBuilderPair.getKey())
                        , SQLUtil.printResult(""));
            }
            return new ArrayList<>();
        }
    }

    public R page(Integer page, Integer pageSize) {
        this.splicingParam.isOpenPage = true;
        this.splicingParam.setPage(page);
        this.splicingParam.setPageSize(pageSize);
        try {
            return returnObj.getDeclaredConstructor(SplicingParam.class).newInstance(splicingParam);
        } catch (Exception e) {
            return null;
        }
    }
    public R or() {
        this.splicingParam.isAnd = false;
        try {
            return returnObj.getDeclaredConstructor(SplicingParam.class).newInstance(splicingParam);
        } catch (Exception e) {
            return null;
        }
    }
}
