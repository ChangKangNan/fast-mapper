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

    public JoinWhere select(SFunction<?, ?> field) {
        String fieldName = ColumnUtil.getFieldName(field);
        String className = ColumnUtil.getClassName(field);
        params.columns.add(className + StrUtil.DOT + fieldName);
        return new JoinWhere(params);
    }

    public JoinWhere select(List<SFunction<?, ?>> fields) {
        if (CollUtil.isNotEmpty(fields)) {
            for (SFunction<?, ?> field : fields) {
                String fieldName = ColumnUtil.getFieldName(field);
                String className = ColumnUtil.getClassName(field);
                params.columns.add(className + StrUtil.DOT + fieldName);
            }
        }
        return new JoinWhere(params);
    }

    public <V> JoinWhere where(SFunction<?, V> field, V o) {
        String fieldName = ColumnUtil.getFieldName(field);
        String className = ColumnUtil.getClassName(field);
        String as = params.aliasMap.get(className);
        this.params.where.put(StrUtil.SPACE + (StrUtil.isNotBlank(as) ? as : className) + StrUtil.DOT + fieldName, o);
        return new JoinWhere(params);
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

    public <R> List<R> find(Class<R> returnObj) {
        return new JoinCustomer(params).find(returnObj);
    }

    public List<Map<String, Object>> find(){
        return new JoinCustomer(params).find();
    }


    /**
     * case: tb.k=:key   map:{key:"value"}
     */
    public JoinTb lastWhere(String sql, Map<String, Object> parameters) {
        params.lastSQL = sql;
        params.lastWhereParameters = parameters;
        return this;
    }

}
