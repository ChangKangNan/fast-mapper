package cn.ft.ckn.fastmapper.component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ckn
 * @date 2022/8/1
 */
public class SplicingParam {
    public Boolean isMaster = true;
    public DataSource dataSource;
    public Boolean isCloseDeleteProtect=Boolean.FALSE;
    public List<WhereCondition> whereCondition;
    public List<OrderByCondition> orderByCondition;
    public List<Value> valueList;
    public Boolean isAnd=Boolean.TRUE;

    public SplicingParam() {
        whereCondition = new ArrayList<>();
        orderByCondition = new ArrayList<>();
        valueList = new ArrayList<>();
        page = 0;
        pageSize = 0;
    }

    private Integer page;
    private Integer pageSize;
    /**
     * 使否开启分页查询
     */
    public Boolean isOpenPage = false;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public static class WhereCondition {
        public String expression;
        public String columnName;
        public Object value;
        public Boolean isAnd;
        public WhereCondition(String columnName, Object value, String expression,Boolean isAnd) {
            this.expression = expression;
            this.columnName = columnName;
            this.value = value;
            this.isAnd=isAnd;
        }
    }
    public static class Value {
        public String columnName;
        public Object value;

        public Value(String columnName, Object value) {
            this.columnName = columnName;
            this.value = value;
        }
    }

    public static class OrderByCondition {
        public String orderByName;
        public String sequence;

        public OrderByCondition(String orderByName, String sequence) {
            this.orderByName = orderByName;
            this.sequence = sequence;
        }
    }


}
