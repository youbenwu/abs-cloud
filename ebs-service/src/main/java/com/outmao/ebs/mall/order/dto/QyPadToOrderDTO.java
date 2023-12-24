package com.outmao.ebs.mall.order.dto;

import com.outmao.ebs.wallet.common.constant.OutPayType;
import com.outmao.ebs.wallet.common.constant.PayChannel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class QyPadToOrderDTO {

    private Long userId;

    private Long skuId;

    @ApiModelProperty(name = "quantity", value = "商品总数量")
    private int quantity;


    @ApiModelProperty(name = "payChannel", value = "支付方式 WalletPay--钱包支付 AliPay--支付宝支付 WxPay--微信支付 UnionPay--银联支付")
    private String payChannel= PayChannel.AliPay.name();


    @ApiModelProperty(name = "outPayType", value = "支付方式 " +
            "WxPayApp--微信APP支付 " +
            "WxPayH5--微信H5支付 " +
            "WxPayJsapi--微信JSAPI支付 " +
            "WxPayNativepay--微信当面付 " +
            "AliPayAPP--支付宝APP支付 " +
            "AliPayPrecreate--支付宝当面付:扫码支付")
    private String outPayType= OutPayType.AliPayPrecreate.name();


    private String remark;


    private String userPhone;

    private String userName;


}
