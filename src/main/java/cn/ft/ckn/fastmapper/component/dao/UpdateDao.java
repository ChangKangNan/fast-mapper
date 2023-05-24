package cn.ft.ckn.fastmapper.component.dao;

import cn.ft.ckn.fastmapper.bean.ColumnParam;
import cn.ft.ckn.fastmapper.bean.Expression;
import cn.ft.ckn.fastmapper.bean.FastMapperParam;
import cn.ft.ckn.fastmapper.component.action.BaseUpdateAction;
import cn.ft.ckn.fastmapper.component.manager.MapperDataSourceManger;
import cn.ft.ckn.fastmapper.config.FastMapperConfig;
import cn.ft.ckn.fastmapper.constants.Operation;
import cn.ft.ckn.fastmapper.util.SQLUtil;
import cn.ft.ckn.fastmapper.transaction.TransactionManager;
import cn.ft.ckn.fastmapper.util.ValueUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.text.CharPool;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import lombok.SneakyThrows;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import cn.ft.ckn.fastmapper.component.dao.set.UpdateValue;
import javax.persistence.Column;
import javax.persistence.Id;
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
public class UpdateDao<T, R> extends MapperDataSourceManger<R> {
    private final FastMapperParam fastMapperParam;
    private final Class<T> classObj;
    private R r;

    public UpdateDao(R r,Class<T> classObj) {
        super(r);
        this.fastMapperParam = BeanUtil.getProperty(r, Operation.PARAM);
        this.classObj = classObj;
        this.r=r;
    }


    public UpdateDao(Class<T> obj) {
        this.fastMapperParam = new FastMapperParam();
        classObj = obj;
    }

    public UpdateValue<T, R> value() {
        return new UpdateValue<>(r,classObj);
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
                this.fastMapperParam.whereCondition.add(new FastMapperParam.WhereCondition(pk, value, Expression.Equal.expression, this.fastMapperParam.isAnd));
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
                this.fastMapperParam.valueList.add(new FastMapperParam.Value(valueParam.getColumnName(),valueParam.getVal()));
            }
        }
        execute();
    }

    public void update(T t) {
        List<ColumnParam> valueParams = ValueUtil.getColumns(t);
        List<ColumnParam> columnParams = valueParams.stream().filter(ColumnParam::getHaveValue).collect(Collectors.toList());
        if(CollUtil.isNotEmpty(columnParams)){
            for (ColumnParam valueParam : columnParams) {
                this.fastMapperParam.valueList.add(new FastMapperParam.Value(valueParam.getColumnName(),valueParam.getVal()));
            }
        }
        execute();
    }

    public void execute(){
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
        StringBuilder updateSQL = new StringBuilder(UPDATE);
        updateSQL.append(StrUtil.SPACE);
        updateSQL.append(tableName);
        updateSQL.append(StrUtil.SPACE);
        updateSQL.append(SET);
        updateSQL.append(StrUtil.SPACE);
        int endIndex=0;
        int uo=0;
        for (FastMapperParam.Value value : this.fastMapperParam.valueList) {
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
                    if (endIndex != this.fastMapperParam.valueList.size()) {
                        updateSQL.append(StrUtil.C_COMMA);
                    }
                    paramMap.put(value.columnName, value.value);
                } else {
                    updateSQL.append(StrUtil.SPACE);
                    updateSQL.append("null");
                    if (endIndex != this.fastMapperParam.valueList.size()) {
                        updateSQL.append(StrUtil.C_COMMA);
                    }
                }
            }
        }
        if(StrUtil.endWith(updateSQL.toString(),StrUtil.C_COMMA)){
            updateSQL=new StringBuilder(updateSQL.substring(0,updateSQL.length()-1));
        }
        if(uo !=0 && this.fastMapperParam.valueList.size()==1){
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
            long count = this.fastMapperParam.whereCondition.stream().filter(t -> t.columnName.equals(FastMapperConfig.logicDeletedColumn)).count();
            if(count==0){
                this.fastMapperParam.whereCondition.add(new FastMapperParam.WhereCondition(FastMapperConfig.logicDeletedColumn,
                        FastMapperConfig.logicDeletedColumnDefaultValue, Expression.Equal.expression,true));
            }
        }
        updateSQL.append(WHERE);
        updateSQL.append(StrUtil.SPACE);
        if(this.fastMapperParam.whereCondition.size()>=2){
            for (int i = 0; i <= this.fastMapperParam.whereCondition.size()-2; i++) {
                this.fastMapperParam.whereCondition.get(i).isAnd= this.fastMapperParam.whereCondition.get(i+1).isAnd;
            }
            this.fastMapperParam.whereCondition.get(this.fastMapperParam.whereCondition.size()-1).isAnd=true;
        }
        for (FastMapperParam.WhereCondition whereCondition : this.fastMapperParam.whereCondition) {
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
            if(endIndex!=this.fastMapperParam.whereCondition.size()){
                updateSQL.append(StrUtil.SPACE);
                if(whereCondition.isAnd){
                    updateSQL.append(AND);
                }else {
                    updateSQL.append(OR);
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
        this.fastMapperParam.isAnd = false;
        BeanUtil.setProperty(r, Operation.PARAM,fastMapperParam);
        return r;
    }
}
