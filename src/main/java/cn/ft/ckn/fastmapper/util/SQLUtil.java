package cn.ft.ckn.fastmapper.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.druid.sql.SQLUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Map;

@Slf4j
public class SQLUtil {

    static String getPrefix(){
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (StackTraceElement stackTraceElement : stackTrace) {
            String className = stackTraceElement.getClassName();
            String methodName = stackTraceElement.getMethodName();
            int lineNumber = stackTraceElement.getLineNumber();
            if(!stackTraceElement.getClassName().startsWith("cn.ft.ckn.fastmapper.")
            && !stackTraceElement.getClassName().startsWith("java.lang")
            ){
                return className + "." + methodName + "(" + lineNumber + ")     SQL执行↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓";
            }
        }
        return "";
    }

    public static void print(String execute,String result){
        log.info(getPrefix()
                + System.lineSeparator()+
                "---------------------------------------------------------"
                + System.lineSeparator()
                +execute
                +System.lineSeparator()
                + result
                +System.lineSeparator()+
                "---------------------------------------------------------");
    }

    public static String printSql(String sql, Map<String, Object> params) {
        if (params.size() > 0) {
            for (String param : params.keySet()) {
                Object o = params.get(param);
                sql = StrUtil.replace(sql, ":" + param, getValue(o));
            }
        }
        return  SQLUtils.formatMySql(sql);
    }

    static String getValue(Object value) {
        StringBuilder stringBuilder = new StringBuilder();
        if (value instanceof String || value instanceof Date) {
            stringBuilder.append("'");
        }
        if (value instanceof Date) {
            stringBuilder.append(DateUtil.format((Date) value, "yyyy-MM-dd"));
        } else {
            stringBuilder.append(value);
        }
        if (value instanceof String || value instanceof Date) {
            stringBuilder.append("'");
        }
        return stringBuilder.toString();
    }

    public static String printResult(Object val) {
        return System.lineSeparator()+"执行结果:[" + val+"]";
    }
}
