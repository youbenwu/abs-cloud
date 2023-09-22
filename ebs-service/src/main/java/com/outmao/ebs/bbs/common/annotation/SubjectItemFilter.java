package com.outmao.ebs.bbs.common.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SubjectItemFilter {

    //是否统计个人收藏数据
    boolean fav() default true;

    //是否统计个人评价数据
    boolean vote() default true;

    //是否返回统计总数
    boolean stats() default true;

}

