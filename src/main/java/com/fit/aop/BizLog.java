package com.fit.aop;

import java.lang.annotation.*;

/**
 * 业务日志注解
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BizLog {

    /**
     * 日志类型
     */
    String logType() default "OPERATION";

    /**
     * 日志名称
     */
    String logName() default "";

    /**
     * 业务描述（支持SpEL表达式）
     */
    String value() default "";

    /**
     * 是否记录参数
     */
    boolean recordParams() default true;

    /**
     * 是否记录返回值
     */
    boolean recordResult() default false;
}
