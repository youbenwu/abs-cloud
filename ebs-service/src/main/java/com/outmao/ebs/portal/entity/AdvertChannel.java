package com.outmao.ebs.portal.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 *
 * 广告频道
 *
 */
@ApiModel(value = "AdvertChannel", description = "广告频道")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@Data
@Entity
@Table(name = "portal_AdvertChannel")
public class AdvertChannel implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;


    /**
     *
     * ID
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     *
     * 组织ID
     *
     */
    @Column(nullable = false)
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
     * 投放是否收费 0--免费 1--收费
     *
     */
    @ApiModelProperty(name = "type", value = "投放是否收费 0--免费 1--收费")
    private int type;


    /**
     *
     * 位置可以显示广告数量
     *
     */
    @ApiModelProperty(name = "num", value = "位置可以显示广告数量")
    private int num;


    /**
     *
     * 最多可以投放广告数
     *
     */
    @ApiModelProperty(name = "maxNum", value = "最多可以投放广告数")
    private int maxNum;


    /**
     *
     * 广告投放价格（每天）
     *
     */
    @ApiModelProperty(name = "price", value = "广告投放价格（每天）")
    private double price;


    /**
     *
     * 频道编码 唯一标识 在广告里代表广告位置
     *
     */
    @ApiModelProperty(name = "code", value = "唯一编码 代表广告位置",required = true)
    @Column(unique = true)
    private String code;

    /**
     *
     * 频道标题
     *
     */
    @ApiModelProperty(name = "title", value = "频道标题",required = true)
    @Column(nullable = false)
    private String title;

    /**
     * 频道描述
     */
    @ApiModelProperty(name = "description", value = "频道描述",required = true)
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
