package com.outmao.ebs.mall.merchant.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;


@ApiModel(value = "UserCommissionDTO", description = "创建佣金")
@Data
public class UserCommissionDTO {


    /**
     * 商家
     */
    private Long merchantId;

    /**
     * 用户
     */
    private Long userId;

    /**
     *
     * 0--经纪人佣金 1--合伙人佣金
     *
     */
    private int type;

    /**
     *
     * 上级ID
     *
     */
    private Long parentId;

    /**
     *
     * 经纪人ID 合伙人ID
     *
     */
    private Long targetId;


}
