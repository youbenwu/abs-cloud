package com.outmao.ebs.jnet.entity.factory;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/*
 * 生产品类
 * */
@Entity(name = "ZProductionCategory")
@Table(name = "z_ProductionCategory")
public class ProductionCategory  implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 自动编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * 分类的父分类
     *
     */
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "parentId")
    private ProductionCategory parent;

    /**
     *
     * 分类的子分类
     *
     */
    @OneToMany(mappedBy = "parent", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @OrderBy("sort desc")
    private List<ProductionCategory> children;

    /**
     * 多级分类中所处的级别，级别从0开始
     *
     */
    private int level;

    /**
     * 多级分类中是否是叶子节点的标识
     *
     */
    private boolean leaf;

    /**
     *
     * 排序，大的排在前
     *
     */
    private int sort;


    /**
     * 行业
     */
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "industryId")
    private Industry industry;

    /**
     * 品类名称
     */
    private String name;


    /**
     * 品类图片
     */
    private String image;


    /**
     * 创建时间
     */
    private Date createTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ProductionCategory getParent() {
        return parent;
    }

    public void setParent(ProductionCategory parent) {
        this.parent = parent;
    }

    public List<ProductionCategory> getChildren() {
        return children;
    }

    public void setChildren(List<ProductionCategory> children) {
        this.children = children;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public Industry getIndustry() {
        return industry;
    }

    public void setIndustry(Industry industry) {
        this.industry = industry;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
