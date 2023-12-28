package com.outmao.ebs.mall.order.common.event;


import com.outmao.ebs.common.services.eventBus.ActionEvent;
import com.outmao.ebs.common.services.eventBus.MethodEvent;
import com.outmao.ebs.mall.order.entity.Order;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.HashMap;
import java.util.Map;


/**
*
* 交易事件
*
* */
@Data
public class OrderStatusChangeEvent extends ActionEvent {


    private Model model=new Model();

    @Override
    public void setValue(MethodEvent methodEvent) {
        Order order=(Order) methodEvent.getReturning();
        BeanUtils.copyProperties(order,model);
    }


    @Override
    public boolean async() {
        return super.async();
    }


    @Data
    public class Model{
        private Long id;
        private Long userId;
        private Long ownerId;
        private Long sellerId;
        private String orderNo;
        private int status;
        private String statusRemark;
        private Integer type;
    }


}
