package cn.ft.ckn.fastmapper.inter;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface GlobalTransactionalLocal {
}
