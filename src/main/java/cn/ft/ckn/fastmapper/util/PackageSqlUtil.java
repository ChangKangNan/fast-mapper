package cn.ft.ckn.fastmapper.util;

import cn.ft.ckn.fastmapper.bean.Expression;
import cn.ft.ckn.fastmapper.bean.SearchParam;
import cn.ft.ckn.fastmapper.bean.TableMapper;
import cn.ft.ckn.fastmapper.config.FastMapperConfig;
import cn.ft.ckn.fastmapper.constants.SQLConstants;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.text.StrBuilder;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;

import java.util.*;
import java.util.stream.Collectors;

import static cn.ft.ckn.fastmapper.config.FastMapperConfig.*;

/**
 * 封装SQL工具类
 * @author ckn
 */
public class PackageSqlUtil {
    public static final String INSERT="INSERT INTO";
    public static final String SELECT="SELECT";
    public static final String DELETE="DELETE";
    public static final String UPDATE="UPDATE";
    public static final String FROM="FROM";
    public static final String SET="SET";
    public static final String EQUAL="=";
    public static final String CRLF=System.lineSeparator();
    public static final String WHERE="WHERE";
    public static final String LIKE="LIKE";
    public static final String AND="and";

    public static final String WHERE_PARAM_TYPE = "where_param_";
    public static final String UPDATE_PARAM_TYPE = "update_param_";
    public static final String INSERT_PARAM_TYPE = "insert_param_";
    public static final String PARAM_PREFIX_1 = "#{";
    public static final String PARAM_PREFIX_2 = "${";
    public static final String PARAM_SUFFIX = "} ";
    public static final String JDBC_SQL_CONVERSION_RE_RULE = "[#][{](\\w*)[}]";
    public static final String JDBC_SQL_CONVERSION_RE_RULE_2 = "[$][{](\\w*)[}]";
    public static final String JDBC_SQL_CONVERSION_RE_RESULT = ":$1";
    public static final String LEFT_BRACKETS="(";
    public static final String RIGHT_BRACKETS=")";
    public static final String VALUES="VALUES";

    public static class ParamIndex {
        private int index = 0;
        private String paramType;

        public int get() {
            return index;
        }

        public int add() {
            this.index++;
            return this.index;
        }

        public String getParamType() {
            return paramType;
        }

        public void setParamType(String paramType) {
            this.paramType = paramType;
        }
    }

    private static StrBuilder packParam(StrBuilder sqlBuilder, Map<String, Object> paramMap, Object value, ParamIndex paramIndex) {
        String paramKey = paramIndex.getParamType() + paramIndex.get();
        paramMap.put(paramKey, value);
        paramIndex.add();
        return packJdbcParam(sqlBuilder, paramKey);
    }

    private static StrBuilder packJdbcParam(StrBuilder sqlBuilder, String paramKey) {
        sqlBuilder.append(PARAM_PREFIX_2).append(paramKey).append(PARAM_SUFFIX);
        return sqlBuilder;
    }


    public static String sqlConversion(String sql) {
        if (sql.contains(PARAM_PREFIX_1)) {
            sql = ReUtil.replaceAll(sql, JDBC_SQL_CONVERSION_RE_RULE, JDBC_SQL_CONVERSION_RE_RESULT);
        }
        if (sql.contains(PARAM_PREFIX_2)) {
            sql = ReUtil.replaceAll(sql, JDBC_SQL_CONVERSION_RE_RULE_2, JDBC_SQL_CONVERSION_RE_RESULT);
        }
        return sql;
    }

