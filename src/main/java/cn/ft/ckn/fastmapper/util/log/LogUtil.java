package cn.ft.ckn.fastmapper.util.log;

import cn.ft.ckn.fastmapper.bean.FastMapperParam;
import cn.ft.ckn.fastmapper.bean.FastTableMapper;
import cn.hutool.core.util.BooleanUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;

import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogUtil {

    /**
     * 获取调用日志工具的方法对应的 "类.方法(行号)" 标识前缀，用于溯源。
     */
    static String getPrefix() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (StackTraceElement stackTraceElement : stackTrace) {
            String className = stackTraceElement.getClassName();
            String methodName = stackTraceElement.getMethodName();
            int lineNumber = stackTraceElement.getLineNumber();
            if (!stackTraceElement.getClassName().startsWith("cn.ft.ckn.fastmapper.")
                    && !stackTraceElement.getClassName().startsWith("java.lang")
                    && !stackTraceElement.getClassName().startsWith("cn.hutool.aop.interceptor.")
            ) {
                return className + "." + methodName + "(" + lineNumber + ")";
            }
        }
        return "";
    }

    /**
     * 打印 SQL 执行日志
     *
     * @param execute SQL 执行语句（含参数替换前）
     * @param result  SQL 执行结果字符串
     */
    public static void print(String execute, String result) {
        FastTableMapper<Object> tableMapper = FastMapperParam.get().getTableMapper();
        cn.hutool.log.Log log;
        if (tableMapper == null || tableMapper.getTableName() == null) {
            log = cn.hutool.log.LogFactory.get("");
        } else {
            log = cn.hutool.log.LogFactory.get(FastMapperParam.get().getTableMapper().getTableName());
        }
        log.info(
                        getPrefix()
                        + " SQL 执行 ↓ "
                        + System.lineSeparator()
                        + execute
                        + System.lineSeparator()
                        + result
                        + System.lineSeparator()
                        + "执行时间: " + FastMapperParam.get().getSqlTime() + "ms"
        );
    }

    /**
     * 格式化 SQL 语句（并用参数替换 :param 占位符）
     *
     * @param sql    SQL字符串
     * @param params 参数map
     * @return 格式化后的 SQL
     */
    public static String printSql(String sql, Map<String, Object> params) {
        if (params != null && !params.isEmpty()) {
            // 使用正则精确替换 :param
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                // 替换带:的参数（避免部分覆盖）
                String regex = ":" + Pattern.quote(entry.getKey()) + "(\\b|\\W)";
                String value = getValue(entry.getValue());
                sql = sql.replaceAll(regex, Matcher.quoteReplacement(value) + "$1");
            }
        }
        return sql;
    }


    /**
     * 返回值字符串化，字符串和日期类型自动加单引号，布尔类型输出 true/false
     */
    public static String getValue(Object value) {
        if (value == null) {
            return "null";
        }
        if (value instanceof String) {
            return "'" + value + "'";
        }
        if (value instanceof java.time.LocalDate) {
            return "'" + value + "'"; // LocalDate默认toString就是yyyy-MM-dd
        }
        if (value instanceof java.time.LocalDateTime) {
            return "'" + value.toString().replace('T', ' ') + "'"; // yyyy-MM-dd HH:mm:ss
        }
        if (value instanceof Date) {
            // 判断时分秒是否全为0
            Date date = (Date) value;
            java.util.Calendar cal = java.util.Calendar.getInstance();
            cal.setTime(date);

            int hour = cal.get(java.util.Calendar.HOUR_OF_DAY);
            int minute = cal.get(java.util.Calendar.MINUTE);
            int second = cal.get(java.util.Calendar.SECOND);
            int millisecond = cal.get(java.util.Calendar.MILLISECOND);

            if (hour == 0 && minute == 0 && second == 0 && millisecond == 0) {
                return "'" + cn.hutool.core.date.DateUtil.format(date, "yyyy-MM-dd") + "'";
            } else {
                return "'" + cn.hutool.core.date.DateUtil.format(date, "yyyy-MM-dd HH:mm:ss") + "'";
            }
        }
        if (BooleanUtil.isBoolean(value.getClass())) {
            return Boolean.TRUE.equals(value) ? "true" : "false";
        }
        return value.toString();
    }

    /**
     * 生成执行结果字符串（供日志输出）
     */
    public static String printResult(Object val) {
        if(val == null){
            return "执行结果: 0";
        }
        if(val instanceof Collection && ((Collection<?>) val).isEmpty()){
            return "执行结果: []";
        }
        return "执行结果: " + (val instanceof Integer ? val : JSONUtil.toJsonStr(val));
    }
}
