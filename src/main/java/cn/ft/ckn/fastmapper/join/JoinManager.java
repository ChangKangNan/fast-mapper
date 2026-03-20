package cn.ft.ckn.fastmapper.join;

import cn.ft.ckn.fastmapper.bean.FastMapperParam;
import cn.ft.ckn.fastmapper.bean.FastTableMapper;
import cn.ft.ckn.fastmapper.bean.em.Expression;
import cn.ft.ckn.fastmapper.support.dao.DaoActuator;
import cn.ft.ckn.fastmapper.support.dao.jdbc.DataSourceConnection;
import cn.ft.ckn.fastmapper.util.log.LogUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cn.ft.ckn.fastmapper.bean.constants.SQLConstants.*;

/**
 * @author ckn
 */
public class JoinManager {
    public JoinParams params;
    private final DaoActuator<?> daoActuator;

    public JoinManager(JoinParams params) {
        FastMapperParam.init(new FastTableMapper<>());
        this.params = params;
        daoActuator = DataSourceConnection.getDaoActuator();
        FastMapperParam.get().setSource(FastMapperParam.ActionSource.JOIN);
    }

    private StringBuilder getSQL() {
        List<String> tables = new ArrayList<>();
        if (MapUtil.isNotEmpty(params.deeps)) {
            int deep = 1;
            int collectedSize = 0;
            while (collectedSize < params.deeps.size()) {
                int collectedInCurrentDeep = 0;
                for (Map.Entry<String, Integer> entry : params.deeps.entrySet()) {
                    if (entry.getValue() == deep) {
                        tables.add(entry.getKey());
                        collectedInCurrentDeep++;
                    }
                }
                collectedSize += collectedInCurrentDeep;
                deep++;
            }
        }

        String columns = "";
        if (CollUtil.isNotEmpty(params.columns)) {
            columns = StrUtil.join(",", params.columns.stream().map(column -> {
                String res = column;
                String t = res.substring(0, res.indexOf(StrUtil.DOT));
                String field = res.substring(res.indexOf(StrUtil.DOT) + 1);
                boolean convertedByAlias = false;
                for (Map.Entry<String, String> aliasEntry : params.aliasMap.entrySet()) {
                    String table = aliasEntry.getKey();
                    if (StrUtil.equals(res, table + StrUtil.DOT + field)) {
                        String alias = params.aliasMap.get(t);
                        res = alias + StrUtil.DOT + StrUtil.toUnderlineCase(field);
                        convertedByAlias = true;
                        break;
                    }
                }
                if (!convertedByAlias) {
                    return StrUtil.toUnderlineCase(res);
                }
                return res;
            }).toArray());
        }

        String cols = CollUtil.isEmpty(params.columns) ? "*" : columns;
        StringBuilder sqlBuilder = new StringBuilder(SELECT)
                .append(Expression.LineSeparator.expression)
                .append(cols)
                .append(Expression.LineSeparator.expression)
                .append(FROM)
                .append(StrUtil.SPACE)
                .append(params.mainTable);
        String main_alias = params.aliasMap.get(params.mainTable);
        if (StrUtil.isNotBlank(main_alias)) {
            sqlBuilder.append(StrUtil.SPACE)
                    .append("AS")
                    .append(StrUtil.SPACE)
                    .append(main_alias);
        }
        sqlBuilder.append(Expression.LineSeparator.expression);
        if (CollUtil.isNotEmpty(tables)) {
            for (String table : tables) {
                Map<String, String> map = params.joins.get(table);
                String r = params.relation.get(table);
                sqlBuilder.append(r)
                        .append(StrUtil.SPACE)
                        .append(table);
                String alias = params.aliasMap.get(table);
                if (StrUtil.isNotBlank(alias)) {
                    sqlBuilder.append(StrUtil.SPACE)
                            .append("AS")
                            .append(StrUtil.SPACE)
                            .append(alias);
                }
                sqlBuilder.append(StrUtil.SPACE)
                        .append("ON")
                        .append(StrUtil.SPACE);
                int i = 0;
                for (Map.Entry<String, String> joinEntry : map.entrySet()) {
                    i++;
                    if (i != 1) {
                        sqlBuilder.append(AND);
                    }
                    sqlBuilder.append(StrUtil.toUnderlineCase(joinEntry.getKey()))
                            .append(Expression.Equal.expression)
                            .append(StrUtil.toUnderlineCase(joinEntry.getValue()))
                            .append(StrUtil.SPACE);
                }
                sqlBuilder.append(Expression.LineSeparator.expression);
            }
        }
        if (MapUtil.isNotEmpty(params.where)) {
            sqlBuilder.append(WHERE);
            sqlBuilder.append(StrUtil.SPACE);
            int i = 0;
            for (Map.Entry<String, Object> whereEntry : params.where.entrySet()) {
                i++;
                if (i != 1) {
                    sqlBuilder.append(Expression.LineSeparator.expression);
                    sqlBuilder.append(AND);
                    sqlBuilder.append(StrUtil.SPACE);
                }
                String key = whereEntry.getKey();
                Object obj = whereEntry.getValue();
                String where = key.substring(0, key.indexOf(StrUtil.DOT));
                String field = key.substring(key.indexOf(StrUtil.DOT) + 1);
                for (Map.Entry<String, String> aliasEntry : params.aliasMap.entrySet()) {
                    if (StrUtil.equals(where.trim(), aliasEntry.getKey())) {
                        String alias = aliasEntry.getValue();
                        where = alias + StrUtil.DOT + StrUtil.toUnderlineCase(field);
                        break;
                    }
                }
                sqlBuilder.append(where)
                        .append(Expression.Equal.expression)
                        .append(LogUtil.getValue(obj))
                        .append(StrUtil.SPACE);
            }
        }
        return sqlBuilder;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public <X> List<X> find(Class<X> returnObj) {
        Map<String, Object> parameters = prepareFind();
        FastMapperParam.get().getTableMapper().setObjClass((Class) returnObj);
        FastMapperParam.get().setParamMap(parameters);
        return (List<X>) daoActuator.select();
    }

    private Map<String, Object> prepareFind() {
        Map<String, Object> parameters = new HashMap<>();
        if (!params.lastWhereParameters.isEmpty()) {
            parameters = params.lastWhereParameters;
        }
        StringBuilder sql = getSQL();
        if (StrUtil.isNotBlank(params.lastSQL)) {
            sql.append(System.lineSeparator());
            if (StrUtil.containsIgnoreCase(sql.toString(), WHERE)) {
                sql.append(AND);
            } else {
                sql.append(WHERE);
            }
            sql.append(StrUtil.SPACE);
            sql.append(params.lastSQL);
        }
        FastMapperParam.get().setExecuteSql(sql.toString());
        return parameters;
    }

    public List<Map<String, Object>> find() {
        Map<String, Object> parameters = prepareFind();
        FastMapperParam.get().setParamMap(parameters);
        return daoActuator.selectList();
    }
}
