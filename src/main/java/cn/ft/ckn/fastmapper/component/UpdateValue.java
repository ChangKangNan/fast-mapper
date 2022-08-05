package cn.ft.ckn.fastmapper.component;

import cn.ft.ckn.fastmapper.util.ColumnUtil;

/**
 * @author ckn
 * @date 2022/8/5
 */
public class UpdateValue<T, R> {
    private final SplicingParam splicingParam;
    private final Class<T> classObj;
    private final Class<R> returnObj;

    UpdateValue(SplicingParam splicingParam, Class<T> classObj, Class<R> returnObj) {
        this.splicingParam = splicingParam;
        this.classObj = classObj;
        this.returnObj = returnObj;
    }

    public UpdateValue<T, R> set(SFunction<T, ?> function, Object val) {
        String fieldName = ColumnUtil.getFieldName(function);
        if (val != null) {
            splicingParam.valueList.add(new SplicingParam.Value(fieldName, val));
        }
        return this;
    }

    public void execute() {
        new UpdateDao<T, R>(splicingParam, classObj, returnObj).execute();
    }
}
