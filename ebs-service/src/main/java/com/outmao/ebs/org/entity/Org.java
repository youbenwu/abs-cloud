package com.outmao.ebs.org.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.outmao.ebs.user.entity.User;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * 组织机构
 *
 */
@Data
@Entity
@Table(name = "ebs_Org")
public class Org implements Serializable {

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
     * 组织类型
     *
     */
    private int type;

    /**
     *
     * 组织类型对应ID
     *
     */
    @Column(unique = true)
    private Long targetId;

    /**
     *
     * 用户
     *
     */
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    /**
     *
     * 上级组织
     *
     */
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "parentId")
    private Org parent;

    /**
     *
     * 下级组织
     *
     */
    @JsonIgnore
    @OneToMany(mappedBy = "parent", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private List<Org> children;

    /**
     *
     * 多级分类中所处的级别，级别从0开始
     *
     */
    private int level;

    /**
     *
     * 多级分类中是否是叶子节点的标识
     *
     */
    private boolean leaf=true;

    /**
     *
     *
     * 状态 0--正常 1--禁用 2--未审核 3--审核中 4--审核失败
     *
     *
     */
    private int status;

    /**
     *
     * 状态
     *
     */
    private String statusRemark;


    /**
     *
     * 组织编码
     *
     */
    @Column(unique = true)
    private String orgNo;


    /**
     *
     * 搜索关键字
     *
     */
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String keyword;

    /**
     *
     * 组织名称
     *
     */
    private String name;

    /**
     *
     * 组织简介
     *
     */
    private String intro;

    /**
     *
     * 联系信息
     *
     */
    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "contactId")
    private OrgContact contact;

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
