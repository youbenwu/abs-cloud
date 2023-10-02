package com.outmao.ebs.mall.shop.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
public class SimpleShopVO  {

    /**
     *
     * 店铺ID
     *
     */
    @ApiModelProperty(name = "id", value = "店铺ID")
    private Long id;

    /**
     *
     * 店铺标题
     *
     */
    @ApiModelProperty(name = "title", value = "店铺标题",required = true)
    private String title;

    /**
     *
     * 店铺图标
     *
     */
    @ApiModelProperty(name = "logo", value = "店铺图标")
    private String logo;



}
