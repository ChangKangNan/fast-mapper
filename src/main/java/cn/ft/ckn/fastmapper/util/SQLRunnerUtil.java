package cn.ft.ckn.fastmapper.util;

import cn.ft.ckn.fastmapper.bean.DaoActuator;
import cn.ft.ckn.fastmapper.bean.SearchParam;
import cn.ft.ckn.fastmapper.bean.TableMapper;
import cn.ft.ckn.fastmapper.component.dao.jdbc.DataSourceConnection;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.resource.ClassPathResource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;

/**
 * 自定义sql查询与更新
 * 门面模式
 * @author ckn
 */
public class SQLRunnerUtil {
    private DaoActuator daoActuator;
    private static SQLRunnerUtil sqlExecutorUtil = new SQLRunnerUtil();

    private SQLRunnerUtil() {
        this.daoActuator = DataSourceConnection.getDaoActuator();
        TableMapper tableMapper = new TableMapper();
        SearchParam.init(tableMapper);
    }

    public static SQLRunnerUtil build() {
        return sqlExecutorUtil;
    }

    public <R> List<R> select(String sql, HashMap<String, Object> params, Class<R> returnObj) {
        SearchParam.get().setExecuteSql(sql);
        SearchParam.get().setParamMap(params);
        SearchParam.get().getTableMapper().setObjClass(returnObj);
        return daoActuator.select();
    }

    public <R> List<R> select(String sql, Class<R> returnObj) {
        SearchParam.get().setExecuteSql(sql);
        SearchParam.get().getTableMapper().setObjClass(returnObj);
        return daoActuator.select();
    }

    /**
     * 通过 sql文件运行获得返回结果
     */
    public <E> List<E> selectByFile(String filePath,HashMap<String, Object> parameters,Class<E> rowMapperClass) {
        //处理参数
        ClassPathResource resource = new ClassPathResource(filePath);
        String sql = IoUtil.read(resource.getStream()).toString();
        SearchParam.get().setExecuteSql(sql);
        SearchParam.get().setParamMap(parameters);
        SearchParam.get().getTableMapper().setObjClass(rowMapperClass);
        return daoActuator.select();
    }

    /**
     * 自定义sql执行
     */
    public int execute(String sql, HashMap<String, Object> parameters) {
        SearchParam.get().setExecuteSql(sql);
        SearchParam.get().setParamMap(parameters);
        return daoActuator.update();
    }

    /**
     * 自定义sql执行
     */
    public int execute(String sql) {
        SearchParam.get().setExecuteSql(sql);
        SearchParam.get().setParamMap(new HashMap<>());
        return daoActuator.update();
    }


    private DataSource getDataSource() {
        return DataSourceConnection.getDataSource();
    }

    public void setSalveDataSource(DataSource dataSource) {
        DataSourceConnection.setSlaveDataSource(dataSource);
    }
}
