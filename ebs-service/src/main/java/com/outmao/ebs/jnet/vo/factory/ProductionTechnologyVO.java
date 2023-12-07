package com.outmao.ebs.jnet.vo.factory;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/*
 * 生产工艺
 * */

public class ProductionTechnologyVO implements Serializable {


    private Long id;

    private Long parentId;

    /**
     *
     * 分类的子分类
     *
     */
    private List<ProductionTechnologyVO> children;

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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public List<ProductionTechnologyVO> getChildren() {
        return children;
    }

    public void setChildren(List<ProductionTechnologyVO> children) {
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
