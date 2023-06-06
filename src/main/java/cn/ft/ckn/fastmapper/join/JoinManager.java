package cn.ft.ckn.fastmapper.join;

import cn.ft.ckn.fastmapper.aspect.MapperActuatorAspect;
import cn.ft.ckn.fastmapper.bean.DaoActuator;
import cn.ft.ckn.fastmapper.bean.Expression;
import cn.ft.ckn.fastmapper.bean.SearchParam;
import cn.ft.ckn.fastmapper.bean.TableMapper;
import cn.ft.ckn.fastmapper.component.dao.jdbc.DataSourceConnection;
import cn.ft.ckn.fastmapper.util.SQLUtil;
import cn.hutool.aop.ProxyUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cn.ft.ckn.fastmapper.constants.SQLConstants.*;

/**
 * @author ckn
 */
public class JoinManager<T> {
    public JoinParams params;
    private final DaoActuator<T> daoActuator;

    public JoinManager(JoinParams params) {
        SearchParam.init(new TableMapper<>());
        this.params=params;
        daoActuator = ProxyUtil.proxy(DataSourceConnection.getDaoActuator(), MapperActuatorAspect.class);
    }

   private StringBuilder getSQL(){
        List<String> tables=new ArrayList<>();
        if(MapUtil.isNotEmpty(params.deeps)){
            int deep=1;
            int size=0;
            while(size<params.deeps.size()){
                int i=0;
                for (String s : params.deeps.keySet()) {
                    i++;
                    Integer dep = params.deeps.get(s);
                    if(dep==deep){
                        size++;
                        tables.add(s);
                    }
                }
                if(i==params.deeps.size()){
                    deep++;
                }
            }
        }
        StringBuilder sqlBuilder=new StringBuilder(SELECT);
        sqlBuilder.append(Expression.LineSeparator.expression);
        sqlBuilder.append(StrUtil.join(",",params.columns.toArray()));
        sqlBuilder.append(Expression.LineSeparator.expression);
        sqlBuilder.append(FROM);
        sqlBuilder.append(StrUtil.SPACE);
        sqlBuilder.append(params.mainTable);
        sqlBuilder.append(Expression.LineSeparator.expression);
        if(ArrayUtil.isNotEmpty(tables)){
            for (String table : tables) {
                Map<String, String> map = params.joins.get(table);
                String r = params.relation.get(table);
                sqlBuilder.append(r);
                sqlBuilder.append(StrUtil.SPACE);
                sqlBuilder.append(table);
                sqlBuilder.append(StrUtil.SPACE);
                sqlBuilder.append("ON");
                sqlBuilder.append(StrUtil.SPACE);
                int i=0;
                for (String link : map.keySet()) {
                    i++;
                    if(i !=1){
                        sqlBuilder.append("and");
                    }
                    String s = map.get(link);
                    sqlBuilder.append(link);
                    sqlBuilder.append(Expression.Equal.expression);
                    sqlBuilder.append(s);
                    sqlBuilder.append(StrUtil.SPACE);
                }
                sqlBuilder.append(Expression.LineSeparator.expression);
            }
        }
        if(MapUtil.isNotEmpty(params.where)){
            sqlBuilder.append(WHERE);
            sqlBuilder.append(StrUtil.SPACE);
            int i=0;
            for (String key : params.where.keySet()) {
                i++;
                if(i !=1){
                    sqlBuilder.append(Expression.LineSeparator.expression);
                    sqlBuilder.append(AND);
                    sqlBuilder.append(StrUtil.SPACE);
                }
                Object obj = params.where.get(key);
                sqlBuilder.append(key);
                sqlBuilder.append(Expression.Equal.expression);
                sqlBuilder.append(SQLUtil.getValue(obj));
                sqlBuilder.append(StrUtil.SPACE);
            }
        }
        return sqlBuilder;
    }

    public <T> List<T> find(Class<T> returnObj) {
        Map<String, Object> parameters = new HashMap<>();
        if (params.lastWhereParameters.size() > 0) {
            parameters = params.lastWhereParameters;
        }
        StringBuilder sql = getSQL();
        if (StrUtil.isNotBlank(params.lastSQL)) {
            sql.append(System.lineSeparator());
            sql.append(WHERE);
            sql.append(StrUtil.SPACE);
            sql.append(params.lastSQL);
        }
        SearchParam.get().setExecuteSql(sql.toString());
        SearchParam.get().getTableMapper().setObjClass(returnObj);
        SearchParam.get().setParamMap(parameters);
        return (List<T>)daoActuator.select();
    }
}
