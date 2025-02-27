package cn.ft.ckn.fastmapper.support.dao.mybatis;

import cn.ft.ckn.fastmapper.bean.FastMapperParam;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @author ckn
 */
public interface MybatisMapper {
    @InsertProvider(type=MybatisSqlProvider.class,method = "getSql")
    Integer insert(FastMapperParam FastMapperParam);
    @InsertProvider(type=MybatisSqlProvider.class,method = "getSql")
    @Options(useGeneratedKeys = true,keyProperty = "returnVal")
    Integer insertPrimaryKeyAuto(FastMapperParam FastMapperParam);
    @SelectProvider(type=MybatisSqlProvider.class,method = "getSql")
    Integer count(FastMapperParam FastMapperParam);
    @DeleteProvider(type=MybatisSqlProvider.class,method = "getSql")
    Integer delete(FastMapperParam FastMapperParam);
    @UpdateProvider(type=MybatisSqlProvider.class,method = "getSql")
    Integer update(FastMapperParam FastMapperParam);
    @SelectProvider(type=MybatisSqlProvider.class,method = "getSql")
    List<Map<String,Object>> select(FastMapperParam FastMapperParam);
    @SelectProvider(type=MybatisSqlProvider.class,method = "getSql")
    List<Map<String,Object>> selectList(FastMapperParam FastMapperParam);
}
