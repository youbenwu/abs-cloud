package com.outmao.ebs.clients.shpanhe.vo;

import lombok.Data;

@Data
public class AutoPayRequest {

    private String orderNo;//	string	是	门票订单号
    private float payAmount;//	float	否	订单支付金额（可为null ,传值时将和订单金额进行比对 ; 传值时就参与签名，为null时不参与签名）
    private String sign;//	string	是	签名校验值，md5(secretKey+orderNo+payAmount+secretKey)，secretKey为请求密钥，开通账户时即可获得，加密结果转为32位小写。【注意：加密前的拼接原串不包含'+'】

}
