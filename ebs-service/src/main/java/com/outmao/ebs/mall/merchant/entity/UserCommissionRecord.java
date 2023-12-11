package com.outmao.ebs.mall.merchant.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * 用户佣金记录
 *
 * */
@Data
@Entity
@Table(name = "ebs_UserCommissionRecord", uniqueConstraints = { @UniqueConstraint(columnNames = {"commissionId","orderId" }) })
public class UserCommissionRecord implements Serializable {

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
     * 佣金
     */
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "commissionId")
    private UserCommission commission;

    /**
     * 商家ID
     */
    @Column(nullable = false)
    private Long merchantId;

    /**
     * 用户ID
     */
    @Column(nullable = false)
    private Long userId;

    /**
     *
     * 关联的订单ID
     *
     */
    private Long orderId;

    /**
     *
     * 0--直接收益 1--子级产生的收益
     *
     */
    private int level;

    /**
     *
     * 佣金
     *
     */
    private double amount;

    /**
     *
     * 类型 0--收益 1--提现 2--提现取消
     *
     */
    private int type;

    /**
     *
     * 显示图片
     *
     */
    private String image;

    /**
     *
     * 显示备注
     *
     */
    private String remark;

    /**
     *
     * 创建时间
     *
     */
    private Date createTime;


}
