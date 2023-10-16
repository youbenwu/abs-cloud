package com.outmao.ebs.org.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.outmao.ebs.common.configuration.constant.Status;
import com.outmao.ebs.user.entity.User;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
     *
     * 组织类型
     * 0--平台
     * 1--租户
     * 2--部门
     * 3--商家
     * 4--门店
     * 5--店铺
     * 6--酒店
     *
     */
    public static final int TYPE_SYSTEM=0;
    public static final int TYPE_TENANT=1;
    public static final int TYPE_DEPART=2;
    public static final int TYPE_MERCHANT=3;
    public static final int TYPE_STORE=4;
    public static final int TYPE_SHOP=5;
    public static final int TYPE_HOTEL=6;

    /**
     * 自动编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * 多个父级组织ID
     */
    private String parents;

    /**
     *
     * 组织类型对应ID
     * 租户ID
     * 部门ID
     * 商家ID
     * 门店ID
     * 店铺ID
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
     * 状态 0--正常 1--禁用 2--未审核 3--审核中 4--审核成功 5--审核失败 6--删除
     *
     *
     */
    private int status= Status.NotAudit.getStatus();

    /**
     *
     * 状态
     *
     */
    private String statusRemark=Status.NotAudit.getStatusRemark();

    /**
     *
     * 组织类型 0--平台 1--租户
     *
     */
    private int type=TYPE_SYSTEM;

    /**
     *
     * 组织编码
     *
     */
    @Column(unique = true)
    private String orgNo;

    /**
     *
     * 企业ID
     *
     * */
    private Long enterpriseId;


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
