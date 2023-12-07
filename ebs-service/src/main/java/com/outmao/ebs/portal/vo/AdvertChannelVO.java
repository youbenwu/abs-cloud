package com.outmao.ebs.portal.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;


/**
 *
 * 广告频道
 *
 */
@ApiModel(value = "AdvertChannelVO", description = "广告频道")
@Data
public class AdvertChannelVO implements Serializable {


    /**
     *
     * ID
     *
     */
    private Long id;

    /**
     *
     * 所属组织ID
     *
     */
    private Long orgId;

    /**
     *
     * 对应一个产品ID，用于投放收费
     *
     */
    @ApiModelProperty(name = "productId", value = "对应一个产品ID，用于投放收费")
    private Long productId;

    /**
     *
     * 广告类型 0--系统广告 1--企业广告 2--个人广告
     *
     */
    @ApiModelProperty(name = "type", value = "广告类型 0--系统广告 1--企业广告 2--个人广告")
    private int type;


    /**
     *
     * 频道编码 唯一标识 在广告里代表广告位置
     *
     */
    @ApiModelProperty(name = "code", value = "唯一编码",required = true)
    private String code;

    /**
     *
     * 频道标题
     *
     */
    @ApiModelProperty(name = "title", value = "频道标题",required = true)
    private String title;

    /**
     * 频道描述
     */
    @ApiModelProperty(name = "description", value = "频道描述")
    private String description;

    /**
     *
     * 创建时间
     *
     */
    private Date createTime;

    /**
     * 更新时间
     *
     */
    private Date updateTime;


}
