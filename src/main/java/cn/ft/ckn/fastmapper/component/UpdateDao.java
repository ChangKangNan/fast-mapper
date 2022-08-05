package cn.ft.ckn.fastmapper.component;

import cn.ft.ckn.fastmapper.util.ValueUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.text.CharPool;
import cn.hutool.core.util.StrUtil;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.persistence.Table;
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
        StringBuilder updateSQL = new StringBuilder("UPDATE " + tableName +" set ");
        int endIndex=0;
        for (SplicingParam.Value value : splicingParam.valueList) {
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
        updateSQL.append(Expression.LineSeparator.expression);
        endIndex=0;
        updateSQL.append("WHERE");
        updateSQL.append(StrUtil.SPACE);
        for (SplicingParam.WhereCondition whereCondition : splicingParam.whereCondition) {
            endIndex++;
            updateSQL.append(whereCondition.columnName);
            updateSQL.append(Expression.Equal.expression);
            updateSQL.append(CharPool.COLON);
            updateSQL.append(whereCondition.columnName+"_"+endIndex);
            paramMap.put(whereCondition.columnName+"_"+endIndex, whereCondition.value);
            if(endIndex!=splicingParam.whereCondition.size()){
                updateSQL.append(StrUtil.SPACE);
                updateSQL.append("and");
                updateSQL.append(StrUtil.SPACE);
            }
        }
        jdbcTemplate.update(updateSQL.toString(), paramMap);
    }
}
