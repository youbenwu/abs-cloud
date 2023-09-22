package com.outmao.ebs.org.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * 角色付给成员
 *
 */
@Data
@Entity
@Table(name = "ebs_AdminRole", uniqueConstraints = {@UniqueConstraint(columnNames = { "adminId","roleId" })})
public class AdminRole implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     *
     * 成员
     *
     */
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "adminId")
    private Admin admin;

    /**
     *
     * 角色
     *
     */
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "roleId")
    private Role role;

    /**
     * 创建时间
     */
    private Date createTime;


}
