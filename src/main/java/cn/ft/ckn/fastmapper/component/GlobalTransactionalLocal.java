package cn.ft.ckn.fastmapper.component;

import java.lang.annotation.*;

/**
 * @author ckn
 * @date 2022/11/1
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface GlobalTransactionalLocal {

}
