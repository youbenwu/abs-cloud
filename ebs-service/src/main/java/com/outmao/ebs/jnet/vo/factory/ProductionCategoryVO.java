package com.outmao.ebs.jnet.vo.factory;



import java.io.Serializable;
import java.util.Date;
import java.util.List;

/*
 * 生产品类
 * */

public class ProductionCategoryVO implements Serializable {


    private Long id;

    /**
     * 分类的父分类
     *
     */
    private Long parentId;

    /**
     *
     * 分类的子分类
     *
     */

    private List<ProductionCategoryVO> children;

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


    public List<ProductionCategoryVO> getChildren() {
        return children;
    }

    public void setChildren(List<ProductionCategoryVO> children) {
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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
