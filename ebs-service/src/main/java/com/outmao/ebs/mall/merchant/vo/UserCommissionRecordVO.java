package com.outmao.ebs.mall.merchant.vo;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;

@ApiModel(value = "UserCommissionRecordVO", description = "佣金记录")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
public class UserCommissionRecordVO {

    /**
     * 自动编号
     */
    @ApiModelProperty(name = "id", value = "自动编号")
    private Long id;

    /**
     * 用户
     */
    @ApiModelProperty(name = "userId", value = "用户ID")
    private Long userId;

    /**
     *
     */
    @ApiModelProperty(name = "commissionId", value = "佣金ID")
    private Long commissionId;

    /**
     *
     * 关联的订单ID
     *
     */
    @ApiModelProperty(name = "orderId", value = "关联的订单ID")
    private Long orderId;


    /**
     *
     * 0--直接收益 1--子级产生的收益
     *
     */
    @ApiModelProperty(name = "level", value = "0--直接收益 1--子级产生的收益")
    private int level;


    /**
     *
     * 佣金
     *
     */
    @ApiModelProperty(name = "amount", value = "佣金")
    private double amount;

    /**
     *
     * 类型 0--订单收益 1--提现 2--提现取消
     *
     */
    @ApiModelProperty(name = "type", value = "类型 0--订单收益 1--提现 2--提现取消")
    private int type;

    /**
     *
     * 显示图片
     *
     */
    @ApiModelProperty(name = "image", value = "显示图片")
    private String image;


    /**
     *
     * 显示备注
     *
     */
    @ApiModelProperty(name = "remark", value = "显示备注")
    private String remark;

    /**
     *
     * 创建时间
     *
     */
    @ApiModelProperty(name = "createTime", value = "创建时间")
    private Date createTime;

}
