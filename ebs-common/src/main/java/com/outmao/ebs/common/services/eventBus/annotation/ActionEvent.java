package com.outmao.ebs.common.services.eventBus.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ActionEvent {

    Class[] value();
    boolean async() default false;

}

