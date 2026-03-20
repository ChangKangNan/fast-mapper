package cn.ft.ckn.fastmapper.aspect.filed;

import cn.ft.ckn.fastmapper.bean.FastMapperParam;
import cn.ft.ckn.fastmapper.bean.em.Expression;
import cn.ft.ckn.fastmapper.config.FastMapperConfig;
import cn.hutool.core.util.StrUtil;

import java.util.EnumMap;
import java.util.Map;

public class LogicDeletedField extends AbstractMapperField {
    private static final Map<FastMapperParam.OperationType, AddOccasion> STRATEGY;

    /*
      策略
     */
    static {
        STRATEGY = new EnumMap<>(FastMapperParam.OperationType.class);
        STRATEGY.put(FastMapperParam.OperationType.INSERT, AddOccasion.OBJECT); // 插入时新增值对象
        STRATEGY.put(FastMapperParam.OperationType.DELETE, AddOccasion.CONDITION);// 以拼接条件新增在条件末尾
        STRATEGY.put(FastMapperParam.OperationType.UPDATE, AddOccasion.CONDITION);// 以拼接条件新增在条件末尾
        STRATEGY.put(FastMapperParam.OperationType.SELECT, AddOccasion.CONDITION);// 以拼接条件新增在条件末尾
    }

    /**
     * 数据库字段名称 对应@Column注解对应的值 若无则默认字段名称
     */
    @Override
    public String fieldName() {
        return FastMapperConfig.logicDeletedColumn;
    }

    /**
     * 当作为条件时 拼接的方式 ，默认为 =
     */
    @Override
    public Expression conditionLink() {
        return Expression.Equal;
    }

    /**
     *进行字段操作的时机
     * 增 删 改 查
     */
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

    /**
     * 默认值 即为插入，或者是查询更新操作时额外处理的默认值
     */
    @Override
    public Object defaultVal() {
        return FastMapperConfig.logicDeletedColumnDefaultValue;
    }

    /**
     * 定义策略
     */
    @Override
    public Map<FastMapperParam.OperationType, AddOccasion> strategy() {
        return STRATEGY;
    }
}
