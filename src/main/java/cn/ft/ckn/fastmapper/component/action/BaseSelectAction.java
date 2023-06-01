package cn.ft.ckn.fastmapper.component.action;

import cn.ft.ckn.fastmapper.component.dao.SelectDao;

/**
 * @author ckn
 */
public class BaseSelectAction<T, R> extends SelectDao<T, R> {

    public BaseSelectAction(Class<R> r,Class<T> classObj) {
        super(r, classObj);
    }

}
