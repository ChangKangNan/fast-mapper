package cn.ft.ckn.fastmapper.support.dao;

import cn.ft.ckn.fastmapper.bean.FastMapperParam;
import cn.ft.ckn.fastmapper.bean.FastTableMapper;
import cn.ft.ckn.fastmapper.config.FastMapperConfig;
import cn.ft.ckn.fastmapper.support.dao.jdbc.DataSourceConnection;
import cn.ft.ckn.fastmapper.util.sql.PackageSqlUtil;
import cn.hutool.core.util.StrUtil;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @author ckn
 * @date 2022/7/28
 */
public class DeleteDao<T,R> extends BaseDao<R>{
    private Class<R> r;
    private final DaoActuator<T> daoActuator;

    public DeleteDao(Class<R> r,Class<T> classObj) {
        FastTableMapper.init(classObj);
        this.r=r;
        daoActuator = DataSourceConnection.getDaoActuator();
    }

    public R closeDeletedProtect(){
        FastMapperParam.get().setCloseDeleteProtect(true);
        return (R) this;
    }

    public Integer delete(){
        //是否包含逻辑删除字段
        Class objClass = FastMapperParam.get().getTableMapper().getObjClass();
        if((!FastMapperParam.get().getCloseDeleteProtect()) && StrUtil.isNotBlank(FastMapperConfig.logicDeletedColumn) && PackageSqlUtil.hasField(objClass,FastMapperConfig.logicDeletedColumn)){
            FastMapperParam.get().getUpdateValueList().add(new FastMapperParam.Value(FastMapperConfig.logicDeletedColumn, FastMapperConfig.logicDeletedColumnDeletedValue));
            return daoActuator.update();
        }
      return daoActuator.delete();
    }

    public R or() {
        FastMapperParam.get().isAnd = false;
        return (R) this;
    }
    private R and() {
        FastMapperParam.get().isAnd = true;
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

    public R andSql(Consumer<R> consumer) {
        and();
        bracketPrefix();
        consumer.accept((R) this);
        bracketSuffix();
        return (R)this;
    }

    public R andSql(String sql, Map<String,Object> params) {
        and();
        bracketPrefix();
        FastMapperParam.get().getWhereCondition().add(new FastMapperParam.WhereCondition(sql, params, FastMapperParam.get().isAnd));
        bracketSuffix();
        return (R)this;
    }


    public R orSql(Consumer<R> consumer) {
        or();
        bracketPrefix();
        consumer.accept((R) this);
        bracketSuffix();
        return (R)this;
    }

    public R orSql(String sql, Map<String, Object> params) {
        or();
        bracketPrefix();
        FastMapperParam.get().getWhereCondition().add(new FastMapperParam.WhereCondition(sql, params, FastMapperParam.get().isAnd));
        bracketSuffix();
        return (R) this;
    }

}
