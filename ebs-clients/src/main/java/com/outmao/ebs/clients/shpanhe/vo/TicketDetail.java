package com.outmao.ebs.clients.shpanhe.vo;


import lombok.Data;

import java.util.List;

/**
 *
 *  "productId": "9759",
 *     "productName": "东方明珠二球联票+旋转餐厅套餐成人票-晚餐17:00-19:00",
 *     "marketPrice": 0.00,
 *     "settlePrice": 406.00,
 *     "drawTicketAddress": "",
 *     "advanceDay": 0,
 *     "advanceTime": "14:00",
 *     "bookNotice": "[{\"key\":null,\"
 *     "refundChangeRule": "[{\"key\":null,\"name\":\"退改规则\",\"value\":\"使用日期前1天21
 *     "costDescription": "[{\"key\":null,\"name\":\"费用包含\",\"value\":\"东方明珠二球联票+旋转餐厅套餐成人票
 *     "useDescription": "[{\"key\":null,\"name\":\"使用方法\",\"value\":\"凭「有效证件+二维码」直接验证入园\"},{\"key\":null
 *     "otherDescription": "[{\"key\":null,\"name\":\"其他须知\",\"value\":\"如需购买儿童票请尽量与成人同时预订，座位安排以现场为准，不接受电话预约。<br/>1.入
 *     "minBuyCount": 1,
 *     "maxBuyCount": 20,
 *     "contactInfoType": 1,
 *     "touristInfoType": 4,
 *     "supportCardTypes": [
 *       1
 *     ],
 *     "priceCalendar": [
 *       {
 *         "settlePrice": 406.00,
 *         "date": "2021-12-19"
 *       },
 *       {
 *         "settlePrice": 406.00,
 *         "date": "2021-12-20"
 *       },
 *
 *     ]
 *
 * **/

@Data
public class TicketDetail {

    private String productId;
    private String productName;
    private String marketPrice;
    private String settlePrice;
    private String drawTicketAddress;
    private String advanceDay;
    private String advanceTime;

    private String bookNotice;
    private String refundChangeRule;
    private String costDescription;
    private String useDescription;
    private String otherDescription;

    private String minBuyCount;
    private String maxBuyCount;
    private String contactInfoType;
    private String touristInfoType;

    private int[] supportCardTypes;


    private List<PriceCalendar> priceCalendar;



}
