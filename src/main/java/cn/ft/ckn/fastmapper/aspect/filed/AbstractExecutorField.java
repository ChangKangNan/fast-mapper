package cn.ft.ckn.fastmapper.aspect.filed;

import cn.ft.ckn.fastmapper.bean.FastMapperParam;
/**
 * sql执行器 ，若需要扩展继承这个
 */
public abstract class AbstractExecutorField extends AbstractField{
    @Override
    public boolean checkGlobal(FastMapperParam param) {
        return param.getSource() == FastMapperParam.ActionSource.EXECUTOR && super.checkGlobal(param);
    }
}
