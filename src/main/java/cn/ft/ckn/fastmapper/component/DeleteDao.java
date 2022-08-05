package cn.ft.ckn.fastmapper.component;

import cn.ft.ckn.fastmapper.config.FastMapperConfig;
import cn.hutool.core.text.CharPool;
import cn.hutool.core.util.StrUtil;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.persistence.Table;
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

    public void delete(){
        Table table = classObj.getAnnotation(Table.class);
        String tableName = table.name();
        NamedParameterJdbcTemplate jdbcTemplate = getJdbcTemplate();
        Map<String, Object> paramMap = new HashMap<>();
        StringBuilder deletedSQL;
        int endIndex=0;
        if(FastMapperConfig.isOpenLogicDeletedAuto){
            deletedSQL=new StringBuilder("UPDATE "+tableName+" set ");
            deletedSQL.append(FastMapperConfig.logicDeletedColumn);
            deletedSQL.append(Expression.Equal.expression);
            deletedSQL.append(CharPool.COLON);
            deletedSQL.append(FastMapperConfig.logicDeletedColumn);
            paramMap.put(FastMapperConfig.logicDeletedColumn, FastMapperConfig.logicDeletedColumnDeletedValue);
            deletedSQL.append(StrUtil.SPACE);
        }else{
            deletedSQL=new StringBuilder("DELETE FROM "+tableName);
            deletedSQL.append(Expression.LineSeparator.expression);
        }
        deletedSQL.append("WHERE");
        deletedSQL.append(StrUtil.SPACE);
        whereConcat(paramMap, deletedSQL, endIndex);
        jdbcTemplate.update(deletedSQL.toString(), paramMap);
    }

    private void whereConcat(Map<String, Object> paramMap, StringBuilder deletedSQL, int endIndex) {
        for (SplicingParam.WhereCondition whereCondition : splicingParam.whereCondition) {
            endIndex++;
            deletedSQL.append(whereCondition.columnName);
            deletedSQL.append(Expression.Equal.expression);
            deletedSQL.append(CharPool.COLON);
            deletedSQL.append(whereCondition.columnName+"_"+endIndex);
            paramMap.put(whereCondition.columnName+"_"+endIndex, whereCondition.value);
            if(endIndex!=splicingParam.whereCondition.size()){
                deletedSQL.append(StrUtil.SPACE);
                deletedSQL.append("and");
                deletedSQL.append(StrUtil.SPACE);
            }
        }
    }
}
