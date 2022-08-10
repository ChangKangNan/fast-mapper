package cn.ft.ckn.fastmapper.join;

import cn.ft.ckn.fastmapper.component.*;
import cn.ft.ckn.fastmapper.config.FastMapperConfig;
import cn.ft.ckn.fastmapper.fm.bean.OtherOpen;
import cn.ft.ckn.fastmapper.util.ColumnUtil;
import cn.ft.ckn.fastmapper.util.FastCustomer;
import cn.ft.ckn.fastmapper.util.SQLUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.text.StrBuilder;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.persistence.Table;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JoinCustomer<T> {
    private final SplicingParam splicingParam;
    Class<T> mainClass;
    Map<String, Object> where;
    String mainTable;
    ArrayList<String> columns;
    Map<String, Map<String, String>> joins;
    Map<String,String> relation;

    public JoinCustomer(Class<T> main, SFunction<T, ?>... fields) {
        splicingParam = new SplicingParam();
        where = new HashMap<>();
        columns = new ArrayList<>();
        joins = new HashMap<>();
        relation=new HashMap<>();
        this.mainClass = main;
        Table mainClassAnnotation = mainClass.getAnnotation(Table.class);
        mainTable = mainClassAnnotation.name();
        if (ArrayUtil.isArray(fields)) {
            for (SFunction<T, ?> field : fields) {
                String fieldName = ColumnUtil.getFieldName(field);
                columns.add(mainTable + StrUtil.DOT + fieldName);
            }
        }
    }

    public <L> JoinCustomer<T> leftJoin(Class<L> joinClass
            , Map<SFunction<T, ?>, SFunction<L, ?>> link
            , Map<SFunction<L, ?>, Object> where
            , SFunction<L, ?>... fields) {
        Table annotation = joinClass.getAnnotation(Table.class);
        String tableName = annotation.name();
        Map<String, String> map = new HashMap<>();
        if (MapUtil.isNotEmpty(link)) {
            for (SFunction<T, ?> key : link.keySet()) {
                String kName = ColumnUtil.getFieldName(key);
                SFunction<L, ?> sFunction = link.get(key);
                String vName = ColumnUtil.getFieldName(sFunction);
                map.put(mainTable+ StrUtil.DOT + kName,tableName+ StrUtil.DOT + vName);
            }
        }
        if (ArrayUtil.isArray(fields)) {
            for (SFunction<L, ?> field : fields) {
                String fieldName = ColumnUtil.getFieldName(field);
                columns.add(tableName + StrUtil.DOT + fieldName);
            }
        }
        if (MapUtil.isNotEmpty(where)) {
            for (SFunction<L, ?> function : where.keySet()) {
                Object o = where.get(function);
                String fieldName = ColumnUtil.getFieldName(function);
                this.where.put(mainTable + StrUtil.DOT + fieldName, o);
            }
        }
        joins.put(tableName, map);
        relation.put(tableName,"LEFT JOIN");
        return this;
    }

    public <R> JoinCustomer<T> rightJoin(Class<R> joinClass
            , Map<SFunction<T, ?>, SFunction<R, ?>> link
            , Map<SFunction<R, ?>, Object> where
            , SFunction<R, ?>... fields) {
        Table annotation = joinClass.getAnnotation(Table.class);
        String tableName = annotation.name();
        Map<String, String> map = new HashMap<>();
        if (MapUtil.isNotEmpty(link)) {
            for (SFunction<T, ?> key : link.keySet()) {
                String kName = ColumnUtil.getFieldName(key);
                SFunction<R, ?> sFunction = link.get(key);
                String vName = ColumnUtil.getFieldName(sFunction);
                map.put(mainTable+ StrUtil.DOT + kName,tableName+ StrUtil.DOT + vName);
            }
        }
        if (ArrayUtil.isArray(fields)) {
            for (SFunction<R, ?> field : fields) {
                String fieldName = ColumnUtil.getFieldName(field);
                columns.add(tableName + StrUtil.DOT + fieldName);
            }
        }
        if (MapUtil.isNotEmpty(where)) {
            for (SFunction<R, ?> function : where.keySet()) {
                Object o = where.get(function);
                String fieldName = ColumnUtil.getFieldName(function);
                this.where.put(mainTable + StrUtil.DOT + fieldName, o);
            }
        }
        joins.put(tableName, map);
        relation.put(tableName,"RIGHT JOIN");
        return this;
    }

    public <I> JoinCustomer<T> innerJoin(Class<I> joinClass
            , Map<SFunction<T, ?> , SFunction<I, ?>> link
            , Map<SFunction<I, ?>, Object> where
            , SFunction<I, ?>... fields) {
        Table annotation = joinClass.getAnnotation(Table.class);
        String tableName = annotation.name();
        Map<String, String> map = new HashMap<>();
        if (MapUtil.isNotEmpty(link)) {
            for (SFunction<T, ?> key : link.keySet()) {
                String kName = ColumnUtil.getFieldName(key);
                SFunction<I, ?> sFunction = link.get(key);
                String vName = ColumnUtil.getFieldName(sFunction);
                map.put(mainTable+ StrUtil.DOT + kName,tableName+ StrUtil.DOT + vName);
            }
        }
        if (ArrayUtil.isArray(fields)) {
            for (SFunction<I, ?> field : fields) {
                String fieldName = ColumnUtil.getFieldName(field);
                columns.add(tableName + StrUtil.DOT + fieldName);
            }
        }
        if (MapUtil.isNotEmpty(where)) {
            for (SFunction<I, ?> function : where.keySet()) {
                Object o = where.get(function);
                String fieldName = ColumnUtil.getFieldName(function);
                this.where.put(mainTable + StrUtil.DOT + fieldName, o);
            }
        }
        joins.put(tableName, map);
        relation.put(tableName,"INNER JOIN");
        return this;
    }

    public <X> JoinCustomer<T> leftJoinGroup(JoinCustomer<X> leftGroup
            , Map<SFunction<T, ?>
            , SFunction<X, ?>> link) {
        if (ArrayUtil.isNotEmpty(leftGroup.columns)) {
            this.columns.addAll(leftGroup.columns);
        }
        if (MapUtil.isNotEmpty(leftGroup.where)) {
            this.where.putAll(leftGroup.where);
        }
        Map<String, String> map = new HashMap<>();
        if (MapUtil.isNotEmpty(link)) {
            for (SFunction<T, ?> key : link.keySet()) {
                String kName = ColumnUtil.getFieldName(key);
                SFunction<X, ?> sFunction = link.get(key);
                String vName = ColumnUtil.getFieldName(sFunction);
                map.put(mainTable + StrUtil.DOT + kName,  leftGroup.mainTable+ StrUtil.DOT + vName);
            }
        }
        this.joins.put(leftGroup.mainTable, map);
        if (MapUtil.isNotEmpty(leftGroup.joins)) {
            this.joins.putAll(leftGroup.joins);
        }
        relation.put(leftGroup.mainTable,"LEFT JOIN");
        return this;
    }

    public <Y> JoinCustomer<T> rightJoinGroup(
            JoinCustomer<Y> rightJoinGroup
            , Map<SFunction<T, ?>, SFunction<Y, ?>> link) {
        if (ArrayUtil.isNotEmpty(rightJoinGroup.columns)) {
            this.columns.addAll(rightJoinGroup.columns);
        }
        if (MapUtil.isNotEmpty(rightJoinGroup.where)) {
            this.where.putAll(rightJoinGroup.where);
        }
        Map<String, String> map = new HashMap<>();
        if (MapUtil.isNotEmpty(link)) {
            for (SFunction<T, ?> key : link.keySet()) {
                String kName = ColumnUtil.getFieldName(key);
                SFunction<Y, ?> sFunction = link.get(key);
                String vName = ColumnUtil.getFieldName(sFunction);
                map.put( mainTable+ StrUtil.DOT + kName, rightJoinGroup.mainTable + StrUtil.DOT + vName);
            }
        }
        this.joins.put(rightJoinGroup.mainTable, map);
        if (MapUtil.isNotEmpty(rightJoinGroup.joins)) {
            this.joins.putAll(rightJoinGroup.joins);
        }
        relation.put(rightJoinGroup.mainTable,"RIGHT JOIN");
        return this;
    }

    public <Z> JoinCustomer<T> innerJoinGroup(JoinCustomer<Z> innerJoinGroup
            , Map<SFunction<T, ?>, SFunction<Z, ?>> link) {
        if (ArrayUtil.isNotEmpty(innerJoinGroup.columns)) {
            this.columns.addAll(innerJoinGroup.columns);
        }
        if (MapUtil.isNotEmpty(innerJoinGroup.where)) {
            this.where.putAll(innerJoinGroup.where);
        }
        Map<String, String> map = new HashMap<>();
        if (MapUtil.isNotEmpty(link)) {
            for (SFunction<T, ?> key : link.keySet()) {
                String kName = ColumnUtil.getFieldName(key);
                SFunction<Z, ?> sFunction = link.get(key);
                String vName = ColumnUtil.getFieldName(sFunction);
                map.put(mainTable + StrUtil.DOT + kName, innerJoinGroup.mainTable + StrUtil.DOT + vName);
            }
        }
        this.joins.put(innerJoinGroup.mainTable, map);
        if (MapUtil.isNotEmpty(innerJoinGroup.joins)) {
            this.joins.putAll(innerJoinGroup.joins);
        }
        relation.put(innerJoinGroup.mainTable,"INNER JOIN");
        return this;
    }

    private DataSource getMasterDataSource() {
        DataSource master = FastMapperConfig.dataSourceMaster.get();
        if (master == null) {
            master = SpringUtil.getBean(DataSource.class);
            FastMapperConfig.dataSourceMaster.set(master);
        }
        return master;
    }

    public JoinCustomer<T> setSalveDataSource(DataSource dataSource) {
        splicingParam.isMaster = false;
        splicingParam.dataSource = dataSource;
        return this;
    }

    protected NamedParameterJdbcTemplate getJdbcTemplate() {
        try {
            NamedParameterJdbcTemplate jdbcTemplate = null;
            if (splicingParam.dataSource != null) {
                if (!splicingParam.isMaster) {
                    DruidDataSource druidDataSource = (DruidDataSource) splicingParam.dataSource;
                    String key = StrBuilder.create(druidDataSource.getDriverClassName(), druidDataSource.getUrl(), druidDataSource.getUsername()
                            , druidDataSource.getPassword()).toString();
                    for (String compare : FastMapperConfig.dataSourceSalveTemplateMap.keySet()) {
                        if (StrUtil.equals(key, compare)) {
                            jdbcTemplate = FastMapperConfig.dataSourceSalveTemplateMap.get(compare);
                        }
                    }
                    if (jdbcTemplate == null) {
                        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(splicingParam.dataSource);
                        FastMapperConfig.dataSourceSalveTemplateMap.putIfAbsent(key, namedParameterJdbcTemplate);
                        return namedParameterJdbcTemplate;
                    }
                } else {
                    jdbcTemplate = FastMapperConfig.dataSourceMasterTemplate.get();
                    if (jdbcTemplate == null) {
                        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getMasterDataSource());
                        FastMapperConfig.dataSourceMasterTemplate.set(namedParameterJdbcTemplate);
                        return namedParameterJdbcTemplate;
                    }
                }
            } else {
                jdbcTemplate = FastMapperConfig.dataSourceMasterTemplate.get();
                if (jdbcTemplate == null) {
                    NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getMasterDataSource());
                    FastMapperConfig.dataSourceMasterTemplate.set(namedParameterJdbcTemplate);
                    return namedParameterJdbcTemplate;
                }
            }
            return jdbcTemplate;
        } catch (Exception e) {
            throw new RuntimeException("获取数据库连接失败!");
        }
    }

    StringBuilder getSQL(){
        StringBuilder sqlBuilder=new StringBuilder("SELECT");
        sqlBuilder.append(Expression.LineSeparator.expression);
        sqlBuilder.append(StrUtil.join(",",columns.toArray()));
        sqlBuilder.append(Expression.LineSeparator.expression);
        sqlBuilder.append("from");
        sqlBuilder.append(StrUtil.SPACE);
        sqlBuilder.append(mainTable);
        sqlBuilder.append(Expression.LineSeparator.expression);
        if(MapUtil.isNotEmpty(joins)){
            for (String table : joins.keySet()) {
                Map<String, String> map = joins.get(table);
                String r = relation.get(table);
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
        if(MapUtil.isNotEmpty(where)){
            sqlBuilder.append("WHERE");
            sqlBuilder.append(StrUtil.SPACE);
            int i=0;
            for (String key : where.keySet()) {
                i++;
                if(i !=1){
                    sqlBuilder.append(Expression.LineSeparator.expression);
                    sqlBuilder.append("and");
                    sqlBuilder.append(StrUtil.SPACE);
                }
                Object obj = where.get(key);
                sqlBuilder.append(key);
                sqlBuilder.append(Expression.Equal.expression);
                sqlBuilder.append(SQLUtil.getValue(obj));
                sqlBuilder.append(StrUtil.SPACE);
            }
        }
        return sqlBuilder;
    }

    public PageInfo<Map<String, Object>> findPage(Integer pageNumber,Integer pageSize){
        NamedParameterJdbcTemplate jdbcTemplate = getJdbcTemplate();
        StringBuilder sql=getSQL();
        StringBuilder countSQL=new StringBuilder("SELECT count(*) ");
        int indexOf = sql.toString().indexOf("from");
        if(indexOf<0){
            indexOf = sql.toString().indexOf("From");
        }
        countSQL.append(sql.substring(indexOf));
        Integer totalCount = jdbcTemplate.queryForObject(countSQL.toString(), new HashMap<>(), Integer.class);
        if (pageNumber != null && pageSize != null) {
            sql.append(System.lineSeparator());
            sql.append("LIMIT");
            sql.append(StrUtil.SPACE);
            int pageNum = pageNumber - 1;
            sql.append(pageNum*pageSize);
            sql.append(StrUtil.C_COMMA);
            sql.append(pageSize);
        }
        try {
            List<OtherOpen> otherOpens = jdbcTemplate.queryForList(sql.toString(), new HashMap<>(), OtherOpen.class);
            List<Map<String, Object>> mapList = jdbcTemplate.queryForList(sql.toString(),new HashMap<>());
            PageInfo<Map<String, Object>> mapPageInfo = new PageInfo<>(mapList, pageNumber, pageSize, totalCount);
            if (FastMapperConfig.isOpenSQLPrint) {
                SQLUtil.print(SQLUtil.printSql(sql.toString(),new HashMap<>())
                        , SQLUtil.printResult(JSONUtil.toJsonStr(mapPageInfo)));
            }
            return mapPageInfo;
        }catch (Exception e){
            if (FastMapperConfig.isOpenSQLPrint) {
                SQLUtil.print(SQLUtil.printSql(sql.toString(),new HashMap<>())
                        , SQLUtil.printResult(""));
            }
            return new PageInfo<>(new ArrayList<>(),pageNumber,pageSize,totalCount);
        }
    }

    public <R>List<R> findAll(Class<R> returnObj){
        NamedParameterJdbcTemplate jdbcTemplate = getJdbcTemplate();
        StringBuilder sql=getSQL();
        try {
            List<R> list = jdbcTemplate.queryForList(sql.toString(), new HashMap<>(), returnObj);
            if (FastMapperConfig.isOpenSQLPrint) {
                SQLUtil.print(SQLUtil.printSql(sql.toString(),new HashMap<>())
                        , SQLUtil.printResult(JSONUtil.toJsonStr(list)));
            }
            return list;
        }catch (Exception e){
            if (FastMapperConfig.isOpenSQLPrint) {
                SQLUtil.print(SQLUtil.printSql(sql.toString(),new HashMap<>())
                        , SQLUtil.printResult(""));
            }
            return new ArrayList<>();
        }
    }

}
