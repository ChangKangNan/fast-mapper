package cn.ft.ckn.fastmapper.bean;

import io.netty.util.concurrent.FastThreadLocal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ckn
 */
public class SearchParam<T> {
    private static FastThreadLocal<SearchParam> searchParamFastThreadLocal=new FastThreadLocal<>();

    public static <T> SearchParam<T> init(TableMapper<T> tableMapper) {
        //初始化默认查询信息
        SearchParam<T> searchParam = searchParamFastThreadLocal.get();
        if (searchParam == null) {
            searchParam = new SearchParam<>();
            searchParamFastThreadLocal.set(searchParam);
        }
        searchParam.tableMapper=tableMapper;
        searchParam.insertList=new ArrayList<>();
        searchParam.operationType = null;
        searchParam.executeSql=null;
        searchParam.whereCondition=new ArrayList<>();
        searchParam.updateValueList=new ArrayList<>();
        searchParam.orderByCondition=new ArrayList<>();
        searchParam.setCloseDeleteProtect(Boolean.FALSE);
        searchParam.paramMap=new HashMap<>();
        return searchParam;
    }

    public static <T> SearchParam<T> get() {
        return searchParamFastThreadLocal.get();
    }

    public static void setSearchParamFastThreadLocal(FastThreadLocal<SearchParam> searchParamFastThreadLocal) {
        SearchParam.searchParamFastThreadLocal = searchParamFastThreadLocal;
    }

    private TableMapper tableMapper;

    public Map<String, Object> getParamMap() {
        return paramMap;
    }

    public void setParamMap(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    /**
     * where条件封装
     */
    private List<SearchParam.WhereCondition> whereCondition;
    /**
     * order by条件封装
     */
    private List<SearchParam.OrderByCondition> orderByCondition;

    /**
     * 数据操作值封装
     */
    private List<SearchParam.Value> updateValueList;

    /**
     * 插入集合
     */
    private List<T> insertList;

    public Boolean isAnd=Boolean.TRUE;

    /**
     * 操作方式
     */
    private OperationType operationType;

    private Boolean master = Boolean.TRUE;

    public Boolean getMaster() {
        return master;
    }

    public void setMaster(Boolean master) {
        this.master = master;
    }

    /**
     * 执行SQL
     */
    private String executeSql;


    private Long sqlTime;

    private Object returnVal;

    private Integer page;
    private Integer pageSize;

    /**
     * 使否开启分页查询
     */
    private Boolean isOpenPage = false;

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

    public Boolean getOpenPage() {
        return isOpenPage;
    }

    public void setOpenPage(Boolean openPage) {
        isOpenPage = openPage;
    }

    public Long getSqlTime() {
        return sqlTime;
    }

    public void setSqlTime(Long sqlTime) {
        this.sqlTime = sqlTime;
    }

    public Object getReturnVal() {
        return returnVal;
    }

    public void setReturnVal(Object returnVal) {
        this.returnVal = returnVal;
    }

    /**
     * 参数列表
     */
    private Map<String,Object> paramMap;


    private Boolean isCloseDeleteProtect=Boolean.FALSE;

    public Boolean getCloseDeleteProtect(){
        return isCloseDeleteProtect;
    }

    public void setCloseDeleteProtect(Boolean action) {
        isCloseDeleteProtect = action;
    }

    public static enum OperationType{
        INSERT,SELECT,UPDATE,DELETE
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
    public static class Value{
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

    public TableMapper getTableMapper() {
        return tableMapper;
    }

    public void setTableMapper(TableMapper tableMapper) {
        this.tableMapper = tableMapper;
    }

    public List<WhereCondition> getWhereCondition() {
        return whereCondition;
    }

    public void setWhereCondition(List<WhereCondition> whereCondition) {
        this.whereCondition = whereCondition;
    }

    public List<OrderByCondition> getOrderByCondition() {
        return orderByCondition;
    }

    public void setOrderByCondition(List<OrderByCondition> orderByCondition) {
        this.orderByCondition = orderByCondition;
    }

    public List<Value> getUpdateValueList() {
        return updateValueList;
    }

    public void setUpdateValueList(List<Value> updateValueList) {
        this.updateValueList = updateValueList;
    }

    public List<T> getInsertList() {
        return insertList;
    }

    public void setInsertList(List<T> insertList) {
        this.insertList = insertList;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    public String getExecuteSql() {
        return executeSql;
    }

    public void setExecuteSql(String executeSql) {
        this.executeSql = executeSql;
    }
}
