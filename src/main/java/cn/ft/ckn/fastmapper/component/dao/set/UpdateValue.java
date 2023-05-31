package cn.ft.ckn.fastmapper.component.dao.set;

import cn.ft.ckn.fastmapper.annotation.SFunction;
import cn.ft.ckn.fastmapper.bean.DaoActuator;
import cn.ft.ckn.fastmapper.bean.SearchParam;
import cn.ft.ckn.fastmapper.util.ColumnUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;

import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author ckn
 */
public class UpdateValue<T, R> {
    private final Class<T> classObj;
    private R r;
    private final DaoActuator<T> daoActuator;

    public UpdateValue(R r,Class<T> classObj,DaoActuator<T> daoActuator) {
        this.classObj = classObj;
        this.r = r;
        this.daoActuator=daoActuator;
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
        if (CollUtil.isNotEmpty(SearchParam.get().getUpdateValueList())) {
            SearchParam.get().setUpdateValueList(SearchParam.get().getUpdateValueList().stream().filter(s -> !Objects.equals(s.columnName, fieldName)).collect(Collectors.toList()));
        }
        SearchParam.get().getUpdateValueList().add(new SearchParam.Value(fieldName, val));
        return this;
    }

    public void execute() {
        daoActuator.update();
    }
}
