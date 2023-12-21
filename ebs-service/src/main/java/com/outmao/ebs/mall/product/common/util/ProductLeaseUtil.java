package com.outmao.ebs.mall.product.common.util;

import com.outmao.ebs.common.vo.TimeSpan;
import com.outmao.ebs.mall.order.entity.OrderProductLease;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductLeaseUtil {

    static Pattern pattern = Pattern.compile("\\s*(\\d+)(\\D)");


    public static TimeSpan skuNameToLease(String skuName){
        TimeSpan lease=new TimeSpan();
        Matcher matcher=pattern.matcher(skuName);
        if(matcher.find()){
            String v=matcher.group(1);
            String f=matcher.group(2);
            System.out.println(v+" "+f);
            lease.setValue(Integer.parseInt(v));
            if(f.equals("年")){
                lease.setField(TimeSpan.YEAR);
            }else if(f.equals("月")){
                lease.setField(TimeSpan.MONTH);
            }else if(f.equals("日")){
                lease.setField(TimeSpan.DAY);
            }
        }
        return lease;
    }


    public static void main(String[] args) {
        skuNameToLease("红色 3年");
        skuNameToLease("3年 红色");
    }

}
