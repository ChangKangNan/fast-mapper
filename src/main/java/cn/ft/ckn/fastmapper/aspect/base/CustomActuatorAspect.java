package cn.ft.ckn.fastmapper.aspect.base;

import cn.ft.ckn.fastmapper.aspect.filed.AbstractField;
import cn.ft.ckn.fastmapper.aspect.filed.Occasion;
import cn.ft.ckn.fastmapper.bean.FastMapperParam;
import cn.ft.ckn.fastmapper.bean.em.ExpanderOccasion;
import cn.ft.ckn.fastmapper.bean.em.FillConditionStrategy;
import cn.ft.ckn.fastmapper.bean.em.FillObjStrategy;
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

        boolean needGroup = FastMapperConfig.addFieldList.stream().anyMatch(f -> f.checkGlobal(param) && condition(param,f.fillConditionStrategy()));
        List<FastMapperParam.WhereCondition> whereCondition = param.getWhereCondition();
        boolean isContainsOr = CollUtil.isEmpty(whereCondition);
        if (!isContainsOr) {
            List<FastMapperParam.Bracket> brackets = FastMapperParam.get().getBrackets();
            for (int i = 0; i < whereCondition.size(); i++) {
                FastMapperParam.WhereCondition condition = whereCondition.get(i);
                int finalI = i;
                long l = brackets.stream().filter(f -> f.getLeftIndex() == finalI).count();
                if (i != 0 && l > 0 && !condition.isAnd) {
                    isContainsOr = true;
                    break;
                }
            }
            if (needGroup && isContainsOr) {
                FastMapperParam.get().setBracket(FastMapperParam.Bracket.builder().leftIndex(0).rightIndex(FastMapperParam.get().getWhereCondition().size() - 1).build());
            }
        }

        for (AbstractField field : FastMapperConfig.addFieldList) {
            String fieldName = field.columnName();
            Object val = field.defaultVal();
            FillConditionStrategy fillConditionStrategy = field.fillConditionStrategy();
            FillObjStrategy fillObjStrategy = field.fillObjStrategy();

            if (!field.checkGlobal(param)) {
                continue;
            }

            if (fillObjStrategy == FillObjStrategy.INSERT_OBJ && StrUtil.equals(param.getOperationType().name(), FastMapperParam.OperationType.INSERT.name())) {
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

            if ((fillObjStrategy == FillObjStrategy.UPDATE_OBJ || fillObjStrategy == FillObjStrategy.INSERT_UPDATE_OBJ) && StrUtil.equals(param.getOperationType().name(), FastMapperParam.OperationType.UPDATE.name())) {
                List<FastMapperParam.Value> updateValueList = param.getUpdateValueList();
                long exist = updateValueList.stream().filter(w -> StrUtil.equals(w.columnName, fieldName)).count();
                if (exist == 0) {
                    updateValueList.add(new FastMapperParam.Value(fieldName, val));
                }
            }

            if (condition(param, fillConditionStrategy)) {
                List<FastMapperParam.WhereCondition> whereConditions = param.getWhereCondition();
                String conditionFormatterName = field.columnConditionFormatterName();
                String columnName = field.columnName();
                boolean match = whereConditions.stream().filter(f -> f.columnName != null).anyMatch(w -> w.columnName.equals(columnName));
                if (match) {
                    continue;
                }
                if (StrUtil.equals(columnName, conditionFormatterName)) {
                    whereConditions.add(new FastMapperParam.WhereCondition(fieldName, val, field.conditionLink(), true));
                } else {
                    whereConditions.add(new FastMapperParam.WhereCondition(fieldName, val, field.conditionLink(), conditionFormatterName, true, true));
                }
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

    private boolean condition(FastMapperParam param,FillConditionStrategy fillConditionStrategy){
        boolean needInsertCondition = (StrUtil.equals(param.getOperationType().name(), FastMapperParam.OperationType.SELECT.name()) && (fillConditionStrategy == FillConditionStrategy.SELECT_CONDITION || fillConditionStrategy == FillConditionStrategy.SELECT_UPDATE_CONDITION || fillConditionStrategy == FillConditionStrategy.SELECT_UPDATE_DELETE_CONDITION));
        boolean needUpdateCondition = (StrUtil.equals(param.getOperationType().name(), FastMapperParam.OperationType.UPDATE.name()) && (fillConditionStrategy == FillConditionStrategy.UPDATE_CONDITION || fillConditionStrategy == FillConditionStrategy.SELECT_UPDATE_CONDITION || fillConditionStrategy == FillConditionStrategy.SELECT_UPDATE_DELETE_CONDITION));
        boolean needDeleteCondition = (StrUtil.equals(param.getOperationType().name(), FastMapperParam.OperationType.DELETE.name()) && (fillConditionStrategy == FillConditionStrategy.DELETE_CONDITION || fillConditionStrategy == FillConditionStrategy.UPDATE_DELETE_CONDITION || fillConditionStrategy == FillConditionStrategy.SELECT_UPDATE_DELETE_CONDITION));
        return needInsertCondition || needUpdateCondition || needDeleteCondition;
    }
}
