package cn.ft.ckn.fastmapper.component;

import cn.ft.ckn.fastmapper.config.FastMapperConfig;
import cn.ft.ckn.fastmapper.util.SQLUtil;
import cn.ft.ckn.fastmapper.util.TransactionManager;
import cn.ft.ckn.fastmapper.util.ValueUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.text.CharPool;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import org.checkerframework.checker.units.qual.C;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.util.*;
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

    public UpdateDao(Class<T> obj) {
        super(null, new SplicingParam());
        this.splicingParam = new SplicingParam();
        classObj = obj;
        returnObj = null;
    }

    public  UpdateValue<T, R> value() {
        return new UpdateValue<>(splicingParam,classObj,returnObj);
    }

    public void updateByPrimaryKey(T t) {
        Field[] fields = classObj.getDeclaredFields();
        boolean exist = false;
        for (Field field : fields) {
            Id id = field.getAnnotation(Id.class);
            if (id != null) {
                Column fieldAnnotation = field.getAnnotation(Column.class);
                exist = true;
                String pk = fieldAnnotation != null ? fieldAnnotation.name() : field.getName();
                Object value = ReflectUtil.getFieldValue(t, field.getName());
                this.splicingParam.whereCondition.add(new SplicingParam.WhereCondition(pk, value, Expression.Equal.expression, splicingParam.isAnd));
                break;
            }
        }
        if (!exist) {
            return;
        }
        update(t);
    }

    public void updateOverride(T t){
        List<ColumnParam> valueParams = ValueUtil.getColumns(t);
        if(CollUtil.isNotEmpty(valueParams)){
            for (ColumnParam valueParam : valueParams) {
                splicingParam.valueList.add(new SplicingParam.Value(valueParam.getColumnName(),valueParam.getVal()));
            }
        }
        execute();
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
                if(StrUtil.equals(name,FastMapperConfig.updateTime)){
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
            endIndex++;
            if(value.columnName.equals(primaryName)){
                uo++;
                continue;
            }
            if (!((isExistDeleted && StrUtil.equals(value.columnName, FastMapperConfig.logicDeletedColumn))
                    || (isExistUpdate && FastMapperConfig.isOpenUpdateTimeAuto && StrUtil.equals(value.columnName,FastMapperConfig.updateTime))
                    || (FastMapperConfig.isOpenCreateTimeAuto && StrUtil.equals(value.columnName,FastMapperConfig.createTime))
            )) {
                updateSQL.append(Expression.LineSeparator.expression);
                updateSQL.append(value.columnName);
                updateSQL.append(Expression.Equal.expression);
                if (value.value != null) {
                    updateSQL.append(CharPool.COLON);
                    updateSQL.append(value.columnName);
                    if (endIndex != splicingParam.valueList.size()) {
                        updateSQL.append(StrUtil.C_COMMA);
                    }
                    paramMap.put(value.columnName, value.value);
                } else {
                    updateSQL.append(StrUtil.SPACE);
                    updateSQL.append("null");
                    if (endIndex != splicingParam.valueList.size()) {
                        updateSQL.append(StrUtil.C_COMMA);
                    }
                }
            }
        }
        if(StrUtil.endWith(updateSQL.toString(),StrUtil.C_COMMA)){
            updateSQL=new StringBuilder(updateSQL.substring(0,updateSQL.length()-1));
        }
        if(uo !=0 && splicingParam.valueList.size()==1){
            throw new RuntimeException("主键不能更新,本次更新无效!");
        }
        if(isExistUpdate && FastMapperConfig.isOpenUpdateTimeAuto){
            updateSQL.append(StrUtil.C_COMMA);
            updateSQL.append(StrUtil.SPACE);
            updateSQL.append(FastMapperConfig.updateTime);
            updateSQL.append(Expression.Equal.expression);
            updateSQL.append(CharPool.COLON);
            updateSQL.append(FastMapperConfig.updateTime);
            paramMap.put(FastMapperConfig.updateTime, new Date());
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
                if(Expression.In.expression.equals(whereCondition.expression) || Expression.NotIn.expression.equals(whereCondition.expression)){
                    if (ArrayUtil.isArray(whereCondition.value)) {
                        updateSQL.append(Expression.LeftBracket.expression);
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
        DataSource dataSource = getDataSource();
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        TransactionManager.initTransaction(dataSource);
        int update = jdbcTemplate.update(updateSQL.toString(), paramMap);
        if (FastMapperConfig.isOpenSQLPrint) {
            SQLUtil.print(SQLUtil.printSql(updateSQL.toString(),paramMap)
                    , SQLUtil.printResult(update));
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
