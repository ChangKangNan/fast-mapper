package cn.ft.ckn.fastmapper.aspect.filed;

import cn.ft.ckn.fastmapper.bean.FastMapperParam;
import cn.ft.ckn.fastmapper.bean.em.Expression;
import cn.ft.ckn.fastmapper.join.JoinCustomer;
import cn.ft.ckn.fastmapper.util.exe.SqlExecutor;

import java.util.Map;

public abstract class AbstractField {
    /**
     * 数据库字段名称
     */
    public abstract String columnName();

    /**
     * 校验数据来源 ,Mapper对应的自动生成快捷操作类 ,JOIN操作对象 {@link JoinCustomer},SQL执行对象 {@link SqlExecutor}
     */
    public abstract boolean checkGlobal(FastMapperParam param);

    /**
     * @return 字段默认值
     */
    public abstract Object defaultVal();

    /**
     * FastMapperParam.OperationType 为对应操作的 point
     *  * Occasion.OBJECT 插入对象
     *  * Occasion.CONDITION 作为条件拼接
     * @return 对应执行策略
     */
    public abstract Map<FastMapperParam.OperationType,Occasion> strategy();

    /**
     * @return 条件时 字段的连接方式，默认等于
     */
    public Expression conditionLink(){
        return Expression.Equal;
    }

    public String columnConditionFormatterName(){
        return columnName();
    }
}
