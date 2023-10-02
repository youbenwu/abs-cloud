package com.outmao.ebs.mall.order.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "OrderContractDTO", description = "订单合同")
@Data
public class OrderContractVO {

    /**
     * 自动编号
     */
    private Long id;

    /**
     * 订单编号
     */
    private Long orderId;

    @ApiModelProperty(name = "name", value = "合同名称")
    private String name;

    @ApiModelProperty(name = "url", value = "合同地址")
    private String url;


}
