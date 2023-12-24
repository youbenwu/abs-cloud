package com.outmao.ebs.mall.order.vo;


import lombok.Data;

@Data
public class QyPadToOrderVO {

    private String orderNo;

    private String qrCode;

    private String qrCodeUrl;

    private Object data;

    private String bindQrCodeUrl;

    private Long bindQrCodeId;

    private OrderVO order;



}
