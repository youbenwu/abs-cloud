package com.outmao.ebs.wallet.common.annotation;


import java.lang.annotation.*;


@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BindingWallet {

    int type() default 0;

}

