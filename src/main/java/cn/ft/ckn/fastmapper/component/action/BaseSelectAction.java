package cn.ft.ckn.fastmapper.component.action;

import cn.ft.ckn.fastmapper.bean.FastMapperParam;
import cn.ft.ckn.fastmapper.component.dao.SelectDao;
import cn.ft.ckn.fastmapper.constants.Operation;
import cn.hutool.core.bean.BeanUtil;

/**
 * @author ckn
 */
public class BaseSelectAction<T, R> extends SelectDao<T, R> {
    private FastMapperParam fastMapperParam;

    public BaseSelectAction(Class<T> classObj, R r) {
        super(r, classObj);
        this.fastMapperParam = BeanUtil.getProperty(r, Operation.PARAM);
    }

    public void setParam(FastMapperParam fastMapperParam) {
        this.fastMapperParam = fastMapperParam;
    }

    public FastMapperParam getParam() {
        return fastMapperParam;
    }
}
