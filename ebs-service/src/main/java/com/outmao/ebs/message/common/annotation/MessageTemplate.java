package com.outmao.ebs.message.common.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MessageTemplate {

    int sendType();
    String name();
    String title();
    String content();
    String url() default "";

}
