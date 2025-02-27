package cn.ft.ckn.fastmapper.aspect.filed;

import cn.ft.ckn.fastmapper.bean.FastMapperParam;
import cn.ft.ckn.fastmapper.config.FastMapperConfig;
import cn.hutool.core.util.StrUtil;

import java.util.HashMap;
import java.util.Map;

public class LogicDeletedField extends AbstractField{

    @Override
    public String fieldName() {
        return FastMapperConfig.logicDeletedColumn;
    }

    @Override
    public boolean check(FastMapperParam param) {
        FastMapperParam.OperationType operationType = param.getOperationType();
        return StrUtil.equalsAny(operationType.name(),
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
        return new HashMap<FastMapperParam.OperationType, AddOccasion>(){{
            put(FastMapperParam.OperationType.INSERT,AddOccasion.OBJECT);
            put(FastMapperParam.OperationType.DELETE,AddOccasion.CONDITION);
            put(FastMapperParam.OperationType.UPDATE,AddOccasion.CONDITION);
            put(FastMapperParam.OperationType.SELECT,AddOccasion.CONDITION);
        }};
    }
}
