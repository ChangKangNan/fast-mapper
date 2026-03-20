package cn.ft.ckn.fastmapper.support.dao.jdbc;

import cn.ft.ckn.fastmapper.aspect.base.MapperActuatorAspect;
import cn.ft.ckn.fastmapper.support.dao.DaoActuator;
import cn.ft.ckn.fastmapper.bean.FastMapperParam;
import cn.ft.ckn.fastmapper.config.FastMapperConfig;
import cn.ft.ckn.fastmapper.transaction.context.DataSourceContext;
import cn.hutool.aop.ProxyUtil;
import cn.hutool.core.text.StrBuilder;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.druid.pool.DruidDataSource;
import io.netty.util.concurrent.FastThreadLocal;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ckn
 */
public class DataSourceConnection {
    private static final FastThreadLocal<NamedParameterJdbcTemplate> jdbcTemplateThreadLocal = new FastThreadLocal<>();
    private static final FastThreadLocal<DataSource> slaveDataSourceThreadLocal = new FastThreadLocal<>();
    public static final Map<String, NamedParameterJdbcTemplate> dataSourceSalveTemplateMap = new ConcurrentHashMap<>();

    /**
     * 获取NamedParameterJdbcTemplate
     *
     * @return 获取到的信息
     */
    public static NamedParameterJdbcTemplate getJdbcTemplate() {
        FastMapperParam<?> fastMapperParam = FastMapperParam.get();
        Boolean master = fastMapperParam.getMaster();
        NamedParameterJdbcTemplate jdbcTemplate = null;
        if (DataSourceContext.getDataSource() != null) {
            return DataSourceContext.getTemplate();
        }
        if (master) {
            jdbcTemplate = jdbcTemplateThreadLocal.get();
            if (jdbcTemplate == null) {
                jdbcTemplate = dataSource();
            }
            return jdbcTemplate;
        } else {
            DataSource dataSource = slaveDataSourceThreadLocal.get();
            if (dataSource == null) {
                return dataSource();
            }
            String slaveKey = getSlaveKey(dataSource);
            jdbcTemplate = dataSourceSalveTemplateMap.computeIfAbsent(slaveKey, key -> new NamedParameterJdbcTemplate(dataSource));
        }
        return jdbcTemplate;
    }

    private static String getSlaveKey(DataSource dataSource) {
        if (dataSource instanceof DruidDataSource) {
            DruidDataSource druidDataSource = (DruidDataSource) dataSource;
            String druidKey = buildStableKey(druidDataSource.getUsername(), druidDataSource.getPassword(), druidDataSource.getUrl());
            if (StrUtil.isNotBlank(druidKey)) {
                return druidKey;
            }
        }

        String methodUsername = invokeStringGetter(dataSource, "getUsername");
        String methodPassword = invokeStringGetter(dataSource, "getPassword");
        String methodUrl = invokeStringGetter(dataSource, "getJdbcUrl");
        if (StrUtil.isBlank(methodUrl)) {
            methodUrl = invokeStringGetter(dataSource, "getUrl");
        }
        String methodKey = buildStableKey(methodUsername, methodPassword, methodUrl);
        if (StrUtil.isNotBlank(methodKey)) {
            return methodKey;
        }

        Class<? extends DataSource> dataSourceClass = dataSource.getClass();
        Field[] fields = dataSourceClass.getDeclaredFields();
        String username = null;
        String password = null;
        String jdbcUrl = null;
        for (Field field : fields) {
            // 设置字段可访问， 否则无法访问private修饰的变量值
            field.setAccessible(true);
            try {
                // 获取字段名称
                String fieldName = field.getName();

                // 获取指定对象的当前字段的值
                Object fieldVal = field.get(dataSource);
                if (StrUtil.equals(fieldName, "username")) {
                    username = fieldVal + "";
                }
                if (StrUtil.equals(fieldName, "password")) {
                    password = fieldVal + "";
                }
                if (StrUtil.equals(fieldName, "jdbcUrl")) {
                    jdbcUrl = fieldVal + "";
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        String reflectionKey = buildStableKey(username, password, jdbcUrl);
        if (StrUtil.isNotBlank(reflectionKey)) {
            return reflectionKey;
        }

        // fallback to avoid empty key collision across different data sources
        return dataSource.getClass().getName() + "@" + System.identityHashCode(dataSource);
    }

    private static String invokeStringGetter(DataSource dataSource, String methodName) {
        try {
            Method method = dataSource.getClass().getMethod(methodName);
            Object value = method.invoke(dataSource);
            return value == null ? null : String.valueOf(value);
        } catch (Exception ignore) {
            return null;
        }
    }

    private static String buildStableKey(String username, String password, String jdbcUrl) {
        if (StrUtil.isNotBlank(username) && StrUtil.isNotBlank(password) && StrUtil.isNotBlank(jdbcUrl)) {
            return new StrBuilder(username).append(password).append(jdbcUrl).toString();
        }
        return null;
    }

    public static NamedParameterJdbcTemplate dataSource() {
        DataSource dataSource = getMasterDataSource();
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        jdbcTemplateThreadLocal.set(jdbcTemplate);
        return jdbcTemplate;
    }

    public static DataSource getDataSource(){
        FastMapperParam<?> fastMapperParam = FastMapperParam.get();
        Boolean master = fastMapperParam.getMaster();
        if (master) {
            return getMasterDataSource();
        }
        return slaveDataSourceThreadLocal.get();
    }

    public static void clearThreadLocal(){
        jdbcTemplateThreadLocal.remove();
    }

    public static void setSlaveDataSource(DataSource dataSource) {
        FastMapperParam.get().setMaster(false);
        slaveDataSourceThreadLocal.set(dataSource);
    }

    public static void clearSlaveThreadLocal(){
        slaveDataSourceThreadLocal.remove();
    }

    private static DataSource getMasterDataSource() {
        DataSource master = FastMapperConfig.dataSourceMaster.get();
        if (master == null) {
            master = SpringUtil.getBean(DataSource.class);
            FastMapperConfig.dataSourceMaster.set(master);
        }
        return master;
    }

    @SuppressWarnings("unchecked")
    private static Class<? extends DaoActuator<?>> daoActuator =
            (Class<? extends DaoActuator<?>>) (Class<?>) JdbcDaoActuator.class;

    public static void setDaoActuator(Class<? extends DaoActuator<?>> daoActuator) {
        DataSourceConnection.daoActuator = daoActuator;
    }

    public static DaoActuator<?> getDaoActuator() {
        try {
            return ProxyUtil.proxy(daoActuator.getDeclaredConstructor().newInstance(), MapperActuatorAspect.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
