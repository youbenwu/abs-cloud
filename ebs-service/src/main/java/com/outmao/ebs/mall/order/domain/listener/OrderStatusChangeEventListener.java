package com.outmao.ebs.mall.order.domain.listener;

import com.google.common.eventbus.Subscribe;
import com.outmao.ebs.mall.order.common.event.OrderStatusChangeEvent;


public interface OrderStatusChangeEventListener  {

    @Subscribe
    public void onEvent(OrderStatusChangeEvent event) ;


}
