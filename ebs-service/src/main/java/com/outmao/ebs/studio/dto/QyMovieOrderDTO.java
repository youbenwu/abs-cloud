package com.outmao.ebs.studio.dto;

import com.outmao.ebs.wallet.common.constant.OutPayType;
import com.outmao.ebs.wallet.common.constant.PayChannel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 迁眼影视下单
 */
@Data
public class QyMovieOrderDTO {

    /**
     *
     * 二维码ID
     *
     */
    @ApiModelProperty(name = "qrCodeId", value = "二维码ID")
    private Long qrCodeId;

    /**
     *
     * 用户ID
     *
     */
    @ApiModelProperty(name = "userId", value = "用户ID")
    private Long userId;


    /**
     *
     * 影视集ID
     *
     */
    @ApiModelProperty(name = "episodeId", value = "影视集ID")
    private Long episodeId;


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



}
