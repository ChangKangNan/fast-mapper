package cn.ft.ckn.fastmapper.support.dao;

import cn.ft.ckn.fastmapper.bean.FastMapperParam;
import cn.ft.ckn.fastmapper.support.dao.jdbc.DataSourceConnection;

import javax.sql.DataSource;
import java.util.List;

public class BaseDao<R> {

    public R bracketPrefix() {
        FastMapperParam.get().setBracket(FastMapperParam.Bracket.builder().leftIndex(FastMapperParam.get().getWhereCondition().size()).build());
        return (R)this;
    }

    public R bracketSuffix() {
        List<FastMapperParam.Bracket> brackets = FastMapperParam.get().getBrackets();
        for (int i = brackets.size() - 1; i >= 0; i--) {
            if (brackets.get(i).getRightIndex() != null) {
                continue;
            }
            FastMapperParam.Bracket bracket = brackets.get(i);
            bracket.setRightIndex(FastMapperParam.get().getWhereCondition().size()-1);
            FastMapperParam.get().setBracket(bracket, i);
        }
        return (R)this;
    }

    public R setSalveDataSource(DataSource dataSource){
        FastMapperParam fastMapperParam = FastMapperParam.get();
        fastMapperParam.setMaster(false);
        DataSourceConnection.setSlaveDataSource(dataSource);
        return (R)this;
    }
}
