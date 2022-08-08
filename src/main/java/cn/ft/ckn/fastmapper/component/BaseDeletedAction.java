package cn.ft.ckn.fastmapper.component;

/**
 * @author ckn
 * @date 2022/8/5
 */
public class BaseDeletedAction<T,R> extends DeleteDao<T,R>{
    protected SplicingParam splicingParam;
    protected String fieldName;

    public BaseDeletedAction(SplicingParam splicingParam, Class<T> classObj, Class<R> returnObj ){
        super(splicingParam,classObj,returnObj);
        this.splicingParam=splicingParam;
    }
}
