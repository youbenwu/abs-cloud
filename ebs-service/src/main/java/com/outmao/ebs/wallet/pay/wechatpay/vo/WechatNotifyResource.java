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
public class WechatNotifyResource {

    private String original_type;
    private String algorithm;
    private String ciphertext;
    private String associated_data;
    private String nonce;


}
