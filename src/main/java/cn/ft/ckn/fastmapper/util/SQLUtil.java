package cn.ft.ckn.fastmapper.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.druid.sql.SQLUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Map;

@Slf4j
public class SQLUtil {
    public static void print(String sql, Map<String, Object> params, String type) {
        int deep = 0;
        if (StrUtil.equals(type, "INSERT")) {
            deep = 3;
        }
        if (StrUtil.equals(type, "SELECT")) {
            deep = 3;
        }
        if (StrUtil.equals(type, "UPDATE")) {
            deep = 4;
        }
        if (StrUtil.equals(type, "DELETE")) {
            deep = 3;
        }
        if (StrUtil.equals(type, "CUSTOMER")) {
            deep = 3;
        }
        if (params.size() > 0) {
            for (String param : params.keySet()) {
                Object o = params.get(param);
                sql = StrUtil.replace(sql, ":" + param, getValue(o));
            }
        }
        String className = Thread.currentThread().getStackTrace()[deep].getClassName();
        String methodName = Thread.currentThread().getStackTrace()[deep].getMethodName();
        int lineNumber = Thread.currentThread().getStackTrace()[deep].getLineNumber();
        log.info("---------------------------------------------------------");
        log.info(className + "." + methodName + "(" + lineNumber + ")     SQL执行↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓"
                + System.lineSeparator() + SQLUtils.formatMySql(sql));
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

    public static void printResult(Object val) {
        log.info(System.lineSeparator()+"执行结果:[" + val+"]");
        log.info("---------------------------------------------------------");
    }
}
