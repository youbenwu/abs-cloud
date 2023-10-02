package com.outmao.ebs.clients.shpanhe.vo;


import lombok.Data;

import java.util.List;

/**
 *
 *
 *     "scenicId": "12361",
 *     "scenicName": "上海野生动物园",
 *     "cityName": "上海",
 *     "provinceName": "上海",
 *     "scenicAddress": "上海市浦东新区南六公路178号",
 *     "longitude": 121.721460,
 *     "latitude": 31.055591,
 *     "images": [
 *       "https://img.shpanhe.com/scenic/100j1h000001hkwyw2759.jpg",
 *       "https://img.shpanhe.com/scenic/0103q1200095f3bih2677.jpg",
 *       "https://img.shpanhe.com/scenic/010191200095f356a4813.jpg",
 *       "https://img.shpanhe.com/scenic/0104m1200095f3bii20A0.jpg",
 *       "https://img.shpanhe.com/scenic/0102y1200095f39eh5CBF.jpg"
 *     ],
 *     "star": 5,
 *     "commentScore": 4.7,
 *     "openTime": "3/1-6/30 09:00-17:00;7/1-8/31 09:00-20:00;9/1-11/30 09:00-17:00;12/1-2/28 09:00-16:30;   说明：车入区运行时间：\n3月-6月、9月-11月  9:15-16:30；\n7月-8月  9:15-18:00 ；\n12月-翌年2月 9:15-15:30。\n \n水域游船运行时间：\n3月-6月、9月-11月 9:15-16:30；\n7月-8月  9:15-19:30；\n12月-翌年2月  9:15-15:30。",
 *     "themeGroups": "动物园",
 *     "scenicDescription": "<p></p><p class=\"MsoNormal\">本周热点一：大老虎扑过来啦！<span lang=\"EN-US\" style=\"\"><o:p></o:p></span></p><p class=\"MsoNormal\">百兽之王是它的大名，勇猛
 *     "recommend": "与世界各地动物亲密接触",
 *     "bookNotice": "[{\"key\":\"81\",\"name\":\"特别提示\",\"value\":\"<p class=\\\"MsoNormal\\\">各场馆展示时间：<span lang=\\\"EN-US\\\" style=\\\"\\\"><o:p></o:p></span></p><p class=\\\"MsoNormal\\\">一、国际大马戏（收费）
 *
 *     "traffic": null,
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
public class Scenic {


    private String scenicId;
    private String scenicName;
    private String mainImage;
    private String cityName;
    private String provinceName;
    private String scenicAddress;
    private double longitude;
    private double latitude;
    private int star;
    private double commentScore;
    private String openTime;
    private String themeGroups;

    private String distance;






}
