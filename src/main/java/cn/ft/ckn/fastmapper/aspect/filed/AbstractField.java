package cn.ft.ckn.fastmapper.aspect.filed;

import cn.ft.ckn.fastmapper.bean.FastMapperParam;

import java.util.Map;

public abstract class AbstractField {
    public abstract String fieldName();
    public abstract boolean check(FastMapperParam param);
    public abstract Object defaultVal();
    public abstract Map<FastMapperParam.OperationType,AddOccasion> strategy();
}