    public static StrBuilder insertSql(SearchParam searchParam) {
        List insertList = searchParam.getInsertList();
        TableMapper tableMapper = searchParam.getTableMapper();
        PackageSqlUtil.ParamIndex paramIndex = new PackageSqlUtil.ParamIndex();
        paramIndex.setParamType(INSERT_PARAM_TYPE);
        List<String> fieldNames = tableMapper.getShowFields();
        HashMap<String,String> fieldToColumn = tableMapper.getFieldToColumn();
        Map<String,Object> paramMap = searchParam.getParamMap();
        StrBuilder sql = StrUtil.strBuilder(INSERT,StrUtil.SPACE, tableMapper.getTableName()).append(CRLF);
        if (insertList.size() == 1) {
            sql.append(SET);
            for (String fieldName : fieldNames) {
                Object in = insertList.get(0);
                Object fieldValue = BeanUtil.getFieldValue(in, fieldName);
                if (fieldValue != null) {
                    sql.append(fieldToColumn.get(fieldName)).append(EQUAL);
                    packParam(sql, paramMap, fieldValue, paramIndex).append(StrUtil.C_COMMA);
                }
            }
            sql.del(sql.length() - 1, sql.length());
        }else {
            sql.append(LEFT_BRACKETS);
            List<String> showFields = tableMapper.getShowFields();
            sql.append(String.join(StrUtil.COMMA, showFields));
            sql.append(RIGHT_BRACKETS).append(VALUES).append(CRLF);
            for (int i = 0; i < insertList.size(); i++) {
                sql.append(LEFT_BRACKETS);
                for (int j = 0; j < fieldNames.size(); j++) {
                    Object fieldValue = BeanUtil.getFieldValue(insertList.get(i), fieldNames.get(j));
                    packParam(sql, paramMap, fieldValue, paramIndex);
                    if (i < fieldNames.size() - 1) {
                        sql.append(StrUtil.C_COMMA);
                    }
                }
                sql.append(RIGHT_BRACKETS);
                if (i < insertList.size() - 1) {
                    sql.append(StrUtil.COMMA);
                }
                sql.append(CRLF);
            }
        }
        return sql;
    }

    public static StrBuilder deleteSql(SearchParam searchParam) {
        TableMapper tableMapper = searchParam.getTableMapper();
        String logicDeletedColumn= FastMapperConfig.logicDeletedColumn;
        List<String> showFields = tableMapper.getShowFields();
        long count = showFields.stream().map(t -> t.equals(logicDeletedColumn)).count();
        //是否关闭逻辑删除
        Boolean closeDeleteProtect = searchParam.getCloseDeleteProtect();
        StrBuilder sql = StrUtil.strBuilder();
        if (closeDeleteProtect || count == 0) {
            sql.append(DELETE).append(StrUtil.SPACE).append(FROM);
        } else {
            sql.append(UPDATE);
        }
        sql.append(StrUtil.SPACE).append(tableMapper.getTableName());
        sql.append(CRLF);
        if(!closeDeleteProtect && count>0){
            sql.append(SET).append(StrUtil.SPACE);
            sql.append(logicDeletedColumn).append(EQUAL).append(logicDeletedColumnDeletedValue);
        }
        return sql;
    }

    public static StrBuilder whereSql(StrBuilder sql, SearchParam searchParam) {
        PackageSqlUtil.ParamIndex paramIndex = new PackageSqlUtil.ParamIndex();
        paramIndex.setParamType(WHERE_PARAM_TYPE);
        List<SearchParam.WhereCondition> whereConditions = searchParam.getWhereCondition();
        Map<String,Object> paramMap = searchParam.getParamMap();

        if(CollUtil.isEmpty(whereConditions)){
            return sql;
        }

        String primaryKey = searchParam.getTableMapper().getPrimaryKey();

        long existPk = whereConditions.stream().map(whereCondition -> whereCondition.columnName.equals(primaryKey)).count();
        boolean ignorePk = existPk > 0;

        List<String> showFields = searchParam.getTableMapper().getShowFields();
        long isLdc = showFields.stream().map(t -> t.equals(logicDeletedColumn)).count();
        if ((!searchParam.getCloseDeleteProtect()) && (!(isLdc > 0)) && ignorePk) {
            whereConditions.add(new SearchParam.WhereCondition(FastMapperConfig.logicDeletedColumn,logicDeletedColumnDefaultValue,EQUAL,true));
        }
        sql.append(CRLF);
        sql.append(WHERE).append(StrUtil.SPACE);
        for (int i = 0; i < whereConditions.size(); i++) {
            SearchParam.WhereCondition whereCondition = whereConditions.get(i);
            if (i != 0 && whereCondition.isAnd) {
                sql.append(CRLF);
                sql.append(AND);
                sql.append(StrUtil.SPACE);
            }
            sql.append(whereCondition.columnName);
            sql.append(whereCondition.expression);
            if (!Expression.Like.expression.equals(whereCondition.expression)) {
                if (StrUtil.equalsAny(whereCondition.expression, Expression.In.expression, Expression.NotIn.expression)) {
                    if (ArrayUtil.isArray(whereCondition.value)) {
                        sql.append(LEFT_BRACKETS);
                        Object[] whereConditionValues = (Object[]) whereCondition.value;
                        List<Object> values = new ArrayList<>();
                        for (Object o : whereConditionValues) {
                            if (o instanceof Collection) {
                                values.addAll((Collection) o);
                            } else {
                                values.add(o);
                            }
                        }
                        values = values.stream().distinct().collect(Collectors.toList());
                        Object[] wrap = ArrayUtil.wrap(values.toArray());
                        for (int j = 0; j < wrap.length; j++) {
                            Object object = wrap[j];
                            packParam(sql, paramMap, object, paramIndex);
                            if (j != wrap.length - 1) {
                                sql.append(StrUtil.C_COMMA);
                            }
                        }
                        sql.append(RIGHT_BRACKETS);
                    }
                } else {
                    packParam(sql, paramMap, whereCondition.value, paramIndex);
                }
            } else {
                sql.append(whereCondition.columnName).append(LIKE);
                packParam(sql, paramMap, "'%" + whereCondition.value + "%", paramIndex);
            }
        }

        return sql;
    }

