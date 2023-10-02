package com.outmao.ebs.mall.order.vo;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.outmao.ebs.mall.order.entity.OrderLogisticsStatusItem;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 *
 * 订单物流状态记录
 *
 * */
@ApiModel(value = "OrderLogisticsStatusVO", description = "订单物流状态记录")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
public class OrderLogisticsStatusVO  {

    /**
     * 自动编号
     */
    @ApiModelProperty(name = "id", value = "自动编号")
    private Long id;

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
     * 创建时间
     */
    @ApiModelProperty(name = "createTime", value = "创建时间")
    private Date createTime;

    @ApiModelProperty(name = "items", value = "订单跟踪")
    private List<OrderLogisticsStatusItem> items;

}
