package com.outmao.ebs.studio.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 获取影视列表
 */
@Data
public class GetMovieListDTO {


    /**
     *
     * 所属组织ID
     *
     */
    @ApiModelProperty(name = "orgId", value = "所属组织ID")
    private Long orgId;

    /**
     * 发布用户ID
     */
    @ApiModelProperty(name = "userId", value = "发布用户ID")
    private Long userId;


    /**
     * 类型ID
     */
    @ApiModelProperty(name = "categoryId", value = "类型ID")
    private Long categoryId;

    /**
     * 付费类型
     * 0--免费 1--会员 2--付费
     */
    @ApiModelProperty(name = "feeType", value = "付费类型 0--免费 1--会员 2--付费")
    private Integer feeType;


    @ApiModelProperty(name = "feeUserId", value = "付费用户ID")
    private Long feeUserId;


    private String keyword;


}
