package cn.ft.ckn.fastmapper.aspect.filed;

import cn.ft.ckn.fastmapper.bean.em.Expression;
import cn.ft.ckn.fastmapper.bean.em.FillConditionStrategy;
import cn.ft.ckn.fastmapper.bean.em.FillObjStrategy;
import cn.ft.ckn.fastmapper.config.FastMapperConfig;

public class LogicDeletedField extends AbstractMapperField {

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

    @Override
    public FillObjStrategy fillObjStrategy() {
        return FillObjStrategy.INSERT_OBJ;
    }

    @Override
    public FillConditionStrategy fillConditionStrategy() {
        return FillConditionStrategy.SELECT_UPDATE_DELETE_CONDITION;
    }


}
