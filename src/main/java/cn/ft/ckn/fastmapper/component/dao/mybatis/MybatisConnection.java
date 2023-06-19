package cn.ft.ckn.fastmapper.component.dao.mybatis;

import cn.ft.ckn.fastmapper.bean.SearchParam;
import cn.ft.ckn.fastmapper.component.dao.jdbc.DataSourceConnection;
import cn.ft.ckn.fastmapper.transaction.context.DataSourceContext;
import io.netty.util.concurrent.FastThreadLocal;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;

import javax.sql.DataSource;

/**
 * @author ckn
 * @date 2023/6/19
 */
public class MybatisConnection {
    private static final FastThreadLocal<MybatisMapper> mapperThreadLocal = new FastThreadLocal<>();

    public static MybatisMapper getMapper() {
        MybatisMapper myBatisMapper = mapperThreadLocal.get();
        if (myBatisMapper == null) {
            myBatisMapper = buildMapper();
        }
        return myBatisMapper;
    }

    public static MybatisMapper buildMapper() {
        DataSource dataSource ;
        if (DataSourceContext.getDataSource() != null) {
            dataSource = DataSourceContext.getDataSource();
        }else {
            dataSource=DataSourceConnection.getDataSource();
        }
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        SqlSessionTemplate template;
        try {
            template = new SqlSessionTemplate(sqlSessionFactoryBean.getObject());
            template.getConfiguration().addMapper(MybatisMapper.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        MybatisMapper mapper = template.getMapper(MybatisMapper.class);
        mapperThreadLocal.set(mapper);
        return mapper;
    }
}
