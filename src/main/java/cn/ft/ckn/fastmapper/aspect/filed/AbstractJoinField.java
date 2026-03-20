package cn.ft.ckn.fastmapper.aspect.filed;

import cn.ft.ckn.fastmapper.bean.FastMapperParam;
/**
 * JOIN操作对象 ，若需要扩展继承这个
 */
public abstract class AbstractJoinField extends AbstractField{
    @Override
    public boolean checkGlobal(FastMapperParam param) {
        return param.getSource() == FastMapperParam.ActionSource.JOIN && super.checkGlobal(param);
    }
}
