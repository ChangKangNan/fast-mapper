package cn.ft.ckn.fastmapper.inter;

import java.lang.annotation.*;
import java.sql.Connection;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface GlobalTransactionalLocal {
    /**
     * 事务隔离级别限制
     * @return
     */
    int isolation() default Connection.TRANSACTION_REPEATABLE_READ;
}
