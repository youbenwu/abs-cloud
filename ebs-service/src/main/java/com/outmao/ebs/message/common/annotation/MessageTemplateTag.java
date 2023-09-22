package com.outmao.ebs.message.common.annotation;


import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MessageTemplateTag {

    String name();
    String value();

}
