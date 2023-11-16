package com.outmao.ebs.mall.order.dto;

import com.outmao.ebs.wallet.common.constant.PayChannel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class OrderPayPrepareDTO {

    @ApiModelProperty(name = "orderNo", value = "订单号")
    private String orderNo;

    /**
     * WalletPay--钱包支付
     * AliPay--支付宝支付
     * WxPay--微信支付
     * UnionPay--银联支付
     */
    @ApiModelProperty(name = "payChannel", value = "支付方式 WalletPay--钱包支付 AliPay--支付宝支付 WxPay--微信支付 UnionPay--银联支付")
    private String payChannel= PayChannel.WalletPay.name();



    /**
     *
     * WxPayApp(1, "微信APP支付"),
     *     WxPayH5(2, "微信H5支付"),
     *     WxPayJsapi(3, "微信JSAPI支付"),
     *     WxPayNativepay(4, "微信当面付"),
     *     AliPayAPP(11, "支付宝APP支付"),
     *     AliPayPrecreate(12, "支付宝当面付:扫码支付");
     *
     * **/

    @ApiModelProperty(name = "outPayType", value = "支付方式 " +
            "WxPayApp--微信APP支付 " +
            "WxPayH5--微信H5支付 " +
            "WxPayJsapi--微信JSAPI支付 " +
            "WxPayNativepay--微信当面付 " +
            "AliPayAPP--支付宝APP支付 " +
            "AliPayPrecreate--支付宝当面付:扫码支付")
    private String outPayType;

    @ApiModelProperty(name = "currency", value = "支付货币 RMB--人民币 COIN--积分")
    private String currency="RMB";

}
