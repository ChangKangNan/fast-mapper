package cn.ft.ckn.fastmapper.expander;

import cn.ft.ckn.fastmapper.bean.SearchParam;
import cn.hutool.aop.aspects.SimpleAspect;
import cn.hutool.core.date.TimeInterval;

import java.lang.reflect.Method;

public class MapperActuatorAspect extends SimpleAspect {

    private TimeInterval interval = new TimeInterval();

    /**
     * 目标方法执行前的操作
     *
     * @param target 目标对象
     * @param method 目标方法
     * @param args   参数
     * @return 是否继续执行接下来的操作
     */
    @Override
    public boolean before(Object target, Method method, Object[] args) {
        //执行计时
        interval.start();
        return MapperExpanderRunner.runBeforeFastDaoExpander(SearchParam.get(), method.getName());
    }

    /**
     * 目标方法执行后的操作
     * 如果 target.method 抛出异常且
     *
     * @param target    目标对象
     * @param method    目标方法
     * @param args      参数
     * @param returnVal 目标方法执行返回值
     * @return 是否允许返回值（接下来的操作）
     */
    @Override
    public boolean after(Object target, Method method, Object[] args, Object returnVal) {
        //FastDaoParam封装打印
        SearchParam daoParam = SearchParam.get();
        daoParam.setSqlTime(interval.intervalMs());
        daoParam.setReturnVal(returnVal);
        MapperExpanderRunner.runAfterFastDaoExpander(daoParam, method.getName());
        return true;
    }
}
