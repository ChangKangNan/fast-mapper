package cn.ft.ckn.fastmapper.support.dao.jdbc;

import cn.ft.ckn.fastmapper.support.dao.DaoActuator;
import cn.ft.ckn.fastmapper.bean.FastMapperParam;
import cn.ft.ckn.fastmapper.support.provider.MapperDeleteProvider;
import cn.ft.ckn.fastmapper.support.provider.MapperInsertProvider;
import cn.ft.ckn.fastmapper.support.provider.MapperSelectProvider;
import cn.ft.ckn.fastmapper.support.provider.MapperUpdateProvider;
import cn.ft.ckn.fastmapper.util.sql.PackageSqlUtil;
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
        FastMapperParam<T> param = FastMapperParam.get();
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
        FastMapperParam<T> param = FastMapperParam.get();
        MapperSelectProvider.findAll(param);
        Class<T> classObj = param.getTableMapper().getObjClass();
        return DataSourceConnection.getJdbcTemplate().query(PackageSqlUtil.sqlConversion(param.getExecuteSql()), param.getParamMap(), new BeanPropertyRowMapper<T>(classObj));
    }

    @Override
    public Integer count() {
        FastMapperParam<T> param = FastMapperParam.get();
        MapperSelectProvider.findCount(param);
        return DataSourceConnection.getJdbcTemplate().queryForObject(PackageSqlUtil.sqlConversion(param.getExecuteSql()), param.getParamMap(), Integer.class);
    }

    @Override
    public Integer update() {
        FastMapperParam<T> param = FastMapperParam.get();
        MapperUpdateProvider.update(param);
        return DataSourceConnection.getJdbcTemplate().update(PackageSqlUtil.sqlConversion(param.getExecuteSql()), param.getParamMap());
    }

    @Override
    public Integer delete() {
        FastMapperParam<T> param = FastMapperParam.get();
        MapperDeleteProvider.delete(param);
        return DataSourceConnection.getJdbcTemplate().update(PackageSqlUtil.sqlConversion(param.getExecuteSql()), param.getParamMap());
    }

}
