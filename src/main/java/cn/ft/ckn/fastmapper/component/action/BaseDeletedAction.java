package cn.ft.ckn.fastmapper.component.action;

import cn.ft.ckn.fastmapper.component.dao.DeleteDao;

/**
 * @author ckn
 */
public class BaseDeletedAction<T,R> extends DeleteDao<T,R> {

    public BaseDeletedAction(Class<T> classObj, R r){
        super(r,classObj);
    }

}
