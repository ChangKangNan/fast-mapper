package cn.ft.ckn.fastmapper.util;

import io.netty.util.concurrent.FastThreadLocal;

import javax.sql.DataSource;

/**
 * 数据源上下文
 *
 * @author ckn
 * @date 2022/11/4
 */
public class DataSourceContext {
    private static FastThreadLocal<DataSource> sourceFastThreadLocal = new FastThreadLocal<>();

    /**
     * 获取当前线程数据源
     * @return
     */
    public static DataSource getDataSource() {
        return sourceFastThreadLocal.get();
    }

    /**
     * 当前线程绑定数据源
     *
     * @param dataSource
     */
    public  static void bind(DataSource dataSource) {
        sourceFastThreadLocal.set(dataSource);
    }

    /**
     * 当前线程解绑数据源
     */
    public  static void unBind() {
        sourceFastThreadLocal.remove();
    }
}
