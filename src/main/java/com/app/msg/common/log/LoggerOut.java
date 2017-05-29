package com.app.msg.common.log;

import java.lang.annotation.*;

/**
 * 功能描述：日志输出Annotation
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface LoggerOut {
    /**
     * 是否关闭日志输出功能
     *
     * @return
     */
    boolean enable() default true;

    /**
     * 是否输出执行时间
     *
     * @return
     */
    boolean duration() default true;

    /**
     * 是否只在debug输出日志
     */
    boolean debug() default false;

    boolean onlyReq() default false;

    /**
     * 日志输出格式
     *
     * @return
     */
    LoggerFormat format() default LoggerFormat.JSON;
}
