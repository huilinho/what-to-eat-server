package net.scode.commons.annotations;

import java.lang.annotation.*;

/**
 * 系统日志记录的注解
 *
 * @author
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {

    String value() default "";

}
