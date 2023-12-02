package com.outmao.ebs.jnet.entity.factory;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/*
 * 行业类
 *
 * */
@Entity
@Table(name = "z_Industry")
public class Industry implements Serializable {

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


//    /**
//     * 分类的父分类
//     *
//     */
//    @JsonIgnore
//    @ManyToOne(cascade = CascadeType.REFRESH, optional = true, fetch = FetchType.LAZY)
//    @JoinColumn(name = "parentId")
//    private Industry parent;
//
//    /**
//     *
//     * 分类的子分类
//     *
//     */
//    @OneToMany(mappedBy = "parent", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
//    private List<Industry> children;
//
//    /**
//     * 多级分类中所处的级别，级别从0开始
//     *
//     */
//    private int level;
//
//    /**
//     * 多级分类中是否是叶子节点的标识
//     *
//     */
//    private boolean leaf;

    /**
     * 行业名称
     */
    private String name;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public Industry getParent() {
//        return parent;
//    }
//
//    public void setParent(Industry parent) {
//        this.parent = parent;
//    }
//
//    public List<Industry> getChildren() {
//        return children;
//    }
//
//    public void setChildren(List<Industry> children) {
//        this.children = children;
//    }
//
//    public int getLevel() {
//        return level;
//    }
//
//    public void setLevel(int level) {
//        this.level = level;
//    }
//
//    public boolean isLeaf() {
//        return leaf;
//    }
//
//    public void setLeaf(boolean leaf) {
//        this.leaf = leaf;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
