package com.outmao.ebs.mall.merchant.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;


@ApiModel(value = "GetUserCommissionTotalAmountDTO", description = "获取时间段内的佣金收益")
@Data
public class GetUserCommissionTotalAmountDTO {

    @ApiModelProperty(name = "merchantId", value = "商家ID")
    private Long merchantId;

    @ApiModelProperty(name = "commissionId", value = "前端必传")
    private Long commissionId;

    @ApiModelProperty(name = "userId", value = "用户ID")
    private Long userId;

    @ApiModelProperty(name = "startTime", value = "开始时间")
    private Date startTime;

    @ApiModelProperty(name = "endTime", value = "结束时间")
    private Date endTime;

}
