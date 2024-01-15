package com.outmao.ebs.org.entity;


import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 *
 * 会员VIP信息
 *
 */
@Data
@Entity
@Table(name = "ebs_MemberVip")
public class MemberVip implements Serializable {

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
     * vip等级
     *
     */
    @Column(unique = true,nullable = false,updatable = false)
    private Long memberId;

    /**
     *
     * vip等级
     *
     */
    private int vip;

    /**
     *
     * 到期时间
     *
     */
    private Date expireTime;

    /**
     *
     * 总消费金额
     *
     */
    private double amount;


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
