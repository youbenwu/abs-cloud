package com.outmao.ebs.mall.order.domain.listener.impl;

import com.outmao.ebs.common.services.eventBus.ActionEventListener;
import com.outmao.ebs.common.vo.BindingItem;
import com.outmao.ebs.mall.order.common.event.OrderBindOwnerEvent;
import com.outmao.ebs.mall.order.common.util.OrderMessageUtil;
import com.outmao.ebs.mall.order.domain.listener.OrderBindOwnerEventListener;
import com.outmao.ebs.message.dto.SendMessageByTypeDTO;
import com.outmao.ebs.message.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.List;

@Component("OrderBindOwnerEventListener")
public class OrderBindOwnerEventListenerImpl extends ActionEventListener<OrderBindOwnerEvent> implements OrderBindOwnerEventListener {


    @Autowired
    private MessageService messageService;


    @Async
    @Override
    public void onEvent(OrderBindOwnerEvent event) {
        if(event.getModel()==null)
            return;
        sendMessage(event.getModel());
    }


    private void sendMessage(OrderBindOwnerEvent.Model order){
        //给被绑定用户发订单状态消息
        String type= OrderMessageUtil.getMessageType(order.getType(),order.getStatus());
        if(type!=null) {
            sendMessageUser(order, type);
        }
    }

    private void sendMessageUser(OrderBindOwnerEvent.Model order,String type){

        List<Long> tos= Arrays.asList(order.getOwnerId());

        SendMessageByTypeDTO dto=new SendMessageByTypeDTO();
        dto.setType(type);
        dto.setFromId(order.getSellerId());
        dto.setTos(tos);
        dto.setData(order);
        BindingItem item=new BindingItem();
        item.setId(order.getId());
        item.setType("Order");
        item.setSubType(order.getType()!=null?order.getType().toString():null);
        dto.setItem(item);

        messageService.sendMessageAsync(dto);

    }


}
