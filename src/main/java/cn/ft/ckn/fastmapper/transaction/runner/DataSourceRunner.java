package cn.ft.ckn.fastmapper.transaction.runner;

import cn.ft.ckn.fastmapper.annotation.RunGlobal;
import cn.ft.ckn.fastmapper.transaction.context.DataSourceContext;
import com.alibaba.druid.pool.DruidDataSource;

import javax.sql.DataSource;

/**
 * @author ckn
 * @date 2022/11/4
 */
public class DataSourceRunner {
    /**
     * 获取数据源
     * @param driverClassName
     * @param url
     * @param userName
     * @param password
     * @return
     */
    public static DataSource getDataSource(String driverClassName, String url, String userName, String password) {
        DruidDataSource druidDataSource = new DruidDataSource();
        try {
            druidDataSource.setDriverClassName(driverClassName);
            druidDataSource.setUrl(url);
            druidDataSource.setUsername(userName);
            druidDataSource.setPassword(password);
            druidDataSource.setConnectionErrorRetryAttempts(1);
            druidDataSource.setBreakAfterAcquireFailure(true);
            return druidDataSource;
        }catch (Exception e){
            return null;
        }
    }

    /**
     * 区域数据源操作
     * @param dataSource
     * @param runGlobal
     */
    public static void runWithDataSource(DataSource dataSource, RunGlobal runGlobal) {
        //绑定
        DataSourceContext.bind(dataSource);
        //处理业务
        runGlobal.run();
        //解绑
        DataSourceContext.unBind();
    }
}
