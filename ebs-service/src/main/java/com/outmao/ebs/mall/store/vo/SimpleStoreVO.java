package com.outmao.ebs.mall.store.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@ApiModel(value = "SimpleStoreVO", description = "门店信息")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
public class SimpleStoreVO {

    /**
     *
     * 门店ID
     *
     */
    @ApiModelProperty(name = "id", value = "门店ID")
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
