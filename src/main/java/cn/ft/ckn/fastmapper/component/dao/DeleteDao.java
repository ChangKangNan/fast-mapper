package cn.ft.ckn.fastmapper.component.dao;

import cn.ft.ckn.fastmapper.bean.Expression;
import cn.ft.ckn.fastmapper.component.manager.MapperDataSourceManger;
import cn.ft.ckn.fastmapper.bean.SplicingParam;
import cn.ft.ckn.fastmapper.config.FastMapperConfig;
import cn.ft.ckn.fastmapper.util.SQLUtil;
import cn.ft.ckn.fastmapper.transaction.TransactionManager;
import cn.hutool.core.text.CharPool;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

import static cn.ft.ckn.fastmapper.constants.SQLConstants.*;

/**
 * @author ckn
 * @date 2022/7/28
 */
public class DeleteDao<T,R>  extends MapperDataSourceManger<R> {
    private final SplicingParam splicingParam;
    private final Class<T> classObj;
    private final Class<R> returnObj;

    public DeleteDao(SplicingParam splicingParam, Class<T> classObj, Class<R> returnObj) {
        super(returnObj,splicingParam);
        this.splicingParam = splicingParam;
        this.classObj = classObj;
        this.returnObj = returnObj;
    }
    public DeleteDao<T,R> closeDeletedProtect(){
        splicingParam.isCloseDeleteProtect=Boolean.TRUE;
        return this;
    }
    public void delete(){
        Table table = classObj.getAnnotation(Table.class);
        Field[] classObjDeclaredFields = classObj.getDeclaredFields();
        boolean isExistUpdate=false;
        boolean isExistDeleted=false;
        for (Field objDeclaredField : classObjDeclaredFields) {
            Column fieldAnnotation = objDeclaredField.getAnnotation(Column.class);
            if(fieldAnnotation !=null){
                String name = fieldAnnotation.name();
                if(StrUtil.equals(name,FastMapperConfig.updateTime)){
                    isExistUpdate=true;
                }
                if(StrUtil.equals(name,FastMapperConfig.logicDeletedColumn)){
                    isExistDeleted=true;
                }
            }
        }
        String tableName = table.name();
        Map<String, Object> paramMap = new HashMap<>();
        StringBuilder deletedSQL=new StringBuilder();
        int endIndex=0;
        if((!splicingParam.isCloseDeleteProtect)&&((FastMapperConfig.isOpenLogicDeletedAuto && isExistDeleted) || (FastMapperConfig.isOpenUpdateTimeAuto && isExistUpdate))){
            deletedSQL.append(StrUtil.SPACE);
            deletedSQL.append(tableName);
            deletedSQL.append(StrUtil.SPACE);
            deletedSQL.append(SET);
            deletedSQL.append(StrUtil.SPACE);
            if(FastMapperConfig.isOpenLogicDeletedAuto && isExistDeleted){
                deletedSQL.append(FastMapperConfig.logicDeletedColumn);
                deletedSQL.append(Expression.Equal.expression);
                deletedSQL.append(CharPool.COLON);
                deletedSQL.append("set_").append(FastMapperConfig.logicDeletedColumn);
                paramMap.put("set_"+FastMapperConfig.logicDeletedColumn, FastMapperConfig.logicDeletedColumnDeletedValue);
                deletedSQL.append(StrUtil.SPACE);

                if(isExistUpdate && FastMapperConfig.isOpenUpdateTimeAuto){
                    deletedSQL.append(StrUtil.C_COMMA);
                    deletedSQL.append(StrUtil.SPACE);
                    deletedSQL.append(FastMapperConfig.updateTime);
                    deletedSQL.append(Expression.Equal.expression);
                    deletedSQL.append(CharPool.COLON);
                    deletedSQL.append(FastMapperConfig.updateTime);
                    paramMap.put(FastMapperConfig.updateTime, new Date());
                    deletedSQL.append(StrUtil.SPACE);
                }
            }else {
                    deletedSQL.append(StrUtil.SPACE);
                    deletedSQL.append(FastMapperConfig.updateTime);
                    deletedSQL.append(Expression.Equal.expression);
                    deletedSQL.append(CharPool.COLON);
                    deletedSQL.append(FastMapperConfig.updateTime);
                    paramMap.put(FastMapperConfig.updateTime, new Date());
                    deletedSQL.append(StrUtil.SPACE);
            }
        }else{
            deletedSQL=new StringBuilder(DELETE);
            deletedSQL.append(StrUtil.SPACE);
            deletedSQL.append(FORM);
            deletedSQL.append(StrUtil.SPACE);
            deletedSQL.append(tableName);
            deletedSQL.append(Expression.LineSeparator.expression);
        }
        if(FastMapperConfig.isOpenLogicDeletedAuto && isExistDeleted){
            long count = splicingParam.whereCondition.stream().filter(t -> t.columnName.equals(FastMapperConfig.logicDeletedColumn)).count();
            if(count==0){
                splicingParam.whereCondition.add(new SplicingParam.WhereCondition(FastMapperConfig.logicDeletedColumn,
                        FastMapperConfig.logicDeletedColumnDefaultValue, Expression.Equal.expression,true));
            }
        }
        deletedSQL.append(WHERE);
        deletedSQL.append(StrUtil.SPACE);
        whereConcat(paramMap, deletedSQL, endIndex);
        DataSource dataSource = getDataSource();
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        TransactionManager.initTransaction(dataSource);
        int update = jdbcTemplate.update(deletedSQL.toString(), paramMap);
        if (FastMapperConfig.isOpenSQLPrint) {
            SQLUtil.print(SQLUtil.printSql(deletedSQL.toString(),paramMap)
                    , SQLUtil.printResult(JSONUtil.toJsonStr(update)));
        }
    }

