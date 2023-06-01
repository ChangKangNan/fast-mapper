package cn.ft.ckn.fastmapper.aspect;

import cn.ft.ckn.fastmapper.bean.Expression;
import cn.ft.ckn.fastmapper.bean.SearchParam;
import cn.ft.ckn.fastmapper.bean.TableMapper;
import cn.ft.ckn.fastmapper.config.FastMapperConfig;
import cn.ft.ckn.fastmapper.expander.ExpanderOccasion;
import cn.ft.ckn.fastmapper.expander.MapperExpander;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.StrUtil;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ckn
 * @date 2023/5/31
 */
public class CustomActuatorAspect implements MapperExpander {
    @Override
    public boolean before(SearchParam param, Method method) {
        String methodName = method.getName();
        // value
        boolean setValBool = StrUtil.equalsAnyIgnoreCase(methodName, ExpanderOccasion.INSERT.name(), ExpanderOccasion.UPDATE.name(), ExpanderOccasion.DELETE.name());
        if (setValBool) {
            if (StrUtil.equalsAnyIgnoreCase(methodName, ExpanderOccasion.INSERT.name())) {
                List insertList = param.getInsertList();
                if (CollUtil.isEmpty(insertList)) {
                    return true;
                }
                for (Object o : insertList) {
                    Map<String, Object> infos = new HashMap<>();
                    if (StrUtil.isNotBlank(FastMapperConfig.createTime)) {
                        infos.put(FastMapperConfig.createTime, new Date());
                    }
                    if (StrUtil.isNotBlank(FastMapperConfig.logicDeletedColumn)) {
                        infos.put(FastMapperConfig.logicDeletedColumn, FastMapperConfig.logicDeletedColumnDefaultValue);
                    }
                    BeanUtil.fillBeanWithMap(infos, o, true, true);
                }
            } else {
                List<SearchParam.Value> updateValueList = param.getUpdateValueList();
                updateValueList.add(new SearchParam.Value(FastMapperConfig.updateTime, new Date()));
            }
        }
        boolean setWhereBool = StrUtil.equalsAnyIgnoreCase(methodName, ExpanderOccasion.SELECT.name(), ExpanderOccasion.UPDATE.name(), ExpanderOccasion.DELETE.name());
        if (setWhereBool) {
            List<SearchParam.WhereCondition> whereConditions = param.getWhereCondition();
            if (CollUtil.isEmpty(whereConditions)) {
                return true;
            }
            String primaryKey = param.getTableMapper().getPrimaryKey();
            long existDelete = whereConditions.stream().filter(w -> StrUtil.equals(w.columnName, FastMapperConfig.logicDeletedColumn)).count();
          //long existPk = whereConditions.stream().filter(w -> StrUtil.equals(w.columnName, primaryKey)).count();
            if (existDelete == 0) {
                whereConditions.add(new SearchParam.WhereCondition(FastMapperConfig.logicDeletedColumn, FastMapperConfig.logicDeletedColumnDefaultValue, Expression.Equal.expression, true));
            }
        }
        return true;
    }

    @Override
    public void after(SearchParam param, Method method) {

    }

    @Override
    public void afterException(SearchParam param, Method method) {

    }

    @Override
    public List<ExpanderOccasion> occasion() {
        return ListUtil.of(ExpanderOccasion.INSERT, ExpanderOccasion.DELETE, ExpanderOccasion.UPDATE, ExpanderOccasion.SELECT);
    }
}
