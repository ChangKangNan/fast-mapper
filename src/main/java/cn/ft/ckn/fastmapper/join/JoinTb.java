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
public class JoinTb {
    private final JoinParams params;

    public JoinTb(JoinParams params) {
        this.params = params;
    }

    public <X> JoinWhere select(List<SFunction<X, ?>> fields) {
        if (CollUtil.isNotEmpty(fields)) {
            for (SFunction field : fields) {
                String fieldName = ColumnUtil.getFieldName(field);
                String className = ColumnUtil.getClassName(field);
                params.columns.add(className + StrUtil.DOT + fieldName);
            }
        }
        return new JoinWhere(params);
    }

    public <X, Y> JoinWhere select(SFunction<X, Y> field) {
        String fieldName = ColumnUtil.getFieldName(field);
        String className = ColumnUtil.getClassName(field);
        params.columns.add(className + StrUtil.DOT + fieldName);
        return new JoinWhere(params);
    }

    public <W, V> JoinWhere where(SFunction<W, V> field, V o) {
        String fieldName = ColumnUtil.getFieldName(field);
        String className = ColumnUtil.getClassName(field);
        String as = params.aliasMap.get(className);
        this.params.where.put(StrUtil.SPACE + (StrUtil.isNotBlank(as) ? as : className) + StrUtil.DOT + fieldName, o);
        return new JoinWhere(params);
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

    public <R> List<R> find(Class<R> returnObj) {
        return new JoinCustomer(params).find(returnObj);
    }

    public List<Map<String, Object>> find() {
        return new JoinCustomer(params).find();
    }

    public <R> R findOne(Class<R> returnObj) {
        List<R> res = new JoinCustomer(params).find(returnObj);
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

    /**
     * case: tb.k= #{key}   map:{key:"value"}
     */
    public JoinTb lastWhere(String sql, Map<String, Object> parameters) {
        params.lastSQL = sql;
        params.lastWhereParameters = parameters;
        return this;
    }

}
