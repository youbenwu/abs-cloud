package com.outmao.ebs.mall.merchant.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.outmao.ebs.user.entity.User;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * 合伙人、兼职经纪人 有上下级关系
 *
 * */
@Data
@Entity
@Table(name = "ebs_MerchantPartner", uniqueConstraints = { @UniqueConstraint(columnNames = {"merchantId","userId" }) })
public class MerchantPartner implements Serializable {

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
     * 统计数据
     */
    @JsonIgnore
    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.REMOVE},fetch = FetchType.LAZY)
    @JoinColumn(name = "statsId")
    private MerchantPartnerStats stats;

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
     * 佣金ID
     *
     */
    private Long commissionId;


    /**
     *
     * 分类的父分类
     *
     */
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "parentId")
    private MerchantPartner parent;

    /**
     * 分类的子分类
     *
     */
    @JsonIgnore
    @OneToMany(mappedBy = "parent", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private List<MerchantPartner> children;

    /**
     * 多级分类中所处的级别，级别从0开始
     *
     */
    private int level;

    /**
     * 多级分类中是否是叶子节点的标识
     *
     */
    private boolean leaf;


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
     * 头像
     *
     */
    private String avatar;
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
     * 电子邮箱
     *
     */
    private String email;

    /**
     *
     * 身份证号码
     *
     */
    private String idCardNo;

    /**
     *
     * 身份证正面
     *
     */
    private String idCardFront;

    /**
     *
     * 身份证反面
     *
     */
    private String idCardBack;


    /**
     *
     * 合伙人H5地址
     *
     */
    private String url;

    /**
     *
     * 二维码地址，扫码访问合伙人H5首页
     *
     */
    private String qrCode;

    /**
     *
     * 绑定客户二维码
     *
     */
    private String brokerQrCode;

    /**
     *
     * 绑定下级兼职经纪人二维码
     *
     */
    private String pyramidQrCode;

    /**
     *
     * 兼职经纪人口令
     *
     */
    @Column(unique = true)
    private String code;


    /**
     *
     * 状态 0--正常 1--禁用 2--未审核 3--审核中 4--审核失败
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
