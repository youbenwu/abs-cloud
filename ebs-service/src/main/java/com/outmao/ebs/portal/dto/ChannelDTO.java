package com.outmao.ebs.portal.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ChannelDTO {

    /**
     *
     * ID
     *
     */
    private Long id;

    /**
     *
     * 组织ID
     *
     */
    @ApiModelProperty(name = "orgId", value = "组织ID",required = true)
    private Long orgId;


    /**
     *
     * 0--总后台 1--酒店
     *
     */
    @ApiModelProperty(name = "type", value = "0--总后台 1--酒店")
    private int type;


    @ApiModelProperty(name = "code", value = "频道编码")
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


}
