package cn.ft.ckn.fastmapper.component;

import cn.ft.ckn.fastmapper.config.FastMapperConfig;
import cn.ft.ckn.fastmapper.util.JDBCUtils;
import cn.ft.ckn.fastmapper.util.SQLUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.text.CharPool;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ckn
 * @date 2022/7/28
 */
public class DeleteDao<T,R>  extends MapperDataSourceManger<R>{
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
                if(StrUtil.equals(name,"update_time")){
                    isExistUpdate=true;
                }
                if(StrUtil.equals(name,FastMapperConfig.logicDeletedColumn)){
                    isExistDeleted=true;
                }
            }
        }
        String tableName = table.name();
        Map<String, Object> paramMap = new HashMap<>();
        StringBuilder deletedSQL;
        int endIndex=0;
        if((!splicingParam.isCloseDeleteProtect)&&((FastMapperConfig.isOpenLogicDeletedAuto && isExistDeleted) || (FastMapperConfig.isOpenUpdateTimeAuto && isExistUpdate))){
            deletedSQL=new StringBuilder("UPDATE "+tableName+" set ");
            if(FastMapperConfig.isOpenLogicDeletedAuto && isExistDeleted){
                deletedSQL.append(FastMapperConfig.logicDeletedColumn);
                deletedSQL.append(Expression.Equal.expression);
                deletedSQL.append(CharPool.COLON);
                deletedSQL.append("set_"+FastMapperConfig.logicDeletedColumn);
                paramMap.put("set_"+FastMapperConfig.logicDeletedColumn, FastMapperConfig.logicDeletedColumnDeletedValue);
                deletedSQL.append(StrUtil.SPACE);

                if(isExistUpdate && FastMapperConfig.isOpenUpdateTimeAuto){
                    deletedSQL.append(StrUtil.C_COMMA);
                    deletedSQL.append(StrUtil.SPACE);
                    deletedSQL.append("update_time");
                    deletedSQL.append(Expression.Equal.expression);
                    deletedSQL.append(CharPool.COLON);
                    deletedSQL.append("updateTime");
                    paramMap.put("updateTime", new Date());
                    deletedSQL.append(StrUtil.SPACE);
                }
            }else {
                    deletedSQL.append(StrUtil.SPACE);
                    deletedSQL.append("update_time");
                    deletedSQL.append(Expression.Equal.expression);
                    deletedSQL.append(CharPool.COLON);
                    deletedSQL.append("updateTime");
                    paramMap.put("updateTime", new Date());
                    deletedSQL.append(StrUtil.SPACE);
            }
        }else{
            deletedSQL=new StringBuilder("DELETE FROM "+tableName);
            deletedSQL.append(Expression.LineSeparator.expression);
        }
        if(FastMapperConfig.isOpenLogicDeletedAuto && isExistDeleted){
            long count = splicingParam.whereCondition.stream().filter(t -> t.columnName.equals(FastMapperConfig.logicDeletedColumn)).count();
            if(count==0){
                splicingParam.whereCondition.add(new SplicingParam.WhereCondition(FastMapperConfig.logicDeletedColumn,
                        FastMapperConfig.logicDeletedColumnDefaultValue, Expression.Equal.expression,true));
            }
        }
        deletedSQL.append("WHERE");
        deletedSQL.append(StrUtil.SPACE);
        whereConcat(paramMap, deletedSQL, endIndex);
        DataSource dataSource = getDataSource();
        try {
            JDBCUtils jdbcUtils = new JDBCUtils(dataSource);
            int update = jdbcUtils.update(deletedSQL.toString(), paramMap);
            if (FastMapperConfig.isOpenSQLPrint) {
                SQLUtil.print(SQLUtil.printSql(deletedSQL.toString(),paramMap)
                        , SQLUtil.printResult(JSONUtil.toJsonStr(update)));
            }
        }catch (Exception e){
            e.printStackTrace();
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
                if(Expression.In.expression.equals(whereCondition.expression)){
                    if (ArrayUtil.isArray(whereCondition.value)) {
                        deletedSQL.append(Expression.LeftBracket.expression);
                        Object[] wrap = ArrayUtil.wrap(whereCondition.value);
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
                    deletedSQL.append("and");
                }else {
                    deletedSQL.append("or");
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
