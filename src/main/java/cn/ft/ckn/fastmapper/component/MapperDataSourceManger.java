package cn.ft.ckn.fastmapper.component;

import cn.ft.ckn.fastmapper.config.FastMapperConfig;
import cn.hutool.core.text.StrBuilder;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

/**
 * @author ckn
 * @date 2022/8/1
 */
public class MapperDataSourceManger<R> {
    private final SplicingParam splicingParam;
    Class<R> returnObj;


    public MapperDataSourceManger(Class<R> returnObj, SplicingParam splicingParam) {
        this.returnObj = returnObj;
        this.splicingParam = splicingParam;
    }

    private DataSource getMasterDataSource() {
        DataSource master = FastMapperConfig.dataSourceMaster.get();
        if (master == null) {
            master = SpringUtil.getBean(DataSource.class);
            FastMapperConfig.dataSourceMaster.set(master);
        }
        return master;
    }

    public R setSalveDataSource(DataSource dataSource){
        splicingParam.isMaster = false;
        splicingParam.dataSource = dataSource;
        try {
            return returnObj.getDeclaredConstructor(SplicingParam.class).newInstance(splicingParam);
        }catch (Exception e){
            return null;
        }
    }

    protected NamedParameterJdbcTemplate getJdbcTemplate() {
        try {
            NamedParameterJdbcTemplate jdbcTemplate = null;
            if (splicingParam.dataSource != null) {
                if (!splicingParam.isMaster) {
                    DruidDataSource druidDataSource = (DruidDataSource) splicingParam.dataSource;
                    String key = StrBuilder.create(druidDataSource.getDriverClassName(), druidDataSource.getUrl(), druidDataSource.getUsername()
                            , druidDataSource.getPassword()).toString();
                    for (String compare : FastMapperConfig.dataSourceSalveTemplateMap.keySet()) {
                        if (StrUtil.equals(key, compare)) {
                            jdbcTemplate = FastMapperConfig.dataSourceSalveTemplateMap.get(compare);
                        }
                    }
                    if (jdbcTemplate == null) {
                        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(splicingParam.dataSource);
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
}
