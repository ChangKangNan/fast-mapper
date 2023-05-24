package cn.ft.ckn.fastmapper.component.action;

import cn.ft.ckn.fastmapper.bean.FastMapperParam;
import cn.ft.ckn.fastmapper.component.dao.UpdateDao;
import cn.ft.ckn.fastmapper.constants.Operation;
import cn.hutool.core.bean.BeanUtil;

/**
 * @author ckn
 */
public class BaseUpdateAction<T,R> extends UpdateDao<T,R> {
    protected FastMapperParam fastMapperParam;

    public BaseUpdateAction(Class<T> classObj,R r){
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
