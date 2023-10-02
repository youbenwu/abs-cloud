package com.outmao.ebs.clients.shpanhe.vo;


import lombok.Data;


/**
 *
 * "name": "张杰",
 *     "mobile": "13917583621"v
 *     "cardType": 1,
 *       "cardNo": "1232434245"
 *
 * */
@Data
public class TravellerInfo {
    private String name;
    private String mobile;
    private int cardType;
    private String cardNo;
}
