package cn.ft.ckn.fastmapper.component.dao.jdbc;

import cn.ft.ckn.fastmapper.bean.DaoActuator;
import cn.ft.ckn.fastmapper.bean.SearchParam;
import cn.ft.ckn.fastmapper.config.FastMapperConfig;
import cn.hutool.core.text.StrBuilder;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import io.netty.util.concurrent.FastThreadLocal;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import sun.reflect.misc.MethodUtil;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ckn
 */
public class DataSourceConnection {
    private static final FastThreadLocal<NamedParameterJdbcTemplate> jdbcTemplateThreadLocal = new FastThreadLocal<>();
    private static final FastThreadLocal<DataSource> slaveDataSourceThreadLocal = new FastThreadLocal<>();
    private static final FastThreadLocal<NamedParameterJdbcTemplate> jdbcTemplateSlaveThreadLocal = new FastThreadLocal<>();
    public static final Map<String, NamedParameterJdbcTemplate> dataSourceSalveTemplateMap = new HashMap<>();

    /**
     * 获取NamedParameterJdbcTemplate
     *
     * @return 获取到的信息
     */
    public static NamedParameterJdbcTemplate getJdbcTemplate() {
        SearchParam searchParam = SearchParam.get();
        Boolean master = searchParam.getMaster();
        NamedParameterJdbcTemplate jdbcTemplate = null;
        if (master) {
            jdbcTemplate = jdbcTemplateThreadLocal.get();
            if (jdbcTemplate == null) {
                jdbcTemplate = dataSource();
            }
            return jdbcTemplate;
        } else {
            DataSource dataSource = slaveDataSourceThreadLocal.get();
            String slaveKey = getSlaveKey(dataSource);
            for (String compare : dataSourceSalveTemplateMap.keySet()) {
                if (StrUtil.equals(slaveKey, compare)) {
                    jdbcTemplate = dataSourceSalveTemplateMap.get(compare);
                }
            }
            if (jdbcTemplate == null) {
                jdbcTemplate=new NamedParameterJdbcTemplate(dataSource);
                dataSourceSalveTemplateMap.put(slaveKey,jdbcTemplate);
            }
        }
        return jdbcTemplate;
    }

    private static String getSlaveKey(DataSource dataSource){
        Class<? extends DataSource> dataSourceClass = dataSource.getClass();
        try {
            String username = (String) MethodUtil.getMethod(dataSourceClass, "getUsername", null).invoke(dataSourceClass);
            String password = (String) MethodUtil.getMethod(dataSourceClass, "getPassword", null).invoke(dataSourceClass);
            String url = (String) MethodUtil.getMethod(dataSourceClass, "getUrl", null).invoke(dataSourceClass);
            return new StrBuilder(username).append(password).append(url).toString();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public static NamedParameterJdbcTemplate dataSource() {
        DataSource dataSource = getMasterDataSource();
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        jdbcTemplateThreadLocal.set(jdbcTemplate);
        return jdbcTemplate;
    }

    public static DataSource getDataSource(){
        SearchParam searchParam = SearchParam.get();
        Boolean master = searchParam.getMaster();
        if (master) {
            return getMasterDataSource();
        }
        return slaveDataSourceThreadLocal.get();
    }

    public static void clearThreadLocal(){
        jdbcTemplateThreadLocal.remove();
    }

    public static void setSlaveDataSource(DataSource dataSource) {
        SearchParam.get().setMaster(false);
        slaveDataSourceThreadLocal.set(dataSource);
    }

    public static void clearSlaveThreadLocal(){
        slaveDataSourceThreadLocal.remove();
        jdbcTemplateSlaveThreadLocal.remove();
    }

    private static DataSource getMasterDataSource() {
        DataSource master = FastMapperConfig.dataSourceMaster.get();
        if (master == null) {
            master = SpringUtil.getBean(DataSource.class);
            FastMapperConfig.dataSourceMaster.set(master);
        }
        return master;
    }

    private static Class<? extends DaoActuator> daoActuator = JdbcDaoActuator.class;

    public static <T> DaoActuator<T> getDaoActuator() {
        try {
            return daoActuator.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
