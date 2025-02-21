package cn.ft.ckn.fastmapper.bean.page;

import lombok.Builder;
import lombok.Data;

/**
 * @author ckn
 * @date 2022/12/30
 */
@Data
@Builder
public class Page {
    private int pageNum;
    private int pageSize;
}
