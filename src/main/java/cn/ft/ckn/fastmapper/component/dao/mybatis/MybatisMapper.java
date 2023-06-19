package cn.ft.ckn.fastmapper.component.dao.mybatis;

import cn.ft.ckn.fastmapper.bean.SearchParam;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @author ckn
 * @date 2023/6/19
 */
public interface MybatisMapper {
    @InsertProvider(type=MybatisSqlProvider.class,method = "getSql")
    Integer insert(SearchParam searchParam);
    @InsertProvider(type=MybatisSqlProvider.class,method = "getSql")
    @Options(useGeneratedKeys = true,keyProperty = "returnVal")
    Integer insertPrimaryKeyAuto(SearchParam searchParam);
    @SelectProvider(type=MybatisSqlProvider.class,method = "getSql")
    Integer count(SearchParam searchParam);
    @DeleteProvider(type=MybatisSqlProvider.class,method = "getSql")
    Integer delete(SearchParam searchParam);
    @UpdateProvider(type=MybatisSqlProvider.class,method = "getSql")
    Integer update(SearchParam searchParam);
    @SelectProvider(type=MybatisSqlProvider.class,method = "getSql")
    List<Map<String,Object>> select(SearchParam searchParam);
}
