package cn.ft.ckn.fastmapper.expander;

import cn.ft.ckn.fastmapper.bean.SearchParam;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Singleton;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MapperExpanderRunner {

    private static List<Class<MapperExpander>> insertOccasion = new ArrayList<>();
    private static List<Class<MapperExpander>> deleteOccasion = new ArrayList<>();
    private static List<Class<MapperExpander>> updateOccasion = new ArrayList<>();
    private static List<Class<MapperExpander>> selectOccasion = new ArrayList<>();
    private static Boolean isExpander = Boolean.FALSE;

    private static final String INSERT = "insert";
    private static final String SELECT = "select";
    private static final String COUNT = "count";
    private static final String UPDATE = "update";
    private static final String DELETE = "delete";


    public static void addExpander(Class<MapperExpander> expanderClass) {
        MapperExpander expander = Singleton.get(expanderClass);
        if (expander == null || CollUtil.isEmpty(expander.occasion())) {
            return;
        }
        for (ExpanderOccasion occasion : expander.occasion()) {
            if (occasion.equals(ExpanderOccasion.INSERT)) {
                insertOccasion.add(expanderClass);
                continue;
            }
            if (occasion.equals(ExpanderOccasion.DELETE)) {
                deleteOccasion.add(expanderClass);
                continue;
            }
            if (occasion.equals(ExpanderOccasion.UPDATE)) {
                updateOccasion.add(expanderClass);
                continue;
            }
            if (occasion.equals(ExpanderOccasion.SELECT)) {
                selectOccasion.add(expanderClass);
            }
        }
        isExpander = Boolean.TRUE;
    }

    public static boolean runBeforeExpander(SearchParam param, String methodName, Method method) {
        if (!isExpander) {
            return true;
        }
        List<Class<MapperExpander>> expanders = getExpanders(methodName);
        if (CollUtil.isEmpty(expanders)) {
            return true;
        }

        for (Class<MapperExpander> expanderClass : expanders) {
            MapperExpander expander = Singleton.get(expanderClass);
            if (!expander.before(param,method)) {
                return false;
            }
        }
        return true;
    }

    public static void runAfterExpander(SearchParam param, String methodName, Method method) {
        if (!isExpander) {
            return;
        }
        List<Class<MapperExpander>> expanders = getExpanders(methodName);
        if (CollUtil.isEmpty(expanders)) {
            return;
        }

        for (Class<MapperExpander> expanderClass : expanders) {
            Singleton.get(expanderClass).after(param,method);
        }
    }

    public static void runAfterExceptionExpander(SearchParam param, String methodName, Method method) {
        if (!isExpander) {
            return;
        }
        List<Class<MapperExpander>> expanders = getExpanders(methodName);
        if (CollUtil.isEmpty(expanders)) {
            return;
        }

        for (Class<MapperExpander> expanderClass : expanders) {
            Singleton.get(expanderClass).afterException(param,method);
        }
    }



    private static List<Class<MapperExpander>> getExpanders(String methodName) {
        List<Class<MapperExpander>> expanders = null;
        if (methodName.equals(INSERT)) {
            expanders = insertOccasion;
        } else if (methodName.equals(DELETE)) {
            expanders = deleteOccasion;
        } else if (methodName.equals(UPDATE)) {
            expanders = updateOccasion;
        } else if (methodName.equals(SELECT) || methodName.equals(COUNT)) {
            expanders = selectOccasion;
        }
        return expanders;
    }


}
