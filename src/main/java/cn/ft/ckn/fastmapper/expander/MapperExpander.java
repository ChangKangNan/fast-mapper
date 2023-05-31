package cn.ft.ckn.fastmapper.expander;

import cn.ft.ckn.fastmapper.bean.SearchParam;

import java.lang.reflect.Method;
import java.util.List;

public interface MapperExpander {

    /**
     * Dao执行前的操作
     *
     * @param param 目标对象
     * @return 是否继续执行接下来的操作
     */
    boolean before(SearchParam param, Method method);

    /**
     * Dao执行后的操作
     *
     * @param param 目标对象
     */
    void after(SearchParam param, Method method);


    void afterException(SearchParam param, Method method);
    /**
     * 执行场景
     * @return INSERT,SELECT,UPDATE,DELETE
     */
    List<ExpanderOccasion> occasion();


}
