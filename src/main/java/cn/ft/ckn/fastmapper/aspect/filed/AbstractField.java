package cn.ft.ckn.fastmapper.aspect.filed;

import cn.ft.ckn.fastmapper.bean.FastMapperParam;
import cn.ft.ckn.fastmapper.bean.em.Expression;

import java.util.Map;

public abstract class AbstractField {
    public abstract String fieldName();

    public boolean checkGlobal(FastMapperParam param) {
        return check(param);
    }
    public abstract boolean check(FastMapperParam param);

    public abstract Object defaultVal();

    public abstract Map<FastMapperParam.OperationType,Occasion> strategy();

    public Expression conditionLink(){
        return Expression.Equal;
    }
}
