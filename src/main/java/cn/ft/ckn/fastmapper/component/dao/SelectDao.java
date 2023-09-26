package cn.ft.ckn.fastmapper.component.dao;

import cn.ft.ckn.fastmapper.annotation.Pager;
import cn.ft.ckn.fastmapper.bean.DaoActuator;
import cn.ft.ckn.fastmapper.bean.SearchParam;
import cn.ft.ckn.fastmapper.bean.TableMapper;
import cn.ft.ckn.fastmapper.component.dao.jdbc.DataSourceConnection;
import cn.hutool.core.collection.CollUtil;

import javax.sql.DataSource;
import java.util.List;

/**
 * @author ckn
 */
public class SelectDao<T,R> implements Pager<R> {
    private final Class<T> classObj;
    private Class<R> r;
    private final DaoActuator<T> daoActuator;

    public SelectDao(Class<R> r, Class<T> classObj) {
        TableMapper.init(classObj);
        this.classObj = classObj;
        this.r=r;
        this.daoActuator = DataSourceConnection.getDaoActuator();
    }

    public T one() {
        List<T> select = daoActuator.select();
        if(CollUtil.isEmpty(select)){
            return null;
        }
        return select.get(0);
    }

    public int count(){
        return daoActuator.count();
    }

    public List<T> list() {
       return daoActuator.select();
    }

    public R page(Integer page, Integer pageSize) {
        SearchParam.get().setOpenPage(true);
        SearchParam.get().setPage(page);
        SearchParam.get().setPageSize(pageSize);
        return (R)this;
    }

    public R or() {
        SearchParam.get().isAnd = false;
        return (R)this;
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
