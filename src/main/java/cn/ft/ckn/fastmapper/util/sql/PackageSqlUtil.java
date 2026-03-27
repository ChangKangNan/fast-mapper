package cn.ft.ckn.fastmapper.util.sql;

import cn.ft.ckn.fastmapper.bean.FastMapperParam;
import cn.ft.ckn.fastmapper.bean.FastTableMapper;
import cn.ft.ckn.fastmapper.bean.constants.SQLConstants;
import cn.ft.ckn.fastmapper.bean.em.Expression;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.text.StrBuilder;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;

import javax.persistence.Column;
import java.util.*;
import java.util.stream.Collectors;

import static cn.ft.ckn.fastmapper.bean.constants.SQLConstants.LIMIT;
import static cn.ft.ckn.fastmapper.bean.constants.SQLConstants.OR;

/**
 * 封装SQL工具类
 *
 * @author ckn
 */
public class PackageSqlUtil {
    public static final String INSERT = "INSERT INTO";
    public static final String SELECT = "SELECT";
    public static final String DELETE = "DELETE";
    public static final String UPDATE = "UPDATE";
    public static final String FROM = "FROM";
    public static final String SET = "SET";
    public static final String EQUAL = "=";
    public static final String CRLF = System.lineSeparator();
    public static final String WHERE = "WHERE";
    public static final String LIKE = "LIKE";
    public static final String AND = "AND";

    public static final String WHERE_PARAM_TYPE = "where_param_";
    public static final String UPDATE_PARAM_TYPE = "update_param_";
    public static final String INSERT_PARAM_TYPE = "insert_param_";
    public static final String PARAM_PREFIX_1 = "#{";
    public static final String PARAM_PREFIX_2 = "${";
    public static final String PARAM_SUFFIX = "}" + StrUtil.SPACE;
    public static final String JDBC_SQL_CONVERSION_RE_RULE = "[#][{](\\w*)[}]";
    public static final String JDBC_SQL_CONVERSION_RE_RULE_2 = "[$][{](\\w*)[}]";
    public static final String JDBC_SQL_CONVERSION_RE_RESULT = ":$1";
    public static final String LEFT_BRACKETS = "(";
    public static final String RIGHT_BRACKETS = ")";
    public static final String VALUES = "VALUES";

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

