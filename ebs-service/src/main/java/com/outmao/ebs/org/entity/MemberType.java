package com.outmao.ebs.org.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * 成员类型
 *
 */
@Data
@Entity
@Table(name = "ebs_MemberType", uniqueConstraints = { @UniqueConstraint(columnNames = {"memberId","type" }) })
public class MemberType implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;


    /**
     * 会员
     */
    public static final long type_member = 1;

    /**
     * 员工
     */
    public static final long type_staff = 2;


    /**
     * 自动编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false,updatable = false)
    private Long memberId;

    private int type;

    private String name;

    private Date createTime;

}
