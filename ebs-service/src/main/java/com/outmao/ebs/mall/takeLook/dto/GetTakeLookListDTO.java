package com.outmao.ebs.mall.takeLook.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class GetTakeLookListDTO {


    @ApiModelProperty(name = "productType", value = "商品类型 0普通商品 11新楼盘 12二手房 13出租房")
    private Integer productType;

    @ApiModelProperty(name = "status", value = "状态 0--未确认 1--待带看 2--带看中 3--带看完成 4--取消关闭")
    private Integer status;

    @ApiModelProperty(name = "userId", value = "用户ID")
    private Long userId;


    @ApiModelProperty(name = "brokerId", value = "经纪人ID")
    private Long brokerId;


}
