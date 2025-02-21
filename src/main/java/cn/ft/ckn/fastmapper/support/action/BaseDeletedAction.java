package cn.ft.ckn.fastmapper.support.action;

import cn.ft.ckn.fastmapper.support.dao.DeleteDao;

/**
 * @author ckn
 */
public class BaseDeletedAction<T,R> extends DeleteDao<T,R> {

    public BaseDeletedAction(Class<R> r,Class<T> classObj){
        super(r,classObj);
    }

}
