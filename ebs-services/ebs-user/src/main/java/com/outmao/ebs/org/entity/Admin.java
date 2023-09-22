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
@Table(name = "ebs_Admin", uniqueConstraints = { @UniqueConstraint(columnNames = {"orgId","userId" }) })
public class Admin implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 成员ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     *
     * 所属组织
     *
     */
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "orgId")
    private Org org;

    /**
     *
     * 所属用户
     *
     */
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;


    /**
     *
     * 所属角色
     *
     */
    @JsonIgnore
    @OneToMany(mappedBy = "admin", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private List<AdminRole> roles;

    /**
     * 姓名
     */
    private String name;

    /**
     * 电话
     */
    private String phone;


    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;


}