    private void whereConcat(Map<String, Object> paramMap, StringBuilder deletedSQL, int endIndex) {
        if(splicingParam.whereCondition.size()>=2){
            for (int i = 0; i <= splicingParam.whereCondition.size()-2; i++) {
                splicingParam.whereCondition.get(i).isAnd= splicingParam.whereCondition.get(i+1).isAnd;
            }
            splicingParam.whereCondition.get(splicingParam.whereCondition.size()-1).isAnd=true;
        }
        for (SplicingParam.WhereCondition whereCondition : splicingParam.whereCondition) {
            endIndex++;
            deletedSQL.append(whereCondition.columnName);
            deletedSQL.append(whereCondition.expression);
            if(!Expression.Like.expression.equals(whereCondition.expression)){
                if(Expression.In.expression.equals(whereCondition.expression) || Expression.NotIn.expression.equals(whereCondition.expression)){
                    if (ArrayUtil.isArray(whereCondition.value)) {
                        deletedSQL.append(Expression.LeftBracket.expression);
                        List<Object> values=new ArrayList<>();
                        for (Object o : (Object[]) whereCondition.value) {
                            if(o instanceof Collection){
                                values.addAll((Collection) o);
                            }else {
                                values.add(o);
                            }
                        }
                        values=values.stream().distinct().collect(Collectors.toList());
                        Object[] wrap = ArrayUtil.wrap(values.toArray());
                        int i=0;
                        for (Object o : wrap) {
                            i++;
                            deletedSQL.append(CharPool.COLON).append("where").append('_');
                            deletedSQL.append(whereCondition.columnName).append("_in_").append(i);
                            if(i!=wrap.length){
                                deletedSQL.append(",");
                            }
                            paramMap.put("where_"+whereCondition.columnName + "_in_" + i, o);
                        }
                        deletedSQL.append(Expression.RightBracket.expression);
                    }

                }else {
                    deletedSQL.append(CharPool.COLON).append("where").append('_');
                    deletedSQL.append(whereCondition.columnName).append("_").append(endIndex);
                    paramMap.put("where_"+whereCondition.columnName + "_" + endIndex, whereCondition.value);
                }
            }else{
                deletedSQL.append("'%").append(whereCondition.value).append("%'");
            }
            if(endIndex!=splicingParam.whereCondition.size()){
                deletedSQL.append(StrUtil.SPACE);
                if(whereCondition.isAnd){
                    deletedSQL.append(AND);
                }else {
                    deletedSQL.append(OR);
                }
                deletedSQL.append(StrUtil.SPACE);
            }
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
}