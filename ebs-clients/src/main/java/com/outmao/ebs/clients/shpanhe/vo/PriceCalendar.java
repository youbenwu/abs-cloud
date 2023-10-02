package com.outmao.ebs.clients.shpanhe.vo;


import lombok.Data;


/**
 *
 * {
 *  *         "settlePrice": 406.00,
 *  *         "date": "2021-12-19"
 *  *       },
 *
 * **/

@Data
public class PriceCalendar {

    private String settlePrice;

    private String date;

}
