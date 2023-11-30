package com.outmao.ebs.mall.order.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GetStatsOrderStatusListDTO {

    @ApiModelProperty(name = "shopId", value = "店铺ID")
    private Long shopId;

    @ApiModelProperty(name = "userId", value = "下单用户ID")
    private Long userId;

    @ApiModelProperty(name = "hotelId", value = "酒店ID")
    private Long hotelId;

    @ApiModelProperty(name = "brokerId", value = "经纪人ID")
    private Long brokerId;

    @ApiModelProperty(name = "partnerId", value = "合伙人ID")
    private Long partnerId;

    @ApiModelProperty(name = "keyword", value = "关键字")
    private String keyword;

    @ApiModelProperty(name = "type", value = "类型\n" +
            "\t * 20 广告投放\n" +
            "\t * 30 酒店干洗服务\n" +
            "\t * 31 酒店送餐服务\n" +
            "\t * 32 酒店商超\n" +
            "\t * 40 酒店投放设备")
    private Integer type;

}
