package cn.ft.ckn.fastmapper.bean;

import lombok.Data;

/**
 * @author ckn
 */
@Data
public class FastMapperColumn {
    private String columnName;
    private String fieldName;
    private Object val;
    private Class<?> type;
    private Boolean haveValue=false;
}
