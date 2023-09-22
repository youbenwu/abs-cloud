package com.outmao.ebs.common.services.juhe.util;




import com.outmao.ebs.common.util.JsonUtil;

import java.util.Map;

public class JuheUtil {


    public static String getParamStringFromMapJson(String params){
        Map map= JsonUtil.fromJson(params,Map.class);

        StringBuffer p=new StringBuffer();

        map.keySet().forEach(key->{
            p.append(String.format("#%s#=",key));
            p.append(map.get(key));
        });

        return p.toString();
    }

}
