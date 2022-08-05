package cn.ft.ckn.fastmapper.component;

import lombok.Data;

/**
 * @author ckn
 * @date 2022/8/3
 */
@Data
public class ColumnParam {
    private String columnName;
    private String fieldName;
    private Object val;
    private Class<?> type;
    private Boolean haveValue=false;
}
