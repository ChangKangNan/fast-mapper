package cn.ft.ckn.fastmapper.anno;

import cn.ft.ckn.fastmapper.bean.page.PageInfo;

public interface Pager<T> {
    PageInfo<T> page(Integer page, Integer pageSize);
}
