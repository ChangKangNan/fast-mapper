package cn.ft.ckn.fastmapper.join;

import cn.ft.ckn.fastmapper.anno.SFunction;
import cn.ft.ckn.fastmapper.util.db.ColumnUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;

import javax.persistence.Table;
import java.util.HashMap;
import java.util.List;

public class JoinCustomer extends JoinManager {

    public JoinCustomer(Class<?> main) {
        super(new JoinParams());
        Table mainClassAnnotation = main.getAnnotation(Table.class);
        params.mainTable = (mainClassAnnotation == null ? StrUtil.toUnderlineCase(main.getSimpleName()) : mainClassAnnotation.name());
        params.obj = main;
    }

    public JoinCustomer(JoinParams joinParams) {
        super(joinParams);
    }

    public <L, K> JoinTb leftJoin(Class<L> joinClass
            , SFunction<?, K> mainKey, SFunction<L, K> joinKey) {
        join(joinClass, mainKey, joinKey, "LEFT JOIN");
        return new JoinTb(params);
    }


    public <R, K> JoinTb rightJoin(Class<R> joinClass
            , SFunction<?, K> mainKey, SFunction<R, K> joinKey) {
        join(joinClass, mainKey, joinKey, "RIGHT JOIN");
        return new JoinTb(params);
    }

    public <I, K> JoinTb innerJoin(Class<I> joinClass
            , SFunction<?, K> mainKey, SFunction<I, K> joinKey) {
        join(joinClass, mainKey, joinKey, "INNER JOIN");
        return new JoinTb(params);
    }

    private <H, I> void join(Class<I> joinClass, SFunction<H, ?> mainKey, SFunction<I, ?> joinKey, String joinTag) {
        Table annotation = joinClass.getAnnotation(Table.class);
        String tableName = (annotation == null ? StrUtil.toUnderlineCase(joinClass.getSimpleName()) : annotation.name());
        params.obj = joinClass;
        String kName = ColumnUtil.getFieldName(mainKey);
        String vName = ColumnUtil.getFieldName(joinKey);
        String tb_name = ColumnUtil.getClassName(mainKey);
        params.joins.put(tableName, new HashMap<String, String>() {{
            put(tb_name + StrUtil.DOT + kName, tableName + StrUtil.DOT + vName);
        }});
        params.relation.put(tableName, joinTag);
        params.deeps.put(tableName, 1);
    }

    public JoinCustomer select(SFunction<?, ?> field) {
        String fieldName = ColumnUtil.getFieldName(field);
        params.columns.add(params.mainTable + StrUtil.DOT + fieldName);
        return this;
    }

    public JoinCustomer select(List<SFunction<?, ?>> fields) {
        if (CollUtil.isNotEmpty(fields)) {
            for (SFunction<?, ?> field : fields) {
                String fieldName = ColumnUtil.getFieldName(field);
                params.columns.add(params.mainTable + StrUtil.DOT + fieldName);
            }
        }
        return this;
    }

    public <N> JoinCustomer where(SFunction<?, N> column, N o) {
        String fieldName = ColumnUtil.getFieldName(column);
        this.params.where.put(this.params.mainTable + StrUtil.DOT + fieldName, o);
        return this;
    }
}
