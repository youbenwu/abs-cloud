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
 * 成员
 *
 */
@Data
@Entity
@Table(name = "ebs_Member", uniqueConstraints = { @UniqueConstraint(columnNames = {"orgId","userId" }) })
public class Member implements Serializable {

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
     * 组织
     *
     */
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "orgId",updatable = false)
    private Org org;

    /**
     *
     * 用户
     *
     */
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "userId",updatable = false)
    private User user;


    /**
     *
     * 所属角色
     *
     */
    @JsonIgnore
    @OneToMany(mappedBy = "member", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private List<MemberRole> roles;

    /**
     *
     * 状态
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
     * 名称
     *
     */
    private String name;

    /**
     *
     * 头像
     *
     */
    private String avatar;

    /**
     *
     * 手机号
     *
     */
    private String phone;

    /**
     *
     * 电子邮箱
     *
     */
    private String email;


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
