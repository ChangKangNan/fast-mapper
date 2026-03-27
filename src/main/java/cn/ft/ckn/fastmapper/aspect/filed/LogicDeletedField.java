package cn.ft.ckn.fastmapper.aspect.filed;

import cn.ft.ckn.fastmapper.bean.FastMapperParam;
import cn.ft.ckn.fastmapper.bean.em.Expression;
import cn.ft.ckn.fastmapper.config.FastMapperConfig;

import java.util.EnumMap;
import java.util.Map;

public class LogicDeletedField extends AbstractMapperField {

    /**
     * 定义策略
     */
    @Override
    public Map<FastMapperParam.OperationType, Occasion> strategy() {
        return new EnumMap<FastMapperParam.OperationType, Occasion>(FastMapperParam.OperationType.class)
        {{
            put(FastMapperParam.OperationType.INSERT, Occasion.OBJECT);
            put(FastMapperParam.OperationType.DELETE, Occasion.CONDITION);
            put(FastMapperParam.OperationType.UPDATE, Occasion.CONDITION);
            put(FastMapperParam.OperationType.SELECT, Occasion.CONDITION);
        }};
    }

    /**
     * 数据库字段名称
     */
    @Override
    public String columnName() {
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
     * 默认值 即为插入，或者是查询更新操作时额外处理的默认值
     */
    @Override
    public Object defaultVal() {
        return FastMapperConfig.logicDeletedColumnDefaultValue;
    }


}
