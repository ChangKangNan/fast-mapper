package cn.ft.ckn.fastmapper.support.dao;

import cn.ft.ckn.fastmapper.anno.Pager;
import cn.ft.ckn.fastmapper.bean.FastMapperParam;
import cn.ft.ckn.fastmapper.bean.FastTableMapper;
import cn.ft.ckn.fastmapper.bean.page.PageInfo;
import cn.ft.ckn.fastmapper.support.dao.jdbc.DataSourceConnection;
import cn.hutool.core.collection.CollUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author ckn
 */
public class SelectDao<T,R> extends BaseDao<R> implements Pager<T> {
    private final Class<T> classObj;
    private Class<R> r;
    private final DaoActuator<T> daoActuator;

    public SelectDao(Class<R> r, Class<T> classObj) {
        FastTableMapper.init(classObj);
        this.classObj = classObj;
        this.r=r;
        this.daoActuator = DataSourceConnection.getDaoActuator();
    }

    public T one() {
        FastMapperParam.get().setLimit(1);
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

    public PageInfo<T> page(Integer page, Integer pageSize) {
        FastMapperParam.get().setOpenPage(true);
        FastMapperParam.get().setPage(page);
        FastMapperParam.get().setPageSize(pageSize);
        Integer count = daoActuator.count();
        if (count == 0) {
            return new PageInfo<T>(new ArrayList<>(), page, pageSize, count);
        }
        List<T> select = daoActuator.select();
        return new PageInfo<T>(select, page, pageSize, count);
    }

    public R or() {
        FastMapperParam.get().isAnd = false;
        return (R)this;
    }

    private R bracketPrefix() {
        FastMapperParam.get().setBracket(FastMapperParam.Bracket.builder().leftIndex(FastMapperParam.get().getWhereCondition().size()).build());
        return (R)this;
    }

    private R bracketSuffix() {
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

    public R sql(Consumer<R> consumer) {
        bracketPrefix();
        consumer.accept((R) this);
        bracketSuffix();
        return (R)this;
    }
}
