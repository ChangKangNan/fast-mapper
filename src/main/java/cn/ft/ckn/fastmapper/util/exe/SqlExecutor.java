package cn.ft.ckn.fastmapper.util.exe;

import cn.ft.ckn.fastmapper.bean.FastMapperParam;
import cn.ft.ckn.fastmapper.bean.FastTableMapper;
import cn.ft.ckn.fastmapper.support.dao.DaoActuator;
import cn.ft.ckn.fastmapper.support.dao.jdbc.DataSourceConnection;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import lombok.SneakyThrows;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 自定义sql查询与更新
 * 门面模式
 * @author ckn
 */
public class SqlExecutor {
    private DaoActuator daoActuator;
    private static SqlExecutor sqlExecutorUtil = new SqlExecutor();

    private SqlExecutor() {
        this.daoActuator = DataSourceConnection.getDaoActuator();
        FastTableMapper tableMapper = new FastTableMapper();
        FastMapperParam.init(tableMapper);
    }

    public static SqlExecutor build() {
        return sqlExecutorUtil;
    }

    public <R> List<R> select(String sql, HashMap<String, Object> params, Class<R> returnObj) {
        FastMapperParam.get().setExecuteSql(sql);
        FastMapperParam.get().setParamMap(params);
        FastMapperParam.get().getTableMapper().setObjClass(returnObj);
        return daoActuator.select();
    }

    public List<Map<String, Object>> select(String sql, HashMap<String, Object> params) {
        FastMapperParam.get().setExecuteSql(sql);
        FastMapperParam.get().setParamMap(params);
        return daoActuator.selectList();
    }

    public <R> List<R> select(String sql, Class<R> returnObj) {
        return select(sql,new HashMap<>(),returnObj);
    }

    /**
     * 通过 sql文件运行获得返回结果
     */
    public <E> List<E> selectByFile(String filePath,HashMap<String, Object> parameters,Class<E> rowMapperClass) {
        //处理参数
        ClassPathResource resource = new ClassPathResource(filePath);
        String sql = IoUtil.read(resource.getStream()).toString();
        return select(sql,parameters,rowMapperClass);
    }

    /**
     * 自定义sql执行
     */
    public int execute(String sql, HashMap<String, Object> parameters) {
        FastMapperParam.get().setExecuteSql(sql);
        FastMapperParam.get().setParamMap(parameters);
        return daoActuator.update();
    }

    /**
     * 自定义sql执行
     */
    public int execute(String sql) {
        FastMapperParam.get().setExecuteSql(sql);
        FastMapperParam.get().setParamMap(new HashMap<>());
        return daoActuator.update();
    }

    @SneakyThrows
    public void executeBatch(List<String> sqls) {
        if (CollUtil.isEmpty(sqls)) {
            return;
        }
        Connection connection = DataSourceConnection.getDataSource().getConnection();
        Statement statement = connection.createStatement();
        for (String batch : sqls) {
            statement.execute(batch);
        }
    }

    public void setSalveDataSource(DataSource dataSource) {
        DataSourceConnection.setSlaveDataSource(dataSource);
    }
}
