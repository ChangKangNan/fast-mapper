package cn.ft.ckn.fastmapper.component.dao;

import cn.ft.ckn.fastmapper.annotation.Pager;
import cn.ft.ckn.fastmapper.bean.Expression;
import cn.ft.ckn.fastmapper.bean.SplicingParam;
import cn.ft.ckn.fastmapper.component.manager.MapperDataSourceManger;
import cn.ft.ckn.fastmapper.config.FastMapperConfig;
import cn.ft.ckn.fastmapper.constants.SQLConstants;
import cn.ft.ckn.fastmapper.util.SQLUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Pair;
import cn.hutool.core.text.CharPool;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/**
 * @author ckn
 * @date 2022/7/28
 */
public class SelectDao<T, R> extends MapperDataSourceManger<R> implements Pager<R> {
    private final SplicingParam splicingParam;
    private final Class<T> classObj;
    private final Class<R> returnObj;

    public SelectDao(SplicingParam splicingParam, Class<T> classObj, Class<R> returnObj) {
        super(returnObj, splicingParam);
        this.splicingParam = splicingParam;
        this.classObj = classObj;
        this.returnObj = returnObj;
    }

    public T one() {
        Pair<Map<String, Object>, StringBuilder> mapStringBuilderPair = packageSQL(this.splicingParam, classObj);
        NamedParameterJdbcTemplate jdbcTemplate = getJdbcTemplate();
        StringBuilder stringBuilder = mapStringBuilderPair.getValue();
        try {
            List<T> query = jdbcTemplate.query(mapStringBuilderPair.getValue().toString(), mapStringBuilderPair.getKey(), new BeanPropertyRowMapper<T>(classObj));
            if (FastMapperConfig.isOpenSQLPrint) {
                if (CollUtil.isNotEmpty(query)) {
                    SQLUtil.print(SQLUtil.printSql(stringBuilder.toString(), mapStringBuilderPair.getKey())
                            , SQLUtil.printResult(JSONUtil.toJsonStr(query.get(0))));
                }else {
                    SQLUtil.print(SQLUtil.printSql(stringBuilder.toString(), mapStringBuilderPair.getKey())
                            , SQLUtil.printResult(0));
                }
            }
            if (CollUtil.isNotEmpty(query)) {
                return query.get(0);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (FastMapperConfig.isOpenSQLPrint) {
                SQLUtil.print(SQLUtil.printSql(stringBuilder.toString(), mapStringBuilderPair.getKey())
                        , SQLUtil.printResult(""));
            }
            return null;
        }
    }

    public int count(){
        Pair<Map<String, Object>, StringBuilder> mapStringBuilderPair = packageSQL(this.splicingParam, classObj);
        NamedParameterJdbcTemplate jdbcTemplate = getJdbcTemplate();
        String s = mapStringBuilderPair.getValue().toString();
        int from = s.toUpperCase().indexOf(SQLConstants.FROM);
        String countSQL="select COUNT(1) AS counts "+s.substring(from);
        try {
            Integer count = jdbcTemplate.queryForObject(countSQL, mapStringBuilderPair.getKey(), Integer.class);
            if (count == null) {
                return -1;
            }
            if (FastMapperConfig.isOpenSQLPrint) {
                    SQLUtil.print(SQLUtil.printSql(countSQL, mapStringBuilderPair.getKey())
                            , SQLUtil.printResult(count));
            }
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            if (FastMapperConfig.isOpenSQLPrint) {
                SQLUtil.print(SQLUtil.printSql(countSQL, mapStringBuilderPair.getKey())
                        , SQLUtil.printResult(-1));
            }
            return -1;
        }
    }

    public List<T> list() {
        Pair<Map<String, Object>, StringBuilder> mapStringBuilderPair = packageSQL(this.splicingParam, classObj);
        NamedParameterJdbcTemplate jdbcTemplate = getJdbcTemplate();
        try {
            List<T> query = jdbcTemplate.query(mapStringBuilderPair.getValue().toString(), mapStringBuilderPair.getKey(), new BeanPropertyRowMapper<T>(classObj));
            if (FastMapperConfig.isOpenSQLPrint) {
                SQLUtil.print(SQLUtil.printSql(mapStringBuilderPair.getValue().toString(), mapStringBuilderPair.getKey())
                , SQLUtil.printResult(JSONUtil.toJsonStr(query)));
            }
            return query;
        } catch (Exception e) {
            if (FastMapperConfig.isOpenSQLPrint) {
                SQLUtil.print(SQLUtil.printSql(mapStringBuilderPair.getValue().toString(), mapStringBuilderPair.getKey())
                        , SQLUtil.printResult(""));
            }
            return new ArrayList<>();
        }
    }

    public R page(Integer page, Integer pageSize) {
        this.splicingParam.isOpenPage = true;
        this.splicingParam.setPage(page);
        this.splicingParam.setPageSize(pageSize);
        try {
            return returnObj.getDeclaredConstructor(SplicingParam.class).newInstance(splicingParam);
        } catch (Exception e) {
            return null;
        }
    }
    public R or() {
        this.splicingParam.isAnd = false;
        try {
            return returnObj.getDeclaredConstructor(SplicingParam.class).newInstance(splicingParam);
        } catch (Exception e) {
            return null;
        }
    }


    private <T> Pair<Map<String, Object>, StringBuilder> packageSQL(SplicingParam splicingParam, Class<T> objClass) {
        StringBuilder stringBuilder = new StringBuilder(SQLConstants.SELECT);
        Field[] declaredFields = objClass.getDeclaredFields();
        Map<String, Object> params = new HashMap<>();
        Map<String, String> columnToProperty = new HashMap<>();
        List<String> columns = new ArrayList<>();
        if (ArrayUtil.isEmpty(declaredFields)) {
            return new Pair<>(params, new StringBuilder());
        }
        AtomicBoolean pk = new AtomicBoolean(false);
        Arrays.stream(declaredFields).filter(f -> f.getAnnotation(Column.class) != null)
                .peek(p->{
                    if(p.getAnnotation(Id.class) != null){
                        pk.set(true);
                    }
                })
                .forEach(t -> {
                    columnToProperty.put(t.getAnnotation(Column.class).name(), t.getName());
                    columns.add(t.getAnnotation(Column.class).name());
                });
        if (!pk.get()) {
            throw new RuntimeException("Id annotation is necessary!");
        }
        stringBuilder.append(System.lineSeparator());
        stringBuilder.append(ArrayUtil.join(columns.toArray(), StrUtil.C_COMMA + System.lineSeparator()));
        stringBuilder.append(System.lineSeparator());
        stringBuilder.append(SQLConstants.FROM);
        stringBuilder.append(StrUtil.SPACE);
        Table annotation = objClass.getAnnotation(Table.class);
        String table = annotation.name();
        stringBuilder.append(table);
        if (CollUtil.isNotEmpty(splicingParam.whereCondition) || FastMapperConfig.isOpenLogicDeletedAuto) {
            stringBuilder.append(System.lineSeparator());
            stringBuilder.append(SQLConstants.WHERE);
            stringBuilder.append(StrUtil.SPACE);
            if (splicingParam.whereCondition.size() >= 2) {
                for (int i = 0; i <= splicingParam.whereCondition.size() - 2; i++) {
                    splicingParam.whereCondition.get(i).isAnd = splicingParam.whereCondition.get(i + 1).isAnd;
                }
                splicingParam.whereCondition.get(splicingParam.whereCondition.size() - 1).isAnd = true;
            }
            if (CollUtil.isNotEmpty(splicingParam.whereCondition)) {
                for (int i = 0; i < splicingParam.whereCondition.size(); i++) {
                    stringBuilder.append(splicingParam.whereCondition.get(i).columnName);
                    stringBuilder.append(splicingParam.whereCondition.get(i).expression);
                    if (!Expression.Like.expression.equals(splicingParam.whereCondition.get(i).expression)) {
                        if (Expression.In.expression.equals(splicingParam.whereCondition.get(i).expression) || Expression.NotIn.expression.equals(splicingParam.whereCondition.get(i).expression)) {
                            if (ArrayUtil.isArray(splicingParam.whereCondition.get(i).value)) {
                                stringBuilder.append(Expression.LeftBracket.expression);
                                List<Object> values = new ArrayList<>();
                                for (Object o : (Object[]) splicingParam.whereCondition.get(i).value) {
                                    if (o instanceof Collection) {
                                        values.addAll((Collection) o);
                                    } else {
                                        values.add(o);
                                    }
                                }
                                values = values.stream().distinct().collect(Collectors.toList());
                                Object[] wrap = ArrayUtil.wrap(values.toArray());
                                int j = 0;
                                for (Object o : wrap) {
                                    j++;
                                    stringBuilder.append(CharPool.COLON);
                                    stringBuilder.append(splicingParam.whereCondition.get(i).columnName).append("_in_").append(j);
                                    stringBuilder.append(",");
                                    params.put(splicingParam.whereCondition.get(i).columnName + "_in_" + j, o);
                                }
                                stringBuilder = new StringBuilder(stringBuilder.substring(0, stringBuilder.length() - 1));
                                stringBuilder.append(Expression.RightBracket.expression);
                            }
                        } else {
                            stringBuilder.append(CharPool.COLON);
                            stringBuilder.append(splicingParam.whereCondition.get(i).columnName).append("_").append(i);
                            params.put(splicingParam.whereCondition.get(i).columnName + "_" + i, splicingParam.whereCondition.get(i).value);
                        }
                    } else {
                        stringBuilder.append("'%").append(splicingParam.whereCondition.get(i).value).append("%'");
                    }
                    if (i != splicingParam.whereCondition.size() - 1) {
                        stringBuilder.append(System.lineSeparator());
                        if (splicingParam.whereCondition.get(i).isAnd) {
                            stringBuilder.append(SQLConstants.AND);
                        } else {
                            stringBuilder.append(SQLConstants.OR);
                        }
                        stringBuilder.append(StrUtil.SPACE);
                    }
                }
                if (FastMapperConfig.isOpenLogicDeletedAuto) {
                    stringBuilder.append(StrUtil.SPACE);
                    stringBuilder.append(SQLConstants.AND);
                    stringBuilder.append(StrUtil.SPACE);
                    stringBuilder.append(FastMapperConfig.logicDeletedColumn);
                    stringBuilder.append(Expression.Equal.expression);
                    stringBuilder.append(CharPool.COLON);
                    stringBuilder.append(FastMapperConfig.logicDeletedColumn);
                    params.put(FastMapperConfig.logicDeletedColumn, FastMapperConfig.logicDeletedColumnDefaultValue);
                }
            } else {
                stringBuilder.append(StrUtil.SPACE);
                stringBuilder.append(FastMapperConfig.logicDeletedColumn);
                stringBuilder.append(Expression.Equal.expression);
                stringBuilder.append(CharPool.COLON);
                stringBuilder.append(FastMapperConfig.logicDeletedColumn);
                params.put(FastMapperConfig.logicDeletedColumn, FastMapperConfig.logicDeletedColumnDefaultValue);
            }
        }
        if (CollUtil.isNotEmpty(splicingParam.orderByCondition)) {
            stringBuilder.append(System.lineSeparator());
            stringBuilder.append(SQLConstants.ORDER_BY);
            stringBuilder.append(StrUtil.SPACE);
            for (int i = 0; i < splicingParam.orderByCondition.size(); i++) {
                stringBuilder.append(splicingParam.orderByCondition.get(i).orderByName);
                stringBuilder.append(StrUtil.SPACE);
                stringBuilder.append(splicingParam.orderByCondition.get(i).sequence);
                if (i != splicingParam.orderByCondition.size() - 1) {
                    stringBuilder.append(StrUtil.C_COMMA);
                }
            }
        }
        if (splicingParam.isOpenPage) {
            stringBuilder.append(System.lineSeparator());
            stringBuilder.append(SQLConstants.LIMIT);
            stringBuilder.append(StrUtil.SPACE);
            stringBuilder.append((splicingParam.getPage() - 1) * splicingParam.getPageSize());
            stringBuilder.append(StrUtil.C_COMMA);
            stringBuilder.append(splicingParam.getPageSize());
        }
        return new Pair<>(params, stringBuilder);
    }
}
