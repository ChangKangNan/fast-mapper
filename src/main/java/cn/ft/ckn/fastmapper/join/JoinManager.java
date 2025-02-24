package cn.ft.ckn.fastmapper.join;

import cn.ft.ckn.fastmapper.bean.FastMapperParam;
import cn.ft.ckn.fastmapper.bean.db.TableMapper;
import cn.ft.ckn.fastmapper.bean.em.Expression;
import cn.ft.ckn.fastmapper.support.dao.DaoActuator;
import cn.ft.ckn.fastmapper.support.dao.jdbc.DataSourceConnection;
import cn.ft.ckn.fastmapper.util.log.LogUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ArrayUtil;
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
        FastMapperParam.init(new TableMapper<>());
        this.params = params;
        daoActuator = DataSourceConnection.getDaoActuator();
    }

   private StringBuilder getSQL(){
       List<String> tables = new ArrayList<>();
       if (MapUtil.isNotEmpty(params.deeps)) {
           int deep = 1;
           int size = 0;
           while (size < params.deeps.size()) {
               int i = 0;
               for (String s : params.deeps.keySet()) {
                   i++;
                   Integer dep = params.deeps.get(s);
                   if (dep == deep) {
                       size++;
                       tables.add(s);
                   }
               }
               if (i == params.deeps.size()) {
                   deep++;
               }
           }
       }

       String columns = "";
       if(CollUtil.isNotEmpty(params.columns)){
           columns = StrUtil.join(",", params.columns.stream().map(column -> {
               String res = column;
               for (String k : params.aliasMap.keySet()) {
                   String t = res.substring(0, res.indexOf(StrUtil.DOT));
                   String field = res.substring(res.indexOf(StrUtil.DOT) + 1);
                   if (StrUtil.equals(res, k + StrUtil.DOT + field)) {
                       String alias = params.aliasMap.get(t);
                       res = alias + StrUtil.DOT + field;
                       break;
                   }
               }
               return res;
           }).toArray());
       }

       StringBuilder sqlBuilder = new StringBuilder(SELECT)
               .append(Expression.LineSeparator.expression)
               .append(ArrayUtil.isEmpty(params.columns) ? "*" : columns)
               .append(Expression.LineSeparator.expression)
               .append(FROM)
               .append(StrUtil.SPACE)
               .append(params.mainTable)
               .append(Expression.LineSeparator.expression);
       if (ArrayUtil.isNotEmpty(tables)) {
           for (String table : tables) {
               Map<String, String> map = params.joins.get(table);
               String r = params.relation.get(table);
               sqlBuilder.append(r)
                       .append(StrUtil.SPACE)
                       .append(table)
                       .append(StrUtil.SPACE)
                       .append("ON")
                       .append(StrUtil.SPACE);
               int i = 0;
               for (String link : map.keySet()) {
                   i++;
                   if (i != 1) {
                       sqlBuilder.append(AND);
                   }
                   String s = map.get(link);
                   sqlBuilder.append(link)
                           .append(Expression.Equal.expression)
                           .append(s)
                           .append(StrUtil.SPACE);
               }
               sqlBuilder.append(Expression.LineSeparator.expression);
           }
       }
       if (MapUtil.isNotEmpty(params.where)) {
           sqlBuilder.append(WHERE);
           sqlBuilder.append(StrUtil.SPACE);
           int i = 0;
           for (String key : params.where.keySet()) {
               i++;
               if (i != 1) {
                   sqlBuilder.append(Expression.LineSeparator.expression);
                   sqlBuilder.append(AND);
                   sqlBuilder.append(StrUtil.SPACE);
               }
               Object obj = params.where.get(key);
               String where = key.substring(0,key.indexOf(StrUtil.DOT));
               String field = key.substring(key.indexOf(StrUtil.DOT)+1);
               for (String k : params.aliasMap.keySet()) {
                   if (StrUtil.equals(where, StrUtil.SPACE + k + StrUtil.DOT)) {
                       String alias = params.aliasMap.get(k);
                       where = alias + StrUtil.DOT+field;
                   }
               }
               sqlBuilder.append(key)
                       .append(Expression.Equal.expression)
                       .append(LogUtil.getValue(obj))
                       .append(StrUtil.SPACE);
           }
       }
       return sqlBuilder;
    }

    public <X> List<X> find(Class<X> returnObj) {
        Map<String, Object> parameters = prepareFind();
        FastMapperParam.get().getTableMapper().setObjClass(returnObj);
        FastMapperParam.get().setParamMap(parameters);
        return (List<X>)daoActuator.select();
    }

    private Map<String, Object> prepareFind() {
        Map<String, Object> parameters = new HashMap<>();
        if (!params.lastWhereParameters.isEmpty()) {
            parameters = params.lastWhereParameters;
        }
        StringBuilder sql = getSQL();
        if (StrUtil.isNotBlank(params.lastSQL)) {
            sql.append(System.lineSeparator());
            sql.append(WHERE);
            sql.append(StrUtil.SPACE);
            sql.append(params.lastSQL);
        }
        FastMapperParam.get().setExecuteSql(sql.toString());
        return parameters;
    }

    public List<Map<String, Object>> find(){
        Map<String, Object> parameters = prepareFind();
        FastMapperParam.get().setParamMap(parameters);
        return daoActuator.selectList();
    }
}
