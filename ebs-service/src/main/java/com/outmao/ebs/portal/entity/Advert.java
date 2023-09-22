package com.outmao.ebs.portal.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.outmao.ebs.common.vo.BindingItem;
import com.outmao.ebs.common.vo.SortEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.persistence.*;
import java.util.Date;

/**
 *
 * 广告
 *
 */
@ApiModel(value = "Advert", description = "广告")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@Data
@Entity
@Table(name = "portal_Advert")
public class Advert extends SortEntity {


    /**
     *
     * 0--未上架 1--已上架
     *
     * */
    @ApiModelProperty(name = "status", value = "0--未上架 1--已上架")
    private int status;

    /**
     *
     * 广告类型
     *
     * */
    @ApiModelProperty(name = "type", value = "广告类型")
    private int type;

    /**
     *
     * 绑定商品 item.id--商品ID item.type--Product
     *
     * */
    @ApiModelProperty(name = "item", value = "绑定商品 item.id--商品ID item.type--Product")
    @Embedded
    private BindingItem item;

    @ApiModelProperty(name = "title", value = "广告名称")
    private String title;

    @ApiModelProperty(name = "image", value = "图片地址")
    private String image;

    @ApiModelProperty(name = "startTime", value = "广告展示开始时间")
    private Date startTime;

    @ApiModelProperty(name = "endTime", value = "广告展示结束时间")
    private Date endTime;



}
