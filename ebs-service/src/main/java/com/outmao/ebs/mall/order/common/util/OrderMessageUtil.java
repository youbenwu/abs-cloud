package com.outmao.ebs.mall.order.common.util;


import com.outmao.ebs.mall.order.common.constant.OrderStatus;
import com.outmao.ebs.mall.product.common.constant.ProductType;
import com.outmao.ebs.message.common.constant.MessageConstant;

public class OrderMessageUtil {



    public static String getMessageType(Integer type,int status){
        String mtype=null;
        if(status== OrderStatus.SUCCESSED.getStatus()){
            mtype= MessageConstant.message_type_order_success;
            if(type!=null){
                if(type== ProductType.HOTEL_ADVERT_CHANNEL.getType()){
                    mtype=MessageConstant.message_type_order_success_20;
                }else if(type==ProductType.HOTEL_DEVICE_LEASE.getType()){
                    mtype=MessageConstant.message_type_order_success_40;
                }else if(type==ProductType.HOTEL_WASH_SERVICE.getType()){
                    mtype=MessageConstant.message_type_order_success_30;
                }else if(type==ProductType.HOTEL_FOOD_SERVICE.getType()){
                    mtype=MessageConstant.message_type_order_success_31;
                }else if(type==ProductType.HOTEL_MALL.getType()){
                    mtype=MessageConstant.message_type_order_success_32;
                }
            }
        }else if(status== OrderStatus.DELIVERED.getStatus()){
            mtype=MessageConstant.message_type_order_delivery;
            if(type!=null){
                if(type==ProductType.HOTEL_DEVICE_LEASE.getType()){
                    mtype=MessageConstant.message_type_order_delivery_40;
                }else if(type==ProductType.HOTEL_WASH_SERVICE.getType()){
                    mtype=MessageConstant.message_type_order_delivery_30;
                }else if(type==ProductType.HOTEL_FOOD_SERVICE.getType()){
                    mtype=MessageConstant.message_type_order_delivery_31;
                }else if(type==ProductType.HOTEL_MALL.getType()){
                    mtype=MessageConstant.message_type_order_delivery_32;
                }
            }
        }else if(status== OrderStatus.FINISHED.getStatus()){
            mtype=MessageConstant.message_type_order_finish;
            if(type!=null){
                if(type==ProductType.HOTEL_ADVERT_CHANNEL.getType()){
                    mtype=MessageConstant.message_type_order_finish_20;
                }else if(type==ProductType.HOTEL_DEVICE_LEASE.getType()){
                    mtype=MessageConstant.message_type_order_finish_40;
                }else if(type==ProductType.HOTEL_WASH_SERVICE.getType()){
                    mtype=MessageConstant.message_type_order_finish_30;
                }else if(type==ProductType.HOTEL_FOOD_SERVICE.getType()){
                    mtype=MessageConstant.message_type_order_finish_31;
                }else if(type==ProductType.HOTEL_MALL.getType()){
                    mtype=MessageConstant.message_type_order_finish_32;
                }
            }
        }
        return mtype;
    }




}
