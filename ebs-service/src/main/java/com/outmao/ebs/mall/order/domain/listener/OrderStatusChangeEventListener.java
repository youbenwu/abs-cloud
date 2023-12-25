package com.outmao.ebs.mall.order.domain.listener;

import com.google.common.eventbus.Subscribe;
import com.outmao.ebs.common.services.eventBus.ActionEventListener;
import com.outmao.ebs.mall.order.common.constant.OrderStatus;
import com.outmao.ebs.mall.order.common.event.OrderStatusChangeEvent;
import com.outmao.ebs.mall.order.dao.OrderDao;
import com.outmao.ebs.mall.order.entity.Order;
import com.outmao.ebs.mall.product.common.constant.ProductType;
import com.outmao.ebs.message.common.constant.MessageConstant;
import com.outmao.ebs.message.dto.SendMessageByTypeDTO;
import com.outmao.ebs.message.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import java.util.*;


public class OrderStatusChangeEventListener extends ActionEventListener<OrderStatusChangeEvent> {

    @Autowired
    private MessageService messageService;

    @Autowired
    private OrderDao orderDao;


    @Subscribe
    @Async
    @Override
    public void onEvent(OrderStatusChangeEvent event) {
        if(event.getOrderId()==null)
            return;
        Order order=orderDao.findById(event.getOrderId()).orElse(null);
        if(order==null)
            return;
        if(order.getStatus()!=event.getStatus())
            return;
        sendMessage(order);
    }


    ////订单消息--未支付订单提醒
    //    public static final String message_type_order_wait_pay="order-wait-pay";
    //
    //    //订单消息--支付成功提醒
    //    public static final String message_type_order_success="order-success";
    //
    //    //订单消息--订单发货提醒
    //    public static final String message_type_order_delivery="order-delivery";
    //
    //    //订单消息--订单完成提醒
    //    public static final String message_type_order_finish="order-finish";
    //
    //    //订单消息--设备租赁去托管提醒
    //    public static final String message_type_order_qy_wait_deploy="order-qy-wait-deploy";

    private void sendMessage(Order order){
        String type=null;
        if(order.getStatus()== OrderStatus.SUCCESSED.getStatus()){
           type=MessageConstant.message_type_order_success;
           if(order.getType()!=null){
               if(order.getType()==ProductType.HOTEL_ADVERT_CHANNEL.getType()){
                   type=MessageConstant.message_type_order_success_20;
               }else if(order.getType()==ProductType.HOTEL_DEVICE_LEASE.getType()){
                   type=MessageConstant.message_type_order_success_40;
               }else if(order.getType()==ProductType.HOTEL_WASH_SERVICE.getType()){
                   type=MessageConstant.message_type_order_success_30;
               }else if(order.getType()==ProductType.HOTEL_FOOD_SERVICE.getType()){
                   type=MessageConstant.message_type_order_success_31;
               }else if(order.getType()==ProductType.HOTEL_MALL.getType()){
                   type=MessageConstant.message_type_order_success_32;
               }
           }
        }else if(order.getStatus()== OrderStatus.DELIVERED.getStatus()){
            type=MessageConstant.message_type_order_delivery;
            if(order.getType()!=null){
                if(order.getType()==ProductType.HOTEL_DEVICE_LEASE.getType()){
                    type=MessageConstant.message_type_order_delivery_40;
                }else if(order.getType()==ProductType.HOTEL_WASH_SERVICE.getType()){
                    type=MessageConstant.message_type_order_delivery_30;
                }else if(order.getType()==ProductType.HOTEL_FOOD_SERVICE.getType()){
                    type=MessageConstant.message_type_order_delivery_31;
                }else if(order.getType()==ProductType.HOTEL_MALL.getType()){
                    type=MessageConstant.message_type_order_delivery_32;
                }
            }
        }else if(order.getStatus()== OrderStatus.FINISHED.getStatus()){
            type=MessageConstant.message_type_order_finish;
            if(order.getType()!=null){
                if(order.getType()==ProductType.HOTEL_ADVERT_CHANNEL.getType()){
                    type=MessageConstant.message_type_order_finish_20;
                }else if(order.getType()==ProductType.HOTEL_DEVICE_LEASE.getType()){
                    type=MessageConstant.message_type_order_finish_40;
                }else if(order.getType()==ProductType.HOTEL_WASH_SERVICE.getType()){
                    type=MessageConstant.message_type_order_finish_30;
                }else if(order.getType()==ProductType.HOTEL_FOOD_SERVICE.getType()){
                    type=MessageConstant.message_type_order_finish_31;
                }else if(order.getType()==ProductType.HOTEL_MALL.getType()){
                    type=MessageConstant.message_type_order_finish_32;
                }
            }
        }
        sendMessageUser(order,type);
    }

    private void sendMessageUser(Order order,String type){

        List<Long> tos= Arrays.asList(order.getUserId());

        Map<String,Object> data=new HashMap<>();
        data.put("orderNo",order.getOrderNo());
        data.put("status",order.getStatus());
        data.put("statusRemark",order.getStatusRemark());

        SendMessageByTypeDTO dto=new SendMessageByTypeDTO();
        dto.setType(type);
        dto.setTos(tos);
        dto.setData(data);

        messageService.sendMessageAsync(dto);

    }

}
