package com.outmao.ebs.mall.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "ebs_ShopProductCategory")
public class ShopProductCategory implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     *
     * 分类唯一不变标识
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * 店铺
     */
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "shopId")
    private Shop shop;

    /**
     * 分类的父分类
     *
     */
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "parentId")
    private ShopProductCategory parent;

    /**
     * 分类的子分类
     *
     */
    @JsonIgnore
    @OneToMany(mappedBy = "parent", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @OrderBy(value = "sort ASC")
    private List<ShopProductCategory> children;

    /**
     * 多级分类中所处的级别，级别从0开始
     *
     */
    private int level;

    /**
     * 多级分类中是否是叶子节点的标识
     *
     */
    private boolean leaf=true;

    /**
     * 排序序号，从0形始
     *
     */
    private int sort;

    /**
     *
     * 酒店ID，酒店中商品
     *
     */
    private Long hotelId;

    /**
     *
     * 商品类型
     *
     */
    private Integer productType;


    /**
     * 分类标题
     */
    private String title;

    /**
     *
     * 分类描述
     *
     */
    private String description;

    /**
     * 分类图片
     */
    private String image;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;


}
