package cn.ft.ckn.fastmapper.component.manager;

import cn.ft.ckn.fastmapper.bean.FastMapperParam;
import cn.ft.ckn.fastmapper.config.FastMapperConfig;
import cn.ft.ckn.fastmapper.constants.Operation;
import cn.ft.ckn.fastmapper.transaction.context.DataSourceContext;
import cn.hutool.core.bean.BeanUtil;
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
   protected final FastMapperParam fastMapperParam;
    private R r;

    public MapperDataSourceManger() {
        this.fastMapperParam = new FastMapperParam();
    }

    public MapperDataSourceManger(R r) {
        this.fastMapperParam = BeanUtil.getProperty(r, Operation.PARAM);
        this.r=r;
    }

    private DataSource getMasterDataSource() {
        DataSource master = FastMapperConfig.dataSourceMaster.get();
        if (master == null) {
            master = SpringUtil.getBean(DataSource.class);
            FastMapperConfig.dataSourceMaster.set(master);
        }
        return master;
    }

    public R setSalveDataSource(DataSource dataSource) {
        fastMapperParam.isMaster = false;
        fastMapperParam.dataSource = dataSource;
        BeanUtil.setProperty(r, Operation.PARAM, fastMapperParam);
        return r;
    }

    protected DataSource getDataSource() {
        DataSource dataSource = DataSourceContext.getDataSource();
        if(dataSource != null){
            return dataSource;
        }
        return fastMapperParam.dataSource==null?getMasterDataSource():fastMapperParam.dataSource;
    }

    protected NamedParameterJdbcTemplate getJdbcTemplate() {
        DataSource dataSource = DataSourceContext.getDataSource();
        if(dataSource != null){
            setSalveDataSource(dataSource);
        }
        try {
            NamedParameterJdbcTemplate jdbcTemplate = null;
            if (fastMapperParam.dataSource != null) {
                if (!fastMapperParam.isMaster) {
                    DruidDataSource druidDataSource = (DruidDataSource) fastMapperParam.dataSource;
                    String key = StrBuilder.create(druidDataSource.getDriverClassName(), druidDataSource.getUrl(), druidDataSource.getUsername()
                            , druidDataSource.getPassword()).toString();
                    for (String compare : FastMapperConfig.dataSourceSalveTemplateMap.keySet()) {
                        if (StrUtil.equals(key, compare)) {
                            jdbcTemplate = FastMapperConfig.dataSourceSalveTemplateMap.get(compare);
                        }
                    }
                    if (jdbcTemplate == null) {
                        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(fastMapperParam.dataSource);
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
