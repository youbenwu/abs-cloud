package com.outmao.ebs.clients.shpanhe.vo;


import lombok.Data;


/**
 *
 *
 * "traffic": null,
 *     "ticketProducts": [
 *       {
 *         "productId": "8193",
 *         "productName": "上海野生动物园门票学生票",
 *         "marketPrice": "0.00",
 *         "settlePrice": "82.50"
 *       },
 *       {
 *         "productId": "8206",
 *         "productName": "上海野生动物园门票老人票(65周岁(含)以上)",
 *         "marketPrice": "0.00",
 *         "settlePrice": "82.50"
 *       }
 *     ]
 *
 *
 * **/
@Data
public class TicketProduct {

    private String productId;
    private String productName;
    private String marketPrice;
    private String settlePrice;


}
