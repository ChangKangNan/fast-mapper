package cn.ft.ckn.fastmapper.component.action;

import cn.ft.ckn.fastmapper.bean.FastMapperParam;
import cn.ft.ckn.fastmapper.component.dao.DeleteDao;
import cn.ft.ckn.fastmapper.constants.Operation;
import cn.hutool.core.bean.BeanUtil;

/**
 * @author ckn
 */
public class BaseDeletedAction<T,R> extends DeleteDao<T,R> {
    protected FastMapperParam fastMapperParam;

    public BaseDeletedAction(Class<T> classObj, R r){
        super(r,classObj);
        this.fastMapperParam = BeanUtil.getProperty(r, Operation.PARAM);
    }

    public void setParam(FastMapperParam fastMapperParam) {
        this.fastMapperParam = fastMapperParam;
    }

    public FastMapperParam getParam() {
        return fastMapperParam;
    }
}
