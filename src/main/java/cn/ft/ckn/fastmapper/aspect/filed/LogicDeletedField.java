package cn.ft.ckn.fastmapper.aspect.filed;

import cn.ft.ckn.fastmapper.bean.FastMapperParam;
import cn.ft.ckn.fastmapper.config.FastMapperConfig;
import cn.hutool.core.util.StrUtil;

import java.util.EnumMap;
import java.util.Map;

public class LogicDeletedField extends AbstractField {
    private static final Map<FastMapperParam.OperationType, AddOccasion> STRATEGY;

    static {
        STRATEGY = new EnumMap<>(FastMapperParam.OperationType.class);
        STRATEGY.put(FastMapperParam.OperationType.INSERT, AddOccasion.OBJECT);
        STRATEGY.put(FastMapperParam.OperationType.DELETE, AddOccasion.CONDITION);
        STRATEGY.put(FastMapperParam.OperationType.UPDATE, AddOccasion.CONDITION);
        STRATEGY.put(FastMapperParam.OperationType.SELECT, AddOccasion.CONDITION);
    }

    @Override
    public String fieldName() {
        return FastMapperConfig.logicDeletedColumn;
    }

    @Override
    public boolean check(FastMapperParam param) {
        FastMapperParam.OperationType operationType = param.getOperationType();
        return param.getSource() == FastMapperParam.ActionSource.MAPPER && StrUtil.equalsAny(operationType.name(),
                FastMapperParam.OperationType.INSERT.name(),
                FastMapperParam.OperationType.DELETE.name(),
                FastMapperParam.OperationType.UPDATE.name(),
                FastMapperParam.OperationType.SELECT.name()
        );
    }

    @Override
    public Object defaultVal() {
        return FastMapperConfig.logicDeletedColumnDefaultValue;
    }

    @Override
    public Map<FastMapperParam.OperationType, AddOccasion> strategy() {
        return STRATEGY;
    }
}
