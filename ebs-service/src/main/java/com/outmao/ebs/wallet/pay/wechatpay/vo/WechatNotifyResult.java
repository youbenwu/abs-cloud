package com.outmao.ebs.wallet.pay.wechatpay.vo;


import lombok.Data;

/**
 *
 * {
 *     "id":"EV-2018022511223320873",
 *     "create_time":"2018-06-08T10:34:56+08:00",
 *     "resource_type":"encrypt-resource",
 *     "event_type":"REFUND.SUCCESS",
 *     "summary":"退款成功",
 *     "resource" : {
 *         "original_type": "refund",
 *         "algorithm":"AEAD_AES_256_GCM",
 *         "ciphertext": "...",
 *         "associated_data": "",
 *         "nonce": "..."
 *     }
 * }
 *
 * */


@Data
public class WechatNotifyResult {

    private String id;
    private String create_time;
    private String resource_type;
    private String event_type;
    private String summary;
    private WechatNotifyResource resource;

}
