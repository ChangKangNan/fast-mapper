package cn.ft.ckn.fastmapper.join;

import cn.ft.ckn.fastmapper.anno.SFunction;
import cn.ft.ckn.fastmapper.util.db.ColumnUtil;
import cn.hutool.core.util.StrUtil;

import java.util.List;
import java.util.Map;

/**
 * @author ckn
 */
public class JoinWhere {
    private final JoinParams params;

    public JoinWhere(JoinParams params) {
        this.params = params;
    }

    public JoinWhere where(SFunction<?, ?> column, Object o) {
        String fieldName = ColumnUtil.getFieldName(column);
        String className = ColumnUtil.getClassName(column);
        this.params.where.put(className + StrUtil.DOT + fieldName, o);
        return this;
    }

    public <L, K> JoinTb leftJoin(Class<L> joinClass, SFunction<?, K> mainKey, SFunction<L, K> joinKey) {
        return new JoinCustomer(params).leftJoin(joinClass, mainKey, joinKey);
    }

    public <H, K> JoinTb rightJoin(Class<H> joinClass, SFunction<?, K> mainKey, SFunction<H, K> joinKey) {
        return new JoinCustomer(params).leftJoin(joinClass, mainKey, joinKey);
    }

    public <I, K> JoinTb innerJoin(Class<I> joinClass, SFunction<?, K> mainKey, SFunction<I, K> joinKey) {
        return new JoinCustomer(params).leftJoin(joinClass, mainKey, joinKey);
    }

    /**
     * case: tb.k=:key   map:{key:"value"}
     */
    public JoinCustomer lastWhere(String sql, Map<String, Object> parameters) {
        params.lastSQL = sql;
        params.lastWhereParameters = parameters;
        return new JoinCustomer(params);
    }

    public <X> List<X> find(Class<X> returnObj) {
        return new JoinCustomer(params).find(returnObj);
    }
}
