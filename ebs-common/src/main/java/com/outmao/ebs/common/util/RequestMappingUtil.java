package com.outmao.ebs.common.util;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;

public class RequestMappingUtil {
	
	public static String getPath(HandlerMethod handler) {
		String path="";
		RequestMapping req=handler.getBeanType().getAnnotation(RequestMapping.class);
		if(req!=null) {
			path+=req.value()[0];
		}else {
			GetMapping get=handler.getBeanType().getAnnotation(GetMapping.class);
			if(get!=null) {
				path+=get.value()[0];
			}else {
				PostMapping post=handler.getBeanType().getAnnotation(PostMapping.class);
				if(post!=null) {
					path+=post.value()[0];
				}
			}
		}
		
		
		req=handler.getMethod().getAnnotation(RequestMapping.class);
		if(req!=null) {
			path+=req.value()[0];
		}else {
			GetMapping get=handler.getMethod().getAnnotation(GetMapping.class);
			if(get!=null) {
				path+=get.value()[0];
			}else {
				PostMapping post=handler.getMethod().getAnnotation(PostMapping.class);
				if(post!=null) {
					path+=post.value()[0];
				}
			}
		}
		if(path.length()>0)
		return path;
		return null;
	}
	
	

}
