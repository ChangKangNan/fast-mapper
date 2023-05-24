package cn.ft.ckn.fastmapper.bean;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * 过程传递参数
 * @author ckn
 */
public class FastMapperParam {
    /**
     * 是否主数据源
     */
    public Boolean isMaster = true;
    /**
     * 当前数据源
     */
    public DataSource dataSource;
    /**
     * 逻辑删除保护是否关闭
     */
    public Boolean isCloseDeleteProtect=Boolean.FALSE;
    /**
     * where条件封装
     */
    public List<WhereCondition> whereCondition;
    /**
     * order by条件封装
     */
    public List<OrderByCondition> orderByCondition;
    /**
     * 数据操作值封装
     */
    public List<Value> valueList;
    /**
     * 连接符是否为and
     */
    public Boolean isAnd=Boolean.TRUE;

    void init(){
        //初始化
        whereCondition = new ArrayList<>();
        orderByCondition = new ArrayList<>();
        valueList = new ArrayList<>();
        page = 0;
        pageSize = 0;
    }

    public FastMapperParam() {
        init();
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
