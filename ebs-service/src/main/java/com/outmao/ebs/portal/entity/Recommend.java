package com.outmao.ebs.portal.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.outmao.ebs.common.vo.BindingItem;
import com.outmao.ebs.common.vo.SortEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * 首页推荐
 *
 */
@ApiModel(value = "Recommend", description = "首页推荐")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@Data
@Entity
@Table(name = "portal_Recommend")
public class Recommend  extends SortEntity {

    /**
     *
     * 推荐类型 0--首页 1--banner
     *
     * */
    @ApiModelProperty(name = "type", value = "推荐类型 0--首页 1--banner")
    private int type;

    /**
     *
     * 频道ID
     *
     * */
    @ApiModelProperty(name = "channelId", value = "频道ID")
    private Long channelId;

    /**
     *
     * 绑定内容
     *
     * */
    @ApiModelProperty(name = "item", value = "绑定对像")
    @Embedded
    private BindingItem item;

    @ApiModelProperty(name = "title", value = "名称")
    private String title;

    @ApiModelProperty(name = "image", value = "图片")
    private String image;


}
