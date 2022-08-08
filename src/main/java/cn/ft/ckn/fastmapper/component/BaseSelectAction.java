package cn.ft.ckn.fastmapper.component;

/**
 * @author ckn
 * @date 2022/7/28
 */
public class BaseSelectAction<T,R> extends SelectDao<T,R>{
    protected SplicingParam splicingParam;
    protected String fieldName;

   public BaseSelectAction(SplicingParam splicingParam, Class<T> classObj, Class<R> returnObj ){
       super(splicingParam,classObj,returnObj);
       this.splicingParam=splicingParam;
    }
}
