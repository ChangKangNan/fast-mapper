package cn.ft.ckn.fastmapper.config;

import cn.ft.ckn.fastmapper.expander.MapperExpander;
import cn.ft.ckn.fastmapper.expander.MapperExpanderRunner;
import io.netty.util.concurrent.FastThreadLocal;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 查询工具配置类
 * @author ckn
 */
public class FastMapperConfig {
    /**
     * 仅数据源需要频繁切换salve时使用
     */
    public static final Map<String, NamedParameterJdbcTemplate> dataSourceSalveTemplateMap = new HashMap<>();
    /**
     * 仅数据源需要频繁切换salve时使用
     */
    public static final FastThreadLocal<NamedParameterJdbcTemplate> dataSourceMasterTemplate = new FastThreadLocal<>();

    /**
     * 单一项目不涉及数据源切换时使用
     */
    public static final FastThreadLocal<DataSource> dataSourceMaster = new FastThreadLocal<>();

    /**
     * 是否打印SQL
     */
    public static Boolean isOpenSQLPrint = Boolean.FALSE;

    /**
     * 是否自动填充创建日期字段(默认系统当前时间)
     */
    public static Boolean isOpenCreateTimeAuto = Boolean.FALSE;

    /**
     * 是否自动填充创建日期字段(默认系统当前时间)
     */
    public static Boolean isOpenUpdateTimeAuto = Boolean.FALSE;
    /**
     * 是否自动填充删除字段(默认为0)
     */
    public static Boolean isOpenLogicDeletedAuto = Boolean.FALSE;
    /**
     * 定义逻辑删除字段以及默认值
     */
    public static String logicDeletedColumn;
    public static Object logicDeletedColumnDefaultValue;
    /**
     * 删除状态
     */
    public static Object logicDeletedColumnDeletedValue;

    public static String createTime;
    public static String updateTime;

    public static void setDeleted(Boolean isOpenLogicDeletedAuto, String logicDeletedColumn, Object logicDeletedColumnDefaultValue, Object deletedValue) {
        FastMapperConfig.isOpenLogicDeletedAuto = isOpenLogicDeletedAuto;
        FastMapperConfig.logicDeletedColumn = logicDeletedColumn;
        FastMapperConfig.logicDeletedColumnDefaultValue = logicDeletedColumnDefaultValue;
        FastMapperConfig.logicDeletedColumnDeletedValue = deletedValue;
    }

    public static void setTimeAuto(Boolean isOpenCreateTimeAuto, Boolean isOpenUpdateTimeAuto) {
        FastMapperConfig.isOpenCreateTimeAuto = isOpenCreateTimeAuto;
        FastMapperConfig.isOpenUpdateTimeAuto = isOpenUpdateTimeAuto;
    }
    public static void setTimeColumn(String createTimeColumn, String updateTimeColumn) {
        createTime=createTimeColumn;
        updateTime=updateTimeColumn;
    }

    public static void addMapperExpander(Class<? extends MapperExpander> expanderClass) {
        if (expanderClass != null) {
            if (expanderClass.isInterface()) {
                return;
            }
            MapperExpanderRunner.addFastDaoExpander((Class<MapperExpander>) expanderClass);
        }
    }
}
