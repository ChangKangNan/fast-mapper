package cn.ft.ckn.fastmapper.anno;

public interface Pager<R> {
    R page(Integer page, Integer pageSize);
}
