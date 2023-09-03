package cn.ft.ckn.fastmapper.component.dao.jdbc;

import cn.ft.ckn.fastmapper.bean.DaoActuator;
import cn.ft.ckn.fastmapper.bean.SearchParam;
import cn.ft.ckn.fastmapper.component.provider.MapperDeleteProvider;
import cn.ft.ckn.fastmapper.component.provider.MapperInsertProvider;
import cn.ft.ckn.fastmapper.component.provider.MapperSelectProvider;
import cn.ft.ckn.fastmapper.component.provider.MapperUpdateProvider;
import cn.ft.ckn.fastmapper.util.PackageSqlUtil;
import cn.hutool.core.bean.BeanUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author ckn
 */
public class JdbcDaoActuator<T> implements DaoActuator<T> {

    @Override
    public List<T> insert() {
        SearchParam<T> param = SearchParam.get();
        MapperInsertProvider.insert(param);
        List<T> insertList = param.getInsertList();
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sqlConversion = PackageSqlUtil.sqlConversion(param.getExecuteSql());
        DataSourceConnection.getJdbcTemplate().update(sqlConversion, new MapSqlParameterSource(param.getParamMap()), keyHolder);
        if (insertList.size() == 1) {
            BeanUtil.setFieldValue(param.getInsertList().get(0), param.getTableMapper().getPrimaryKey(), Objects.requireNonNull(keyHolder.getKey()).longValue());
        }else {
            List<Map<String, Object>> keyList = keyHolder.getKeyList();
            for (int i = 0; i < keyList.size(); i++) {
                BeanUtil.setFieldValue(param.getInsertList().get(i), param.getTableMapper().getPrimaryKey(), Objects.requireNonNull(keyList.get(i).values().iterator().next()));
            }
        }
        return insertList;
    }

    @Override
    public List<T> select() {
        SearchParam<T> param = SearchParam.get();
        MapperSelectProvider.findAll(param);
        Class<T> classObj = param.getTableMapper().getObjClass();
        return DataSourceConnection.getJdbcTemplate().query(PackageSqlUtil.sqlConversion(param.getExecuteSql()), param.getParamMap(), new BeanPropertyRowMapper<T>(classObj));
    }

    @Override
    public Integer count() {
        SearchParam<T> param = SearchParam.get();
        MapperSelectProvider.findCount(param);
        return DataSourceConnection.getJdbcTemplate().queryForObject(PackageSqlUtil.sqlConversion(param.getExecuteSql()), param.getParamMap(), Integer.class);
    }

    @Override
    public Integer update() {
        SearchParam<T> param = SearchParam.get();
        MapperUpdateProvider.update(param);
        return DataSourceConnection.getJdbcTemplate().update(PackageSqlUtil.sqlConversion(param.getExecuteSql()), param.getParamMap());
    }

    @Override
    public Integer delete() {
        SearchParam<T> param = SearchParam.get();
        MapperDeleteProvider.delete(param);
        return DataSourceConnection.getJdbcTemplate().update(PackageSqlUtil.sqlConversion(param.getExecuteSql()), param.getParamMap());
    }

}
