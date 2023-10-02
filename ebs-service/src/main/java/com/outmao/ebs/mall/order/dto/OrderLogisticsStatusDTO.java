package com.outmao.ebs.mall.order.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "OrderLogisticsStatusDTO", description = "设置物流状态")
@Data
public class OrderLogisticsStatusDTO {

    /**
     * 自动编号
     */
    @ApiModelProperty(name = "id", value = "自动编号")
    private Long id;

    /**
     *
     *  物流信息ID
     *
     * */
    private Long logisticsId;

    /**
     *
     * 自定义物流状态
     *
     */
    @ApiModelProperty(name = "status", value = "自定义物流状态")
    private int status;

    /**
     *
     * 自定义物流状态
     *
     */
    @ApiModelProperty(name = "statusRemark", value = "自定义物流状态")
    private String statusRemark;


    /**
     *
     * 自定义物流状态
     *
     */
    @ApiModelProperty(name = "statusContent", value = "自定义物流状态")
    private String statusContent;



}
