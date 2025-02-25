package cn.ft.ckn.fastmapper.join;

import cn.ft.ckn.fastmapper.anno.SFunction;
import cn.ft.ckn.fastmapper.util.db.ColumnUtil;
import cn.hutool.core.collection.CollUtil;
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

    public JoinWhere where(SFunction column, Object o) {
        String fieldName = ColumnUtil.getFieldName(column);
        String className = ColumnUtil.getClassName(column);
        String as = params.aliasMap.get(className);
        this.params.where.put(StrUtil.SPACE + (StrUtil.isNotBlank(as) ? as : className) + StrUtil.DOT + fieldName, o);
        return this;
    }

    public <L, K, W> JoinTb leftJoin(Class<L> joinClass, SFunction<W, K> mainKey, SFunction<L, K> joinKey) {
        return new JoinCustomer(params).leftJoin(joinClass, mainKey, joinKey);
    }

    public <H, K, W> JoinTb rightJoin(Class<H> joinClass, SFunction<W, K> mainKey, SFunction<H, K> joinKey) {
        return new JoinCustomer(params).leftJoin(joinClass, mainKey, joinKey);
    }

    public <I, K, W> JoinTb innerJoin(Class<I> joinClass, SFunction<W, K> mainKey, SFunction<I, K> joinKey) {
        return new JoinCustomer(params).leftJoin(joinClass, mainKey, joinKey);
    }

    /**
     * case: tb.k= #{key}   map:{key:"value"}
     */
    public JoinCustomer lastWhere(String sql, Map<String, Object> parameters) {
        params.lastSQL = sql;
        params.lastWhereParameters = parameters;
        return new JoinCustomer(params);
    }

    public <X> List<X> find(Class<X> returnObj) {
        return new JoinCustomer(params).find(returnObj);
    }

    public List<Map<String, Object>> find() {
        return new JoinCustomer(params).find();
    }

    public <X> X findOne(Class<X> returnObj) {
        List<X> res = new JoinCustomer(params).find(returnObj);
        if (CollUtil.isNotEmpty(res)) {
            return res.get(0);
        }
        return null;
    }

    public Map<String, Object> findOne() {
        List<Map<String, Object>> mapList = new JoinCustomer(params).find();
        if (CollUtil.isNotEmpty(mapList)) {
            return mapList.get(0);
        }
        return null;
    }
}
