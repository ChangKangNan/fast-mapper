package cn.ft.ckn.fastmapper.transaction.runner;

import cn.ft.ckn.fastmapper.transaction.context.DataSourceContext;
import org.junit.Assert;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DataSourceRunnerTest {

    @Test
    public void runWithDataSourceAlwaysUnbindsWhenExceptionThrown() {
        DataSource dataSource = new EmptyDataSource();
        RuntimeException thrown = null;
        try {
            DataSourceRunner.runWithDataSource(dataSource, () -> {
                throw new RuntimeException("boom");
            });
        } catch (RuntimeException e) {
            thrown = e;
        }
        Assert.assertNotNull(thrown);
        Assert.assertNull(DataSourceContext.getDataSource());
        Assert.assertNull(DataSourceContext.getTemplate());
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
        public <T> T unwrap(Class<T> iface) throws SQLException {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean isWrapperFor(Class<?> iface) throws SQLException {
            return false;
        }

        @Override
        public PrintWriter getLogWriter() throws SQLException {
            return null;
        }

        @Override
        public void setLogWriter(PrintWriter out) throws SQLException {
        }

        @Override
        public void setLoginTimeout(int seconds) throws SQLException {
        }

        @Override
        public int getLoginTimeout() throws SQLException {
            return 0;
        }

        @Override
        public Logger getParentLogger() {
            return Logger.getGlobal();
        }
    }
}
