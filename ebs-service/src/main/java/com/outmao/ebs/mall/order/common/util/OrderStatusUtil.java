package com.outmao.ebs.mall.order.common.util;

import com.outmao.ebs.mall.order.common.constant.OrderStatus;
import com.outmao.ebs.mall.order.common.constant.OrderSubStatus;

public class OrderStatusUtil {



    public static OrderSubStatus getSubStatus(int status){
        OrderSubStatus[] statuses=OrderSubStatus.values();
        for (OrderSubStatus s:statuses){
            if(s.getStatus()==status)
                return s;
        }
        return null;
    }

    public static boolean isSub(OrderStatus status,OrderSubStatus subStatus){
        if(subStatus.getStatus()>=status.getStatus()&&(subStatus.getStatus()<status.getStatus()+10)){
            return true;
        }
        return false;
    }




}
