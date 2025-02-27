package cn.ft.ckn.fastmapper.aspect.base;

import cn.ft.ckn.fastmapper.aspect.filed.AbstractField;
import cn.ft.ckn.fastmapper.aspect.filed.AddOccasion;
import cn.ft.ckn.fastmapper.bean.FastMapperParam;
import cn.ft.ckn.fastmapper.bean.em.ExpanderOccasion;
import cn.ft.ckn.fastmapper.bean.em.Expression;
import cn.ft.ckn.fastmapper.config.FastMapperConfig;
import cn.ft.ckn.fastmapper.ex.MapperExpander;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.StrUtil;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomActuatorAspect implements MapperExpander {

    @Override
    public boolean before(FastMapperParam param, Method method) {
        if(CollUtil.isEmpty(FastMapperConfig.addFieldList)){
            return true;
        }
        for (AbstractField field : FastMapperConfig.addFieldList) {
            String fieldName = field.fieldName();
            Object val = field.defaultVal();
            Map<FastMapperParam.OperationType, AddOccasion> strategy = field.strategy();
            AddOccasion addOccasion = strategy.get(param.getOperationType());
            if(!field.check(param)){
                continue;
            }

            if(addOccasion == AddOccasion.OBJECT && StrUtil.equals(param.getOperationType().name(),FastMapperParam.OperationType.INSERT.name())){
                List insertList = param.getInsertList();
                if (CollUtil.isEmpty(insertList)) {
                    return true;
                }
                for (Object o : insertList) {
                    Map<String, Object> infos = new HashMap<>();
                    infos.put(fieldName, val);
                    BeanUtil.fillBeanWithMap(infos, o, true, true);
                }
            }

            if(addOccasion == AddOccasion.OBJECT && StrUtil.equals(param.getOperationType().name(),FastMapperParam.OperationType.UPDATE.name())){
                List<FastMapperParam.Value> updateValueList = param.getUpdateValueList();
                long exist = updateValueList.stream().filter(w -> StrUtil.equals(w.columnName, fieldName)).count();
                if (exist == 0) {
                    updateValueList.add(new FastMapperParam.Value(fieldName, val));
                }
            }

            if (addOccasion == AddOccasion.CONDITION && StrUtil.equalsAny(param.getOperationType().name()
                    , FastMapperParam.OperationType.UPDATE.name()
                    , FastMapperParam.OperationType.SELECT.name()
                    , FastMapperParam.OperationType.DELETE.name())) {
                List<FastMapperParam.WhereCondition> whereConditions = param.getWhereCondition();
                if (CollUtil.isEmpty(whereConditions)) {
                    return true;
                }
                long exist = whereConditions.stream().filter(w -> StrUtil.equals(w.columnName, fieldName)).count();
                if (exist == 0) {
                    whereConditions.add(new FastMapperParam.WhereCondition(fieldName, val, Expression.Equal.expression, true));
                }
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
