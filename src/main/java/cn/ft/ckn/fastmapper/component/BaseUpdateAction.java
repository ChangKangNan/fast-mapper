package cn.ft.ckn.fastmapper.component;

/**
 * @author ckn
 * @date 2022/8/5
 */
public class BaseUpdateAction<T,R> extends UpdateDao<T,R>{
    protected SplicingParam splicingParam;
    protected String fieldName;

    public BaseUpdateAction(SplicingParam splicingParam, Class<T> classObj, Class<R> returnObj ){
        super(splicingParam,classObj,returnObj);
        this.splicingParam=splicingParam;
    }
}
