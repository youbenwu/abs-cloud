package com.outmao.ebs.mall.merchant.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.outmao.ebs.user.entity.User;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * 用户佣金
 *
 * */
@Data
@Entity
@Table(name = "ebs_UserCommission")
public class UserCommission  implements Serializable {

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


    @Column(nullable = false)
    private Long merchantId;

    /**
     *
     * 上级ID
     *
     */
    private Long parentId;

    /**
     * 用户
     */
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    /**
     *
     * 经纪人ID 合伙人ID
     *
     */
    @Column(unique = true,nullable = false)
    private Long targetId;

    /**
     *
     * 0--经纪人佣金 1--合伙人佣金 2--酒店设备佣金 3--租赁酒店设备佣金
     *
     */
    private int type;

    /**
     *
     * 可提现佣金
     *
     */
    private double amount;

    /**
     *
     * 佣金总额
     *
     */
    private double totalAmount;

    /**
     *
     * 统计时间
     *
     */
    private Date statsTime;


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
