package com.outmao.ebs.mall.order.domain.listener.impl;

import com.outmao.ebs.common.services.eventBus.ActionEventListener;
import com.outmao.ebs.common.vo.BindingItem;
import com.outmao.ebs.mall.order.common.event.OrderStatusChangeEvent;
import com.outmao.ebs.mall.order.common.util.OrderMessageUtil;
import com.outmao.ebs.mall.order.domain.listener.OrderStatusChangeEventListener;
import com.outmao.ebs.message.dto.SendMessageByTypeDTO;
import com.outmao.ebs.message.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Component("OrderStatusChangeEventListener")
public class OrderStatusChangeEventListenerImpl extends ActionEventListener<OrderStatusChangeEvent> implements OrderStatusChangeEventListener {

    @Autowired
    private MessageService messageService;


    @Async
    @Override
    public void onEvent(OrderStatusChangeEvent event) {
        if(event.getModel()==null)
            return;
        sendMessage(event.getModel());
    }


    private void sendMessage(OrderStatusChangeEvent.Model order){
        String type= OrderMessageUtil.getMessageType(order.getType(),order.getStatus());
        if(type!=null) {
            sendMessageUser(order, type);
        }

        String sellerType=OrderMessageUtil.getMessageTypeBySeller(order.getType(),order.getStatus());


        if(sellerType!=null){
            sendMessageSeller(order,sellerType);
        }

    }

    private void sendMessageUser(OrderStatusChangeEvent.Model order,String type){

        List<Long> tos= Arrays.asList(order.getUserId(),order.getOwnerId()).stream().filter(t->t!=null).collect(Collectors.toList());

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

    private void sendMessageSeller(OrderStatusChangeEvent.Model order,String type){

        List<Long> tos= Arrays.asList(order.getSellerId());

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
