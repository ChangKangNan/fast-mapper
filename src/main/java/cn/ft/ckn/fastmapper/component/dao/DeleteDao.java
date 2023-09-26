package cn.ft.ckn.fastmapper.component.dao;

import cn.ft.ckn.fastmapper.bean.DaoActuator;
import cn.ft.ckn.fastmapper.bean.SearchParam;
import cn.ft.ckn.fastmapper.bean.TableMapper;
import cn.ft.ckn.fastmapper.component.dao.jdbc.DataSourceConnection;

import javax.sql.DataSource;
import java.util.List;

/**
 * @author ckn
 * @date 2022/7/28
 */
public class DeleteDao<T,R>{
    private Class<R> r;
    private final DaoActuator<T> daoActuator;

    public DeleteDao(Class<R> r,Class<T> classObj) {
        TableMapper.init(classObj);
        this.r=r;
        daoActuator = DataSourceConnection.getDaoActuator();
    }

    public R closeDeletedProtect(){
        SearchParam.get().setCloseDeleteProtect(true);
        return (R) this;
    }

    public Integer delete(){
      return daoActuator.delete();
    }

    public R setSalveDataSource(DataSource dataSource){
        SearchParam searchParam = SearchParam.get();
        searchParam.setMaster(false);
        DataSourceConnection.setSlaveDataSource(dataSource);
        return (R)this;
    }

    public R bracketPrefix() {
        SearchParam.get().setBracket(SearchParam.Bracket.builder().leftIndex(SearchParam.get().getWhereCondition().size()).build());
        return (R)this;
    }

    public R bracketSuffix() {
        List<SearchParam.Bracket> brackets = SearchParam.get().getBrackets();
        for (int i = brackets.size() - 1; i >= 0; i--) {
            if (brackets.get(i).getRightIndex() != null) {
                continue;
            }
            SearchParam.Bracket bracket = brackets.get(i);
            bracket.setRightIndex(SearchParam.get().getWhereCondition().size()-1);
            SearchParam.get().setBracket(bracket, i);
        }
        return (R)this;
    }
}
