package cn.ft.ckn.fastmapper.component.dao.set;

import cn.ft.ckn.fastmapper.annotation.SFunction;
import cn.ft.ckn.fastmapper.bean.SplicingParam;
import cn.ft.ckn.fastmapper.component.dao.UpdateDao;
import cn.ft.ckn.fastmapper.util.ColumnUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;

import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author ckn
 * @date 2022/8/5
 */
public class UpdateValue<T, R> {
    private final SplicingParam splicingParam;
    private final Class<T> classObj;
    private final Class<R> returnObj;

    public UpdateValue(SplicingParam splicingParam, Class<T> classObj, Class<R> returnObj) {
        this.splicingParam = splicingParam;
        this.classObj = classObj;
        this.returnObj = returnObj;
    }

    public <V> UpdateValue<T, R> set(SFunction<T, V> function, V val) {
        if (val == null) {
            return this;
        }
        Class<?> fieldType = ColumnUtil.getFieldType(function);
        String fieldName = ColumnUtil.getFieldName(function);
        String fieldTypeName = fieldType.getName();
        String parameterType = val.getClass().getName();
        if (!StrUtil.equals(fieldTypeName, parameterType)) {
            throw new RuntimeException(fieldName + "需要类型:"+fieldTypeName+",提供的参数类型:"+parameterType+",两者不匹配!");
        }
        if (CollUtil.isNotEmpty(splicingParam.valueList)) {
            splicingParam.valueList = splicingParam.valueList.stream().filter(s -> !Objects.equals(s.columnName, fieldName)).collect(Collectors.toList());
        }
        splicingParam.valueList.add(new SplicingParam.Value(fieldName, val));
        return this;
    }

    public void execute() {
        new UpdateDao<T, R>(splicingParam, classObj, returnObj).execute();
    }
}
