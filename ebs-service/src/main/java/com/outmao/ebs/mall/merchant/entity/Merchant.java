package com.outmao.ebs.mall.merchant.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.outmao.ebs.common.configuration.constant.Status;
import com.outmao.ebs.common.vo.Item;
import com.outmao.ebs.org.common.data.BindingOrg;
import com.outmao.ebs.user.entity.User;
import com.outmao.ebs.wallet.common.data.BindingWallet;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * 商家
 *
 * */
@Data
@Entity
@Table(name = "ebs_Merchant")
public class Merchant implements Serializable , BindingOrg, BindingWallet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     *
     * 自动编号
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * 组织ID
     */
    @Column(unique = true)
    private Long orgId;

    /**
     *
     * 用户
     *
     */
    @JsonIgnore
    @OneToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    /**
     *
     * 店铺ID
     *
     */
    private Long shopId;

    /**
     *
     * 商家类型 0--企业 1--个人
     *
     * */
    private int type;


    /**
     *
     * 钱包ID
     *
     * */
    private Long walletId;

    /**
     *
     * 企业ID
     *
     * */
    private Long enterpriseId;

    /**
     *
     * 状态 0--正常 1--禁用 2--未审核 3--审核中 4--审核失败
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
     * 搜索关键字
     *
     */
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String keyword;


    /**
     *
     * 商家名称
     *
     * */
    private String name;

    /**
     *
     * 商家简介
     *
     */
    @Lob
    private String intro;

    /**
     *
     * 联系信息
     *
     */
    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "contactId")
    private MerchantContact contact;

    /**
     *
     * 商家首页H5地址
     *
     */
    private String url;

    /**
     *
     * 商家二维码地址，扫码访问商家首页
     *
     */
    private String qrCode;

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


    @Override
    public Long getUserId() {
        return user.getId();
    }

    @Override
    public Long getParentOrgId() {
        return null;
    }

    @Override
    public Item toItem() {
        return new Item(id,"Merchant",name);
    }


}
