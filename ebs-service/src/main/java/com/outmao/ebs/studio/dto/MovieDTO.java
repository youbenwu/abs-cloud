package com.outmao.ebs.studio.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;

/**
 * 影视对象
 */
@Data
public class MovieDTO{


    /**
     * 自动ID
     */
    @ApiModelProperty(name = "id", value = "ID")
    private Long id;

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
    private int feeType;

    /**
     *
     * 价格
     *
     */
    @ApiModelProperty(name = "price", value = "价格")
    private double price;

    /**
     * 影视名称
     */
    @ApiModelProperty(name = "name", value = "影视名称")
    private String name;

    /**
     * 影视简介
     */
    @ApiModelProperty(name = "intro", value = "影视简介")
    private String intro;

    /**
     * 影视封面
     */
    @ApiModelProperty(name = "cover", value = "影视封面")
    private String cover;

    /**
     * 上映时间
     */
    @ApiModelProperty(name = "releaseTime", value = "上映时间")
    private Date releaseTime;





}
