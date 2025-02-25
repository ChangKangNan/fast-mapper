package cn.ft.ckn.fastmapper.join;

import cn.ft.ckn.fastmapper.anno.SFunction;
import cn.ft.ckn.fastmapper.util.db.ColumnUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;

import javax.persistence.Table;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JoinCustomer extends JoinManager {

    public JoinCustomer(Class<?> main) {
        super(new JoinParams());
        Table mainClassAnnotation = main.getAnnotation(Table.class);
        params.mainTable = (mainClassAnnotation == null ? StrUtil.toUnderlineCase(main.getSimpleName()) : mainClassAnnotation.name());
    }

    public JoinCustomer(Class<?> main, String alias) {
        super(new JoinParams());
        Table mainClassAnnotation = main.getAnnotation(Table.class);
        params.mainTable = (mainClassAnnotation == null ? StrUtil.toUnderlineCase(main.getSimpleName()) : mainClassAnnotation.name());
        params.aliasMap.putIfAbsent(params.mainTable, alias);
    }

    public JoinCustomer(JoinParams joinParams) {
        super(joinParams);
    }

    public <L, K, W> JoinTb leftJoin(Class<L> joinClass
            , SFunction<W, K> mainKey, SFunction<L, K> joinKey) {
        join(joinClass, null, mainKey, joinKey, "LEFT JOIN");
        return new JoinTb(params);
    }


    public <R, K, W> JoinTb rightJoin(Class<R> joinClass
            , SFunction<W, K> mainKey, SFunction<R, K> joinKey) {
        join(joinClass, null, mainKey, joinKey, "RIGHT JOIN");
        return new JoinTb(params);
    }

    public <I, K, W> JoinTb innerJoin(Class<I> joinClass
            , SFunction<W, K> mainKey, SFunction<I, K> joinKey) {
        join(joinClass, null, mainKey, joinKey, "INNER JOIN");
        return new JoinTb(params);
    }

    public <L, K, W> JoinTb leftJoin(Class<L> joinClass, String alias
            , SFunction<W, K> mainKey, SFunction<L, K> joinKey) {
        join(joinClass, alias, mainKey, joinKey, "LEFT JOIN");
        return new JoinTb(params);
    }

    public <R, K, W> JoinTb rightJoin(Class<R> joinClass, String alias
            , SFunction<W, K> mainKey, SFunction<R, K> joinKey) {
        join(joinClass, alias, mainKey, joinKey, "RIGHT JOIN");
        return new JoinTb(params);
    }

    public <I, K, W> JoinTb innerJoin(Class<I> joinClass, String alias
            , SFunction<W, K> mainKey, SFunction<I, K> joinKey) {
        join(joinClass, alias, mainKey, joinKey, "INNER JOIN");
        return new JoinTb(params);
    }

    private <H, I, F> void join(Class<I> joinClass, String alias, SFunction<H, F> mainKey, SFunction<I, F> joinKey, String joinTag) {
        Table annotation = joinClass.getAnnotation(Table.class);
        String tableName = (annotation == null ? StrUtil.toUnderlineCase(joinClass.getSimpleName()) : annotation.name());
        if (StrUtil.isNotBlank(alias)) {
            Map<String, String> aliasMap = params.aliasMap;
            String as = aliasMap.get(tableName);
            if (StrUtil.isNotBlank(as)) {
                throw new IllegalArgumentException("alias can not repeat!");
            }
            params.aliasMap.put(tableName, alias);
        }
        String kName = ColumnUtil.getFieldName(mainKey);
        String vName = ColumnUtil.getFieldName(joinKey);
        String tb_name = ColumnUtil.getClassName(mainKey);
        String as = params.aliasMap.get(tb_name);
        params.joins.put(tableName, new HashMap<String, String>() {{
            put((StrUtil.isNotBlank(as) ? as : tb_name) + StrUtil.DOT + kName, (StrUtil.isNotBlank(alias) ? alias : tableName) + StrUtil.DOT + vName);
        }});
        params.relation.put(tableName, joinTag);
        params.deeps.put(tableName, 1);
    }

    public <X, Y> JoinCustomer select(List<SFunction<X, Y>> fields) {
        if (CollUtil.isNotEmpty(fields)) {
            for (SFunction field : fields) {
                String fieldName = ColumnUtil.getFieldName(field);
                String className = ColumnUtil.getClassName(field);
                params.columns.add(className + StrUtil.DOT + fieldName);
            }
        }
        return this;
    }

    public <X, Y> JoinCustomer select(SFunction<X, Y> field) {
        String fieldName = ColumnUtil.getFieldName(field);
        String className = ColumnUtil.getClassName(field);
        params.columns.add(className + StrUtil.DOT + fieldName);
        return this;
    }

    public <N> JoinCustomer where(SFunction<?, N> column, N o) {
        String fieldName = ColumnUtil.getFieldName(column);
        String tb_name = ColumnUtil.getClassName(column);
        String as = params.aliasMap.get(tb_name);
        this.params.where.put(StrUtil.SPACE + (StrUtil.isNotBlank(as) ? as : tb_name) + StrUtil.DOT + fieldName, o);
        return this;
    }
}
