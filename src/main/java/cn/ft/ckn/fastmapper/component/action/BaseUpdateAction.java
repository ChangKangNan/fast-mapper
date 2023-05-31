package cn.ft.ckn.fastmapper.component.action;

import cn.ft.ckn.fastmapper.component.dao.UpdateDao;

/**
 * @author ckn
 */
public class BaseUpdateAction<T, R> extends UpdateDao<T, R> {

    public BaseUpdateAction(Class<T> classObj, R r) {
        super(r, classObj);
    }

}
