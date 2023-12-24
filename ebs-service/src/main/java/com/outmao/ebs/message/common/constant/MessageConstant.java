package com.outmao.ebs.message.common.constant;

public class MessageConstant {


    //消息类型--钱包转出
    public static final String message_type_transfer_out="wallet-transfer-out";

    //消息类型--钱包转入
    public static final String message_type_transfer_in="wallet-transfer-in";


    //订单消息--未支付订单提醒
    public static final String message_type_order_wait_pay="order-wait-pay";

    //订单消息--支付成功提醒
    public static final String message_type_order_success="order-success";

    //订单消息--订单发货提醒
    public static final String message_type_order_delivery="order-delivery";

    //订单消息--订单完成提醒
    public static final String message_type_order_finish="order-finish";

    //订单消息--设备租赁去托管提醒
    public static final String message_type_order_qy_wait_deploy="order-qy-wait-deploy";


}
