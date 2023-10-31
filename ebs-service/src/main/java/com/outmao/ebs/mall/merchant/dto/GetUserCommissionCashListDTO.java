package com.outmao.ebs.mall.merchant.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GetUserCommissionCashListDTO {

    private Long merchantId;

    private Long commissionId;

    private Long userId;

    @ApiModelProperty(name = "status", value = "状态 0--未处理 1--处理中 2--已退款 3--不通过")
    private Integer status;


}
