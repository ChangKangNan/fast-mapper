package cn.ft.ckn.fastmapper.aspect.filed;

import cn.ft.ckn.fastmapper.bean.FastMapperParam;
import cn.ft.ckn.fastmapper.join.JoinCustomer;

/**
 * JOIN操作对象 {@link JoinCustomer} ，若需要扩展继承这个
 */
public abstract class AbstractJoinField extends AbstractField{
    @Override
    public boolean checkGlobal(FastMapperParam param) {
        return param.getSource() == FastMapperParam.ActionSource.JOIN && super.checkGlobal(param);
    }
}
