package com.outmao.ebs.mall.promotion.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.outmao.ebs.bbs.common.data.BindingSubjectId;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * 赠品促销
 *
 */
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@Data
@Entity
@Table(name = "ebs_Gifts")
public class Gifts implements Serializable {

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
     * 所属店铺ID
     *
     */
    @Column(nullable = false,updatable = false)
    private Long shopId;


    /**
     *
     * 状态 0--正常 1--已下架
     *
     */
    private int status;


    /**
     *
     * 主商品ID
     *
     */
    @Column(unique = true)
    private Long productId;


    /**
     *
     * 标题
     *
     */
    private String title;

    /**
     *
     * 描述
     *
     */
    private String description;


    /**
     *
     * 是否永远有效
     *
     */
    private boolean forEver;

    /**
     *
     * 活动开始时间
     *
     */
    private Date startTime;


    /**
     *
     * 活动开始时间
     *
     */
    private Date endTime;


    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;




}
