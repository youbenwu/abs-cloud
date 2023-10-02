package com.outmao.ebs.mall.order.vo;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.outmao.ebs.common.vo.LogisticsInfo;
import com.outmao.ebs.common.vo.SimpleContact;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
import java.util.List;

@ApiModel(value = "OrderLogisticsVO", description = "订单物流信息")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
public class OrderLogisticsVO {

    /**
     * 自动编号
     */
    @ApiModelProperty(name = "id", value = "自动编号")
    private Long id;

    /**
     * 订单
     */
    @ApiModelProperty(name = "orderId", value = "订单编号")
    private Long orderId;


    /**
     *
     * 发货地址
     *
     */
    private SimpleContact from;


    /**
     *
     * 收货地址
     *
     */
    private SimpleContact to;

    /**
     * 订单物流状态记录
     */
    @ApiModelProperty(name = "statuses", value = "订单物流状态记录")
    private List<OrderLogisticsStatusVO> statuses;


    /**
     *
     * 物流信息
     *
     */
    @ApiModelProperty(name = "logisticsInfo", value = "物流信息")
    private LogisticsInfo logisticsInfo;


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
     * 自定义物流状态信息
     *
     */
    @ApiModelProperty(name = "statusContent", value = "自定义物流状态信息")
    private String statusContent;


    /**
     * 创建时间
     */
    @ApiModelProperty(name = "createTime", value = "创建时间")
    private Date createTime;

    /**
     *
     * 更新时间
     *
     */
    @ApiModelProperty(name = "updateTime", value = "更新时间")
    private Date updateTime;


}
