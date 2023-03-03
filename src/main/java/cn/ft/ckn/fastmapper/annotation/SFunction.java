package cn.ft.ckn.fastmapper.annotation;

import java.io.Serializable;
import java.util.function.Function;

/**
 * 使Function获取序列化能力
 */
@FunctionalInterface
public interface SFunction<T, R> extends Function<T, R>, Serializable {
}