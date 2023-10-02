package com.outmao.ebs.mall.store.dto;

import com.outmao.ebs.common.vo.Contact;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@ApiModel(value = "StoreDTO", description = "保存门店参数")
@Data
public class StoreDTO {

    /**
     *
     * 门店ID
     *
     */
    @ApiModelProperty(name = "id", value = "门店ID")
    private Long id;

    /**
     *
     * 0--门店 1--仓库
     *
     */
    @ApiModelProperty(name = "type", value = "0--门店 1--仓库")
    private int type;

    /**
     *
     * 商家ID
     *
     *
     */
    @ApiModelProperty(name = "merchantId", value = "商家ID",required = true)
    private Long merchantId;


    /**
     *
     * 联系信息
     *
     */
    @ApiModelProperty(name = "contact", value = "联系信息")
    private Contact contact;


    /**
     *
     * 店铺标题
     *
     */
    @ApiModelProperty(name = "title", value = "店铺标题",required = true)
    private String title;

    /**
     *
     * 店铺副标题
     *
     */
    @ApiModelProperty(name = "subtitle", value = "店铺副标题")
    private String subtitle;

    /**
     *
     * 店铺简介
     *
     */
    @ApiModelProperty(name = "intro", value = "店铺简介")
    private String intro;

    /**
     *
     * 店铺图标
     *
     */
    @ApiModelProperty(name = "logo", value = "店铺图标")
    private String logo;

    /**
     *
     * 店铺封面
     *
     */
    @ApiModelProperty(name = "image", value = "店铺封面")
    private String image;

    /**
     *
     * 主营区域
     *
     */
    @ApiModelProperty(name = "主营区域", value = "主营区域")
    private String businessArea;


}