    public static StrBuilder insertSql(FastMapperParam<?> fastMapperParam) {
        List<?> insertList = fastMapperParam.getInsertList();
        FastTableMapper<?> tableMapper = fastMapperParam.getTableMapper();
        PackageSqlUtil.ParamIndex paramIndex = new PackageSqlUtil.ParamIndex();
        paramIndex.setParamType(INSERT_PARAM_TYPE);
        List<String> fieldNames = tableMapper.getShowFields();
        Map<String, String> fieldToColumn = tableMapper.getFieldToColumn();
        Map<String, Object> paramMap = fastMapperParam.getParamMap();
        StrBuilder sql = StrUtil.strBuilder(INSERT, StrUtil.SPACE, tableMapper.getTableName()).append(CRLF);
        if (insertList.size() == 1) {
            sql.append(SET);
            sql.append(CRLF);
            for (String fieldName : fieldNames) {
                Object in = insertList.get(0);
                Object fieldValue = BeanUtil.getFieldValue(in, fieldName);
                if (fieldValue != null) {
                    sql.append("`").append(fieldToColumn.get(fieldName)).append("`").append(EQUAL);
                    packParam(sql, paramMap, fieldValue, paramIndex).append(StrUtil.C_COMMA);
                }
            }
            sql.del(sql.length() - 1, sql.length());
        } else {
            sql.append(LEFT_BRACKETS);
            List<String> showFields = tableMapper.getShowFields();
            List<String> columns = showFields.stream().map(field -> "`" + fieldToColumn.get(field) + "`").collect(Collectors.toList());
            sql.append(String.join(StrUtil.COMMA, columns));
            sql.append(RIGHT_BRACKETS).append(VALUES).append(CRLF);
            for (int i = 0; i < insertList.size(); i++) {
                sql.append(LEFT_BRACKETS);
                for (int j = 0; j < fieldNames.size(); j++) {
                    Object fieldValue = BeanUtil.getFieldValue(insertList.get(i), fieldNames.get(j));
                    packParam(sql, paramMap, fieldValue, paramIndex);
                    if (j < fieldNames.size() - 1) {
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

    public static StrBuilder deleteSql(FastMapperParam<?> fastMapperParam) {
        FastTableMapper<?> tableMapper = fastMapperParam.getTableMapper();
        StrBuilder sql = StrUtil.strBuilder();
        sql.append(DELETE).append(StrUtil.SPACE).append(FROM);
        sql.append(StrUtil.SPACE).append(tableMapper.getTableName());
        return sql;
    }

    public static StrBuilder whereSql(StrBuilder sql, FastMapperParam<?> fastMapperParam) {
        PackageSqlUtil.ParamIndex paramIndex = new PackageSqlUtil.ParamIndex();
        paramIndex.setParamType(WHERE_PARAM_TYPE);
        List<FastMapperParam.WhereCondition> whereConditions = fastMapperParam.getWhereCondition();

        Map<String, Object> paramMap = fastMapperParam.getParamMap();

        if (CollUtil.isEmpty(whereConditions)) {
            return sql;
        }
        sql.append(CRLF);
        sql.append(WHERE).append(StrUtil.SPACE);
        List<FastMapperParam.Bracket> brackets = fastMapperParam.getBrackets();
        for (int i = 0; i < whereConditions.size(); i++) {
            FastMapperParam.WhereCondition whereCondition = whereConditions.get(i);
            if (whereCondition.sql == null && !hasField(fastMapperParam.getTableMapper().getObjClass(), whereCondition.columnName)) {
                continue;
            }
            if (i != 0) {
                int finalI = i;
                boolean needSpace = false;
                long l = brackets.stream().filter(f -> finalI > f.getLeftIndex() && finalI <= f.getRightIndex()).count();
                if (l > 0) {
                    needSpace = true;
                }
                if(needSpace){
                    sql.append(StrUtil.SPACE);
                }else {
                    sql.append(CRLF);
                }
                if (whereCondition.isAnd) {
                    sql.append(AND);
                } else {
                    sql.append(OR);
                }
                sql.append(StrUtil.SPACE);
            }

            for (FastMapperParam.Bracket bracket : brackets) {
                Integer leftIndex = bracket.getLeftIndex();
                if (leftIndex != i) {
                    continue;
                }
                sql.append(LEFT_BRACKETS);
            }

            if (whereCondition.sql != null) {
                sql.append(whereCondition.sql);
                paramMap.putAll(whereCondition.params);
            }else {
                String linkColumnName = "`" + whereCondition.columnName + "`";
                Boolean formatter = whereCondition.formatter;
                if (formatter) {
                    linkColumnName = whereCondition.formatterColumnName;
                }
                if (StrUtil.equalsAnyIgnoreCase(whereCondition.expression.name, Expression.IsNull.name, Expression.IsNotNull.name)) {
                    sql.append(linkColumnName);
                    sql.append(StrUtil.SPACE);
                    sql.append(whereCondition.expression.expression);
                    sql.append(StrUtil.SPACE);
                    continue;
                }

                if (StrUtil.equalsAny(whereCondition.expression.name, Expression.Match.name, Expression.NotMatch.name)) {
                    sql.append(whereCondition.expression.name).append(LEFT_BRACKETS).append(linkColumnName).append(RIGHT_BRACKETS);
                    sql.append(StrUtil.SPACE);
                    sql.append(whereCondition.expression.expression);
                    sql.append(LEFT_BRACKETS);
                    packParam(sql, paramMap, whereCondition.value, paramIndex);
                    sql.append(RIGHT_BRACKETS);
                } else if (StrUtil.equalsAny(whereCondition.expression.name, Expression.Between.name, Expression.NotBetween.name)) {
                    sql.append(linkColumnName);
                    sql.append(whereCondition.expression.expression);
                    packParam(sql, paramMap, whereCondition.minValue, paramIndex);
                    sql.append(StrUtil.SPACE);
                    sql.append(AND);
                    sql.append(StrUtil.SPACE);
                    packParam(sql, paramMap, whereCondition.maxValue, paramIndex);
                } else if (StrUtil.equals(whereCondition.expression.name, Expression.Like.name)) {
                    sql.append(linkColumnName);
                    sql.append(StrUtil.SPACE);
                    sql.append(whereCondition.expression.expression);
                    packParam(sql, paramMap, "%" + whereCondition.value + "%", paramIndex);
                } else {
                    sql.append(linkColumnName);
                    sql.append(whereCondition.expression.expression);
                    if (StrUtil.equalsAny(whereCondition.expression.name, Expression.In.name, Expression.NotIn.name)) {
                        if (ArrayUtil.isArray(whereCondition.value)) {
                            sql.append(LEFT_BRACKETS);
                            Object[] whereConditionValues = (Object[]) whereCondition.value;
                            List<Object> values = new ArrayList<>();
                            for (Object o : whereConditionValues) {
                                if (o instanceof Collection<?>) {
                                    values.addAll((Collection<?>) o);
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
                }
            }
            for (FastMapperParam.Bracket bracket : brackets) {
                Integer rightIndex = bracket.getRightIndex();
                if (rightIndex != i) {
                    continue;
                }
                sql.append(RIGHT_BRACKETS);
            }
        }

        return sql;
    }

    public static boolean hasField(Class<?> clazz, String fieldName) {
        try {
            clazz.getDeclaredField(fieldName);
            return true;
        } catch (NoSuchFieldException e) {
            List<String> collect = Arrays.stream(clazz.getDeclaredFields())
                    .map(f -> {
                        Column column = f.getAnnotation(Column.class);
                        return column == null ? null : column.name();
                    })
                    .filter(StrUtil::isNotBlank)
                    .collect(Collectors.toList());
            return CollUtil.contains(collect, fieldName);
        }
    }


    public static StrBuilder updateSql(FastMapperParam<?> fastMapperParam) {
        PackageSqlUtil.ParamIndex paramIndex = new PackageSqlUtil.ParamIndex();
        paramIndex.setParamType(UPDATE_PARAM_TYPE);
        FastTableMapper<?> tableMapper = fastMapperParam.getTableMapper();
        Map<String, Object> paramMap = fastMapperParam.getParamMap();
        StrBuilder sql = StrUtil.strBuilder(UPDATE, StrUtil.SPACE, tableMapper.getTableName()).append(CRLF);
        List<FastMapperParam.Value> updateValueList = fastMapperParam.getUpdateValueList();
        if (CollUtil.isEmpty(updateValueList)) {
            return new StrBuilder();
        }
        sql.append(SET).append(StrUtil.SPACE);
        for (int i = 0; i < updateValueList.size(); i++) {
            FastMapperParam.Value value = updateValueList.get(i);
            if (!hasField(tableMapper.getObjClass(), value.columnName)) {
                continue;
            }
            sql.append("`").append(value.columnName).append("`").append(EQUAL);
            if (value.value == "null") {
                sql.append("null");
            } else {
                packParam(sql, paramMap, value.value, paramIndex);
            }
            if (i != updateValueList.size() - 1) {
                sql.append(StrUtil.C_COMMA);
            }
        }
        if (sql.charAt(sql.length() - 1) == StrUtil.C_COMMA) {
            sql.del(sql.length() - 1, sql.length());
        }
        return sql;
    }

    public static StrBuilder orderBySql(StrBuilder sql, FastMapperParam<?> fastMapperParam) {
        List<FastMapperParam.OrderByCondition> orderByCondition = fastMapperParam.getOrderByCondition();
        if (CollUtil.isEmpty(orderByCondition)) {
            return sql;
        }
        sql.append(CRLF);
        sql.append(SQLConstants.ORDER_BY);
        sql.append(StrUtil.SPACE);
        for (int i = 0; i < orderByCondition.size(); i++) {
            if (!hasField(fastMapperParam.getTableMapper().getObjClass(), orderByCondition.get(i).orderByName)) {
                continue;
            }
            sql.append("`").append(orderByCondition.get(i).orderByName).append("`");
            sql.append(StrUtil.SPACE);
            sql.append(orderByCondition.get(i).sequence);
            if (i != orderByCondition.size() - 1) {
                sql.append(StrUtil.C_COMMA);
            }
        }
        if (sql.charAt(sql.length() - 1) == StrUtil.C_COMMA) {
            sql.del(sql.length() - 1, sql.length());
        }
        return sql;
    }


    public static StrBuilder selectSql(FastMapperParam<?> FastMapperParam) {
        StrBuilder sql = StrUtil.strBuilder(SELECT).append(StrUtil.SPACE);
        FastTableMapper<?> tableMapper = FastMapperParam.getTableMapper();
        Map<String, String> fieldToColumn = tableMapper.getFieldToColumn();
        List<String> showFields = tableMapper.getShowFields();
        String columns = showFields.stream().map(field -> "`" + fieldToColumn.get(field) + "`").collect(Collectors.joining(","));
        sql.append(columns).append(StrUtil.SPACE).append(FROM).append(StrUtil.SPACE).append(tableMapper.getTableName());
        return sql;
    }

    public static StrBuilder countSql(FastMapperParam<?> FastMapperParam) {
        FastTableMapper<?> tableMapper = FastMapperParam.getTableMapper();
        return StrUtil.strBuilder(SELECT).append(StrUtil.SPACE).append("COUNT(1) AS `counts`").append(StrUtil.SPACE).append(FROM).append(StrUtil.SPACE).append(tableMapper.getTableName());
    }

    public static void limit(StrBuilder sqlBuilder, FastMapperParam<?> param) {
        if (param.getOpenPage() || param.getLimit() != null) {
            sqlBuilder.append(System.lineSeparator());
        }
        if (param.getOpenPage()) {
            sqlBuilder.append(LIMIT).append(StrUtil.SPACE).append(param.getPage() - 1).append(StrUtil.C_COMMA).append(param.getPageSize());
        } else if (param.getLimit() != null) {
            sqlBuilder.append(LIMIT).append(StrUtil.SPACE).append(param.getLimit());
        }
    }
}
