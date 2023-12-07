package com.outmao.ebs.mall.order.common.util;

import com.outmao.ebs.common.vo.TimeSpan;
import com.outmao.ebs.mall.order.entity.OrderProductLease;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderProductLeaseUtil {

    static Pattern pattern = Pattern.compile("\\s*(\\d+)(\\D)");




    public static OrderProductLease skuNameToLease(String skuName){
        OrderProductLease lease=new OrderProductLease();
        Matcher matcher=pattern.matcher(skuName);
        if(matcher.find()){
            lease.setLease(true);
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
