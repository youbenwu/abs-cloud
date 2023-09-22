package com.outmao.ebs.org.common.annotation;

import java.lang.annotation.*;


/*
 * 功能权限
 *
 * */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AccessPermission {


	/**
	 * 排序，从0开始
	 * */
	int sort() default 0;
	/**
	 * 0--功能 1--菜单 2--页面
	 */
	int type() default 0;
	/*
	 * 地址
	 * */
	String url();
	/*
	 * 权限
	 * */
	String name();

	/*
	 * 权限标题
	 * */
	String title();

	/*
	 * 权限描述
	 * */
	String description() default "";

}
