package com.outmao.ebs.common.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class ServletRequestUtil {

    public static Object getAttribute(String name){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        if(requestAttributes!=null){
            return requestAttributes.getAttribute(name,0);
        }
        return null;
    }

    public static void setAttribute(String name,Object value){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        if(requestAttributes!=null){
            requestAttributes.setAttribute(name,value,0);
        }
    }


}
