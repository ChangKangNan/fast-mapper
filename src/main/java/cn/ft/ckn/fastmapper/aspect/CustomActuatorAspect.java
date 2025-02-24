package cn.ft.ckn.fastmapper.aspect;

import cn.ft.ckn.fastmapper.bean.em.Expression;
import cn.ft.ckn.fastmapper.bean.FastMapperParam;
import cn.ft.ckn.fastmapper.config.FastMapperConfig;
import cn.ft.ckn.fastmapper.bean.em.ExpanderOccasion;
import cn.ft.ckn.fastmapper.ex.MapperExpander;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomActuatorAspect implements MapperExpander {
    @Override
    public boolean before(FastMapperParam param, Method method) {
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
                List<FastMapperParam.Value> updateValueList = param.getUpdateValueList();
                updateValueList.add(new FastMapperParam.Value(FastMapperConfig.updateTime, DateUtil.format(new Date(),"yyyy-MM-dd hh:mm:ss")));
            }
        }
        boolean setWhereBool = StrUtil.equalsAnyIgnoreCase(methodName, ExpanderOccasion.SELECT.name(), ExpanderOccasion.UPDATE.name(), ExpanderOccasion.DELETE.name());
        if (setWhereBool) {
            List<FastMapperParam.WhereCondition> whereConditions = param.getWhereCondition();
            if (CollUtil.isEmpty(whereConditions)) {
                return true;
            }
            long existDelete = whereConditions.stream().filter(w -> StrUtil.equals(w.columnName, FastMapperConfig.logicDeletedColumn)).count();
            if (existDelete == 0) {
                whereConditions.add(new FastMapperParam.WhereCondition(FastMapperConfig.logicDeletedColumn, FastMapperConfig.logicDeletedColumnDefaultValue, Expression.Equal.expression, true));
            }
        }
        return true;
    }

    @Override
    public void after(FastMapperParam param, Method method) {

    }

    @Override
    public void afterException(FastMapperParam param, Method method) {

    }

    @Override
    public List<ExpanderOccasion> occasion() {
        return ListUtil.of(ExpanderOccasion.INSERT, ExpanderOccasion.DELETE, ExpanderOccasion.UPDATE, ExpanderOccasion.SELECT);
    }
}
