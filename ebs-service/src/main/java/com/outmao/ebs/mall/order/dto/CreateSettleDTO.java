package com.outmao.ebs.mall.order.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;


@Data
public class CreateSettleDTO {

    private Long userId;

    private Long storeId;

    private Long lookId;

    /**
     * 商品类型
     */
    @ApiModelProperty(name = "type", value = "商品类型  " +
            "0--普通商品 " +
            "11--新楼盘 " +
            "12--二手房 " +
            "13--出租房 " +
            "1--虚拟商品 " +
            "20--广告频道 " +
            "30--酒店洗衣服务 " +
            "31--酒店送餐服务 " +
            "100--外部携程旅游商品")
    private Integer type;

    private List<CreateSettleProductDTO> products;


}
