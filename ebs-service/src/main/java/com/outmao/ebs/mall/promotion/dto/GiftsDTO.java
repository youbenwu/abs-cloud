package com.outmao.ebs.mall.promotion.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class GiftsDTO {

    /**
     *
     * ID
     *
     */
    private Long id;

    /**
     *
     * 所属店铺ID
     *
     */
    @ApiModelProperty(name = "shopId", value = "所属店铺ID")
    private Long shopId;

    /**
     *
     * 主商品ID
     *
     */
    @ApiModelProperty(name = "productId", value = "主商品ID")
    private Long productId;


    /**
     *
     * 赠品SKU列表
     *
     */
    @ApiModelProperty(name = "skus", value = "赠品SKU列表")
    private List<GiftsProductSkuDTO> skus;


    /**
     *
     * 标题
     *
     */
    @ApiModelProperty(name = "title", value = "标题")
    private String title;

    /**
     *
     * 描述
     *
     */
    @ApiModelProperty(name = "description", value = "描述")
    private String description;

    /**
     *
     * 是否永远有效
     *
     */
    @ApiModelProperty(name = "forEver", value = "是否永远有效")
    private boolean forEver=true;


    /**
     *
     * 活动开始时间
     *
     */
    @ApiModelProperty(name = "startTime", value = "活动开始时间")
    private Date startTime;


    /**
     *
     * 活动结束时间
     *
     */
    @ApiModelProperty(name = "endTime", value = "活动结束时间")
    private Date endTime;


}
