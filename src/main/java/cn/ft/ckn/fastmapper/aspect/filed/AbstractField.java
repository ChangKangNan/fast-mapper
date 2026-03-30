package cn.ft.ckn.fastmapper.aspect.filed;

import cn.ft.ckn.fastmapper.bean.FastMapperParam;
import cn.ft.ckn.fastmapper.bean.em.Expression;
import cn.ft.ckn.fastmapper.bean.em.FillConditionStrategy;
import cn.ft.ckn.fastmapper.bean.em.FillObjStrategy;
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
     * 填充对象{@link FillObjStrategy}
     * @return 对应执行策略
     */
    public FillObjStrategy fillObjStrategy(){return FillObjStrategy.NON;}

    /**
     * 填充条件{@link FillConditionStrategy}
     * @return 对应执行策略
     */
    public  FillConditionStrategy fillConditionStrategy(){return FillConditionStrategy.NON;}


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
