package com.outmao.ebs.data.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * 分类
 *
 */
@Data
@Entity
@Table(name = "ebs_Category")
public class Category implements Serializable {

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
     *
     * 分类的父分类
     *
     */
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "parentId")
    private Category parent;

    /**
     *
     * 分类的子分类
     *
     */
    @JsonIgnore
    @OneToMany(mappedBy = "parent", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @OrderBy(value = "sort ASC")
    private List<Category> children;

    /**
     *
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
     * 排序序号，从0形始
     *
     */
    private int sort;

    /**
     *
     * 状态 0--显示 1--隐藏
     *
     */
    private int status;

    /**
     *
     * 类型
     *
     */
    private String type;

    /**
     *
     * 所属对象ID
     *
     */
    private Long targetId;

    /**
     *
     * 分类图片,分类的图片地址
     *
     */
    private String image;

    /**
     *
     * 分类的标题
     *
     */
    @Column(nullable = false)
    private String title;

    /**
     * 分类的描述
     */
    private String description;

    /**
     *
     * 拼音字母,分类标题的拼音字母，用于检索
     *
     */
    private String letter;

    /**
     *
     * 创建时间
     *
     */
    private Date createTime;

    /**
     *
     * 更新时间
     *
     */
    private Date updateTime;





}