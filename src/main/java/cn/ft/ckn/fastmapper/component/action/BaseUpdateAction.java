package cn.ft.ckn.fastmapper.component.action;

import cn.ft.ckn.fastmapper.component.dao.UpdateDao;

/**
 * @author ckn
 */
public class BaseUpdateAction<T, R> extends UpdateDao<T, R> {

    public BaseUpdateAction(Class<R> r,Class<T> classObj) {
        super(r, classObj);
    }

}
