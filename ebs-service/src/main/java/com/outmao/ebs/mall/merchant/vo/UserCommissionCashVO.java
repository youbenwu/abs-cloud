package com.outmao.ebs.mall.merchant.vo;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.outmao.ebs.user.common.data.SimpleUserSetter;
import com.outmao.ebs.user.vo.SimpleUserVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@ApiModel(value = "UserCommissionCashVO", description = "佣金提现")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
public class UserCommissionCashVO implements SimpleUserSetter {


    /**
     * 自动编号
     */
    @ApiModelProperty(name = "id", value = "自动编号")
    private Long id;

    /**
     * 用户
     */
    @ApiModelProperty(name = "commissionId", value = "用户ID")
    private Long userId;

    private SimpleUserVO user;

    @ApiModelProperty(name = "commissionId", value = "佣金ID")
    private Long commissionId;

    /**
     *
     *
     * 状态 0--未处理 1--处理中 2--已退款 3--不通过
     *
     */
    @ApiModelProperty(name = "status", value = "状态 0--未处理 1--处理中 2--已完成 3--审核不通过")
    private int status;


    /**
     *
     *
     * 状态备注
     *
     */
    @ApiModelProperty(name = "statusRemark", value = "状态备注")
    private String statusRemark;


    /**
     *
     *
     * 提现佣金
     *
     */
    @ApiModelProperty(name = "amount", value = "提现佣金")
    private double amount;

    /**
     *
     *
     * 备注
     *
     */
    @ApiModelProperty(name = "remark", value = "备注")
    private String remark;

    /**
     *
     * 创建时间
     *
     */
    @ApiModelProperty(name = "createTime", value = "创建时间")
    private Date createTime;

    /**
     *
     * 更新时间
     *
     *
     *
     */
    @ApiModelProperty(name = "updateTime", value = "更新时间")
    private Date updateTime;


}
