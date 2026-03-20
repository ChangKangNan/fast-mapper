package cn.ft.ckn.fastmapper.support.dao.jdbc;

import cn.ft.ckn.fastmapper.bean.FastMapperParam;
import cn.ft.ckn.fastmapper.bean.FastTableMapper;
import com.alibaba.druid.pool.DruidDataSource;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DataSourceConnectionTest {

    @Test
    public void slaveKeyUsesStableDruidProperties() throws Exception {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername("u");
        dataSource.setPassword("p");
        dataSource.setUrl("jdbc:mysql://localhost:3306/db");

        String key = invokeGetSlaveKey(dataSource);
        Assert.assertTrue(key.contains("u"));
        Assert.assertTrue(key.contains("p"));
        Assert.assertTrue(key.contains("jdbc:mysql://localhost:3306/db"));
    }

    @Test
    public void slaveKeyFallsBackToIdentityWhenNoMetadataAvailable() throws Exception {
        DataSource ds1 = new EmptyDataSource();
        DataSource ds2 = new EmptyDataSource();

        String key1 = invokeGetSlaveKey(ds1);
        String key2 = invokeGetSlaveKey(ds2);

        Assert.assertNotEquals(key1, key2);
        Assert.assertTrue(key1.contains("@"));
        Assert.assertTrue(key2.contains("@"));
    }

    @Test
    public void jdbcTemplateCacheReusesSameSlaveDataSource() {
        DataSourceConnection.dataSourceSalveTemplateMap.clear();
        FastMapperParam.init(new FastTableMapper<>());

        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername("u");
        dataSource.setPassword("p");
        dataSource.setUrl("jdbc:mysql://localhost:3306/db");

        DataSourceConnection.setSlaveDataSource(dataSource);
        NamedParameterJdbcTemplate first = DataSourceConnection.getJdbcTemplate();
        NamedParameterJdbcTemplate second = DataSourceConnection.getJdbcTemplate();

        Assert.assertSame(first, second);

        DataSourceConnection.clearSlaveThreadLocal();
        DataSourceConnection.dataSourceSalveTemplateMap.clear();
    }

    private String invokeGetSlaveKey(DataSource dataSource) throws Exception {
        Method method = DataSourceConnection.class.getDeclaredMethod("getSlaveKey", DataSource.class);
        method.setAccessible(true);
        return (String) method.invoke(null, dataSource);
    }

    private static class EmptyDataSource implements DataSource {
        @Override
        public Connection getConnection() throws SQLException {
            throw new UnsupportedOperationException();
        }

        @Override
        public Connection getConnection(String username, String password) throws SQLException {
            throw new UnsupportedOperationException();
        }

        @Override
        public <T> T unwrap(Class<T> iface) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean isWrapperFor(Class<?> iface) {
            return false;
        }

        @Override
        public java.io.PrintWriter getLogWriter() {
            return null;
        }

        @Override
        public void setLogWriter(java.io.PrintWriter out) {
        }

        @Override
        public void setLoginTimeout(int seconds) {
        }

        @Override
        public int getLoginTimeout() {
            return 0;
        }

        @Override
        public Logger getParentLogger() {
            return Logger.getGlobal();
        }
    }
}
