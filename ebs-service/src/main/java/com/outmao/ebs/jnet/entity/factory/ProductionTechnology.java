package com.outmao.ebs.jnet.entity.factory;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/*
 * 生产工艺
 * */
@Entity
@Table(name = "z_ProductionTechnology")
public class ProductionTechnology   implements Serializable {

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
    private ProductionTechnology parent;

    /**
     *
     * 分类的子分类
     *
     */
    @OneToMany(mappedBy = "parent", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private List<ProductionTechnology> children;

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
     * 分类
     */
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "industryId")
    private Industry industry;

    /**
     * 工艺名称
     */
    private String name;


    /**
     * 工艺单位后缀
     */
    private String suffix;


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

    public ProductionTechnology getParent() {
        return parent;
    }

    public void setParent(ProductionTechnology parent) {
        this.parent = parent;
    }

    public List<ProductionTechnology> getChildren() {
        return children;
    }

    public void setChildren(List<ProductionTechnology> children) {
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

    public Industry getIndustry() {
        return industry;
    }

    public void setIndustry(Industry industry) {
        this.industry = industry;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
