package cn.ft.ckn.fastmapper.util;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Stock  implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String stockName;
    private Date createTime;
    private Date updateTime;
    private Boolean deleted;
}