    public static StrBuilder updateSql(SearchParam searchParam) {
        PackageSqlUtil.ParamIndex paramIndex = new PackageSqlUtil.ParamIndex();
        paramIndex.setParamType(UPDATE_PARAM_TYPE);
        TableMapper tableMapper = searchParam.getTableMapper();
        Map<String,Object> paramMap = searchParam.getParamMap();
        StrBuilder sql = StrUtil.strBuilder(UPDATE,StrUtil.SPACE, tableMapper.getTableName()).append(CRLF);
        List<SearchParam.Value> updateValueList = searchParam.getUpdateValueList();
        if(CollUtil.isEmpty(updateValueList)){
            return new StrBuilder();
        }
        sql.append(SET).append(StrUtil.SPACE);
        for (int i = 0; i < updateValueList.size(); i++) {
            SearchParam.Value value = updateValueList.get(i);
            sql.append(value.columnName).append(EQUAL);
            packParam(sql,paramMap,value.value,paramIndex);
            if(i !=updateValueList.size()-1){
                sql.append(StrUtil.C_COMMA);
            }
        }
        return sql;
    }

    public static StrBuilder orderBySql(StrBuilder sql, SearchParam searchParam) {
        List<SearchParam.OrderByCondition> orderByCondition = searchParam.getOrderByCondition();
        if (CollUtil.isEmpty(orderByCondition)) {
            return sql;
        }
        sql.append(CRLF);
        sql.append(SQLConstants.ORDER_BY);
        sql.append(StrUtil.SPACE);
        for (int i = 0; i < orderByCondition.size(); i++) {
            sql.append(orderByCondition.get(i).orderByName);
            sql.append(StrUtil.SPACE);
            sql.append(orderByCondition.get(i).sequence);
            if (i != orderByCondition.size() - 1) {
                sql.append(StrUtil.C_COMMA);
            }
        }
        return sql;
    }


    public static StrBuilder selectSql(SearchParam searchParam){
        if(StrUtil.isNotBlank(searchParam.getExecuteSql())){
            return new StrBuilder(searchParam.getExecuteSql());
        }
        StrBuilder sql = StrUtil.strBuilder(SELECT).append(StrUtil.SPACE);
        TableMapper tableMapper = searchParam.getTableMapper();
        HashMap<String,String> fieldToColumn = tableMapper.getFieldToColumn();
        List<String> showFields = tableMapper.getShowFields();
        String columns = showFields.stream().map(fieldToColumn::get).collect(Collectors.joining(","));
        sql.append(columns).append(StrUtil.SPACE).append(FROM).append(StrUtil.SPACE).append(tableMapper.getTableName());
        return sql;
    }

    public static StrBuilder countSql(SearchParam searchParam){
        if(StrUtil.isNotBlank(searchParam.getExecuteSql())){
            return new StrBuilder(searchParam.getExecuteSql());
        }
        TableMapper tableMapper = searchParam.getTableMapper();
        return StrUtil.strBuilder(SELECT).append(StrUtil.SPACE).append("COUNT(1) AS counts").append(StrUtil.SPACE).append(FROM).append(StrUtil.SPACE).append(tableMapper.getTableName());
    }
}
