package cn.ft.ckn.fastmapper.anno;

import java.lang.annotation.*;
import java.sql.Connection;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface LocalTransactional {
    /**
     * 事务隔离级别限制
     */
    int isolation() default Connection.TRANSACTION_REPEATABLE_READ;
}
