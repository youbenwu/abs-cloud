package com.outmao.ebs.mall.order.common.event;


import com.outmao.ebs.common.services.eventBus.ActionEvent;
import com.outmao.ebs.common.services.eventBus.MethodEvent;
import com.outmao.ebs.mall.order.entity.Order;
import lombok.Data;


/**
*
* 交易事件
*
* */
@Data
public class OrderStatusChangeEvent extends ActionEvent {

    private Long orderId;
    private int status;


    @Override
    public void setValue(MethodEvent methodEvent) {
        Order order=(Order) methodEvent.getReturning();
        if(order!=null){
            orderId=order.getId();
            status=order.getStatus();
        }
    }


    @Override
    public boolean async() {
        return super.async();
    }



}
