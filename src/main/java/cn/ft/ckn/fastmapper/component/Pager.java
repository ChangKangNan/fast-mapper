package cn.ft.ckn.fastmapper.component;

/**
 * @author ckn
 * @date 2022/8/1
 */
public interface Pager<R> {
    R page(Integer page,Integer pageSize);
}
