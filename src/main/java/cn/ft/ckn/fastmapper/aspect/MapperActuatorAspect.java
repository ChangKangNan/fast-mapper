package cn.ft.ckn.fastmapper.aspect;

import cn.ft.ckn.fastmapper.bean.SearchParam;
import cn.ft.ckn.fastmapper.expander.MapperExpanderRunner;
import cn.hutool.aop.aspects.SimpleAspect;
import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.util.StrUtil;

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
        if(StrUtil.equals(method.getName().toUpperCase(), SearchParam.OperationType.INSERT.name())){
            SearchParam.get().setOperationType(SearchParam.OperationType.INSERT);
        }else if(StrUtil.equals(method.getName().toUpperCase(), SearchParam.OperationType.UPDATE.name())){
            SearchParam.get().setOperationType(SearchParam.OperationType.UPDATE);
        }else if(StrUtil.equals(method.getName().toUpperCase(), SearchParam.OperationType.DELETE.name())){
            SearchParam.get().setOperationType(SearchParam.OperationType.DELETE);
        }else if(StrUtil.equals(method.getName().toUpperCase(), SearchParam.OperationType.SELECT.name())){
            SearchParam.get().setOperationType(SearchParam.OperationType.SELECT);
        }
        return MapperExpanderRunner.runBeforeExpander(SearchParam.get(), method.getName(),method);
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
        SearchParam daoParam = SearchParam.get();
        daoParam.setSqlTime(interval.intervalMs());
        daoParam.setReturnVal(returnVal);
        MapperExpanderRunner.runAfterExpander(daoParam, method.getName(),method);
        return true;
    }

    @Override
    public boolean afterException(Object target, Method method, Object[] args, Throwable e) {
        //继承此类后实现此方法
        SearchParam daoParam = SearchParam.get();
        MapperExpanderRunner.runAfterExceptionExpander(daoParam, method.getName(),method);
        return true;
    }

}
