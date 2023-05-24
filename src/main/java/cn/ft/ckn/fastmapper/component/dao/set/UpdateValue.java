package cn.ft.ckn.fastmapper.component.dao.set;

import cn.ft.ckn.fastmapper.annotation.SFunction;
import cn.ft.ckn.fastmapper.bean.FastMapperParam;
import cn.ft.ckn.fastmapper.component.dao.UpdateDao;
import cn.ft.ckn.fastmapper.constants.Operation;
import cn.ft.ckn.fastmapper.util.ColumnUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;

import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author ckn
 * @date 2022/8/5
 */
public class UpdateValue<T, R> {
    private final FastMapperParam fastMapperParam;
    private final Class<T> classObj;
    private R r;

    public UpdateValue(R r,Class<T> classObj) {
        this.fastMapperParam = BeanUtil.getProperty(r, Operation.PARAM);
        this.classObj = classObj;
        this.r = r;
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
        if (CollUtil.isNotEmpty(this.fastMapperParam.valueList)) {
            this.fastMapperParam.valueList = this.fastMapperParam.valueList.stream().filter(s -> !Objects.equals(s.columnName, fieldName)).collect(Collectors.toList());
        }
        this.fastMapperParam.valueList.add(new FastMapperParam.Value(fieldName, val));
        return this;
    }

    public void execute() {
        new UpdateDao<T, R>(r, classObj).execute();
    }
}
