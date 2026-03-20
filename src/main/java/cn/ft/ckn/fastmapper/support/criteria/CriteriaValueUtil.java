package cn.ft.ckn.fastmapper.support.criteria;

import cn.hutool.core.util.ArrayUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

final class CriteriaValueUtil {
    private CriteriaValueUtil() {
    }

    static Object[] normalizeInValues(Object... value) {
        if (value == null || ArrayUtil.isEmpty(value)) {
            return new Object[0];
        }
        List<Object> values = new ArrayList<>();
        for (Object o : value) {
            if (o instanceof Collection<?>) {
                values.addAll((Collection<?>) o);
            } else {
                values.add(o);
            }
        }
        values = values.stream().distinct().collect(Collectors.toList());
        return ArrayUtil.wrap(values.toArray());
    }
}
