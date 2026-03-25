package cn.ft.ckn.fastmapper.aspect.base;

import cn.ft.ckn.fastmapper.aspect.filed.AbstractField;
import cn.ft.ckn.fastmapper.aspect.filed.Occasion;
import cn.ft.ckn.fastmapper.bean.FastMapperParam;
import cn.ft.ckn.fastmapper.bean.em.ExpanderOccasion;
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
        if (CollUtil.isEmpty(FastMapperConfig.addFieldList) || (param.isAddFieldBefore())) {
            return true;
        }

        boolean needGroup = FastMapperConfig.addFieldList.stream().allMatch(f -> f.checkGlobal(param) && f.strategy().get(param.getOperationType()) == Occasion.CONDITION);
        if (needGroup) {
            FastMapperParam.get().setBracket(FastMapperParam.Bracket.builder().leftIndex(0).rightIndex(FastMapperParam.get().getWhereCondition().size() - 1).build());
        }

        for (AbstractField field : FastMapperConfig.addFieldList) {
            String fieldName = field.fieldName();
            Object val = field.defaultVal();
            Map<FastMapperParam.OperationType, Occasion> strategy = field.strategy();
            Occasion occasion = strategy.get(param.getOperationType());
            if (!field.checkGlobal(param)) {
                continue;
            }

            if (occasion == Occasion.OBJECT && StrUtil.equals(param.getOperationType().name(), FastMapperParam.OperationType.INSERT.name())) {
                List insertList = param.getInsertList();
                Map<String, String> fieldToColumn = param.getTableMapper().getFieldToColumn();
                String insertFillField = fieldName;
                for (String key : fieldToColumn.keySet()) {
                    String column = fieldToColumn.get(key);
                    if (column.equals(fieldName)) {
                        insertFillField = key;
                        break;
                    }
                }
                if (CollUtil.isEmpty(insertList)) {
                    return true;
                }
                for (Object o : insertList) {
                    Map<String, Object> infos = new HashMap<>();
                    infos.put(insertFillField, val);
                    //内部会转驼峰，无论是否设置isToCamelCase
                    BeanUtil.fillBeanWithMap(infos, o, true, true);
                }
            }

            if (occasion == Occasion.OBJECT && StrUtil.equals(param.getOperationType().name(), FastMapperParam.OperationType.UPDATE.name())) {
                List<FastMapperParam.Value> updateValueList = param.getUpdateValueList();
                long exist = updateValueList.stream().filter(w -> StrUtil.equals(w.columnName, fieldName)).count();
                if (exist == 0) {
                    updateValueList.add(new FastMapperParam.Value(fieldName, val));
                }
            }

            if (occasion == Occasion.CONDITION && StrUtil.equalsAny(param.getOperationType().name()
                    , FastMapperParam.OperationType.UPDATE.name()
                    , FastMapperParam.OperationType.SELECT.name()
                    , FastMapperParam.OperationType.DELETE.name())) {
                List<FastMapperParam.WhereCondition> whereConditions = param.getWhereCondition();
                whereConditions.add(new FastMapperParam.WhereCondition(fieldName, val, field.conditionLink(), true));
            }

        }
        param.setAddFieldBefore(true);
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
