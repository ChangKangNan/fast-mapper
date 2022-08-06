package cn.ft.ckn.fastmapper.component;

import cn.ft.ckn.fastmapper.config.FastMapperConfig;
import cn.ft.ckn.fastmapper.util.SQLUtil;
import cn.ft.ckn.fastmapper.util.ValueUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.text.CharPool;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author ckn
 * @date 2022/7/28
 */
public class UpdateDao<T, R> extends MapperDataSourceManger<R> {
    private final SplicingParam splicingParam;
    private final Class<T> classObj;
    private final Class<R> returnObj;

    public UpdateDao(SplicingParam splicingParam, Class<T> classObj, Class<R> returnObj) {
        super(returnObj, splicingParam);
        this.splicingParam = splicingParam;
        this.classObj = classObj;
        this.returnObj = returnObj;
    }

    public  UpdateValue<T, R> value() {
        return new UpdateValue<>(splicingParam,classObj,returnObj);
    }

    public void update(T t) {
        List<ColumnParam> valueParams = ValueUtil.getColumns(t);
        List<ColumnParam> columnParams = valueParams.stream().filter(ColumnParam::getHaveValue).collect(Collectors.toList());
        if(CollUtil.isNotEmpty(columnParams)){
            for (ColumnParam valueParam : columnParams) {
                splicingParam.valueList.add(new SplicingParam.Value(valueParam.getColumnName(),valueParam.getVal()));
            }
        }
        execute();
    }

    protected void execute(){
        Table table = classObj.getAnnotation(Table.class);
        String tableName = table.name();
        NamedParameterJdbcTemplate jdbcTemplate = getJdbcTemplate();
        Map<String, Object> paramMap = new HashMap<>();
        boolean isExistUpdate=false;
        boolean isExistDeleted=false;
        Field[] classObjDeclaredFields = classObj.getDeclaredFields();
        String primaryName=null;
        for (Field objDeclaredField : classObjDeclaredFields) {
            Id id = objDeclaredField.getAnnotation(Id.class);
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
            if(id !=null){
                if(fieldAnnotation==null){
                    throw new RuntimeException("id注解需column联合使用");
                }
                primaryName=fieldAnnotation.name();
            }
        }
        StringBuilder updateSQL = new StringBuilder("UPDATE " + tableName +" set ");
        int endIndex=0;
        int uo=0;
        for (SplicingParam.Value value : splicingParam.valueList) {
            if(value.columnName.equals(primaryName)){
                uo++;
                continue;
            }
            updateSQL.append(Expression.LineSeparator.expression);
            endIndex++;
            updateSQL.append(value.columnName);
            updateSQL.append(Expression.Equal.expression);
            updateSQL.append(CharPool.COLON);
            updateSQL.append(value.columnName);
            paramMap.put(value.columnName, value.value);
            if(endIndex!=splicingParam.valueList.size()){
                updateSQL.append(StrUtil.C_COMMA);
            }
        }
        if(uo !=0 && splicingParam.valueList.size()==1){
            throw new RuntimeException("主键不能更新,本次更新无效!");
        }
        if(isExistUpdate && FastMapperConfig.isOpenUpdateTimeAuto){
            updateSQL.append(StrUtil.C_COMMA);
            updateSQL.append(StrUtil.SPACE);
            updateSQL.append("update_time");
            updateSQL.append(Expression.Equal.expression);
            updateSQL.append(CharPool.COLON);
            updateSQL.append("update_time");
            paramMap.put("update_time", new Date());
            updateSQL.append(StrUtil.SPACE);
        }
        updateSQL.append(Expression.LineSeparator.expression);
        endIndex=0;
        if(FastMapperConfig.isOpenLogicDeletedAuto && isExistDeleted){
            long count = splicingParam.whereCondition.stream().filter(t -> t.columnName.equals(FastMapperConfig.logicDeletedColumn)).count();
            if(count==0){
                splicingParam.whereCondition.add(new SplicingParam.WhereCondition(FastMapperConfig.logicDeletedColumn,
                        FastMapperConfig.logicDeletedColumnDefaultValue, Expression.Equal.expression,true));
            }
        }
        updateSQL.append("WHERE");
        updateSQL.append(StrUtil.SPACE);
        if(splicingParam.whereCondition.size()>=2){
            for (int i = 0; i <= splicingParam.whereCondition.size()-2; i++) {
                splicingParam.whereCondition.get(i).isAnd= splicingParam.whereCondition.get(i+1).isAnd;
            }
            splicingParam.whereCondition.get(splicingParam.whereCondition.size()-1).isAnd=true;
        }
        for (SplicingParam.WhereCondition whereCondition : splicingParam.whereCondition) {
            endIndex++;
            updateSQL.append(whereCondition.columnName);
            updateSQL.append(whereCondition.expression);
            if(!Expression.Like.expression.equals(whereCondition.expression)){
                if(Expression.In.expression.equals(whereCondition.expression)){
                    if (ArrayUtil.isArray(whereCondition.value)) {
                        updateSQL.append(Expression.LeftBracket.expression);
                        Object[] wrap = ArrayUtil.wrap(whereCondition.value);
                        int j=0;
                        for (Object o : wrap) {
                            j++;
                            updateSQL.append(CharPool.COLON);
                            updateSQL.append(whereCondition.columnName).append("_in_").append(j);
                            if(j != wrap.length){
                                updateSQL.append(",");
                            }
                            paramMap.put(whereCondition.columnName + "_in_" + j, o);
                        }
                        updateSQL.append(Expression.RightBracket.expression);
                    }
                }else {
                    updateSQL.append(CharPool.COLON);
                    updateSQL.append(whereCondition.columnName).append("_").append(endIndex);
                    paramMap.put(whereCondition.columnName + "_" + endIndex, whereCondition.value);
                }
            }else{
                updateSQL.append("'%").append(whereCondition.value).append("%'");
            }
            if(endIndex!=splicingParam.whereCondition.size()){
                updateSQL.append(StrUtil.SPACE);
                if(whereCondition.isAnd){
                    updateSQL.append("and");
                }else {
                    updateSQL.append("or");
                }
                updateSQL.append(StrUtil.SPACE);
            }
        }
        if(FastMapperConfig.isOpenSQLPrint){
            SQLUtil.print(updateSQL.toString(),paramMap,"UPDATE");
        }
        int update = jdbcTemplate.update(updateSQL.toString(), paramMap);
        if(FastMapperConfig.isOpenSQLPrint){
            SQLUtil.printResult(update);
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
