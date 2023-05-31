package cn.ft.ckn.fastmapper.aspect;

import cn.ft.ckn.fastmapper.bean.SearchParam;
import cn.ft.ckn.fastmapper.expander.ExpanderOccasion;
import cn.ft.ckn.fastmapper.expander.MapperExpander;
import cn.hutool.core.collection.ListUtil;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author ckn
 * @date 2023/5/31
 */
public class CustomActuatorAspect implements MapperExpander {
    @Override
    public boolean before(SearchParam param, Method method) {
        return false;
    }

    @Override
    public void after(SearchParam param, Method method) {

    }

    @Override
    public void afterException(SearchParam param, Method method) {

    }

    @Override
    public List<ExpanderOccasion> occasion() {
        return ListUtil.of(ExpanderOccasion.INSERT, ExpanderOccasion.DELETE, ExpanderOccasion.UPDATE, ExpanderOccasion.SELECT);
    }
}
