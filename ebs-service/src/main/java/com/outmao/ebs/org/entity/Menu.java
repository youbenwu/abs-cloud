package com.outmao.ebs.org.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * 后台管理菜单
 *
 * */

@Data
@Entity
@Table(name = "ebs_Menu")
public class Menu implements Serializable
{

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
     *
     * 上级
     *
     */
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
    @JoinColumn(name = "parentId",updatable = false)
    private Menu parent;

    /**
     *
     * 多级分类中所处的级别，级别从0开始
     *
     */
    private int level=0;

    /**
     *
     * 多级分类中是否是叶子节点的标识
     *
     */
    private boolean leaf=true;

    /**
     *
     * 子权限
     *
     */
    @JsonIgnore
    @OneToMany(mappedBy = "parent", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private List<Menu> children;

    /**
     * 排序 从0开始
     */
    private int sort;


    /**
     * 0--显示 1--隐藏
     */
    private int status;

    /**
     *
     * 菜单URL
     *
     */
    private String url;

    /**
     *
     * 菜单路径
     *
     */
    @Column(unique = true)
    private String path;

    /**
     * 显示图标
     */
    private String icon;

    /**
     * 显示名称
     */
    private String name;

    /**
     * 菜单描述
     */
    private String description;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;


}
