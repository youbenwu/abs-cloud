package com.outmao.ebs.mall.merchant.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.outmao.ebs.user.entity.User;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * 商家客户
 *
 * */
@Data
@Entity
@Table(name = "ebs_MerchantCustomer", uniqueConstraints = { @UniqueConstraint(columnNames = {"merchantId","userId" }) })
public class MerchantCustomer implements Serializable {

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
     * 商家
     */
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "merchantId")
    private Merchant merchant;

    /**
     * 用户
     */
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;


    /**
     *
     * 经纪人
     *
     */
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "brokerId")
    private MerchantBroker broker;


    /**
     *
     * 发展这个客户的合伙人
     *
     */
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "partnerId")
    private MerchantPartner partner;

    /**
     *
     * 搜索关键字
     *
     */
    @JsonIgnore
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String keyword;

    /**
     *
     * 名称
     *
     */
    private String name;

    /**
     *
     * 手机号
     *
     */
    private String phone;

    /**
     *
     * 商家备注备注
     *
     */
    private String remark;


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
