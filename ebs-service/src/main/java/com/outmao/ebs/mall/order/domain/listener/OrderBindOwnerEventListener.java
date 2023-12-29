package com.outmao.ebs.mall.order.domain.listener;

import com.google.common.eventbus.Subscribe;
import com.outmao.ebs.mall.order.common.event.OrderBindOwnerEvent;


public interface OrderBindOwnerEventListener {

    @Subscribe
    public void onEvent(OrderBindOwnerEvent event) ;


}
