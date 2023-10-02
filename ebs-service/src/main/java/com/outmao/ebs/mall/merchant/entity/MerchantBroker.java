package com.outmao.ebs.mall.merchant.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.outmao.ebs.common.vo.Item;
import com.outmao.ebs.bbs.common.data.BindingSubject;
import com.outmao.ebs.bbs.entity.Subject;
import com.outmao.ebs.user.entity.User;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * 商家员工、经纪人
 *
 * */
@Data
@Entity
@Table(name = "ebs_MerchantBroker", uniqueConstraints = { @UniqueConstraint(columnNames = {"merchantId","userId" }) })
public class MerchantBroker implements Serializable, BindingSubject {

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

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "subjectId")
    private Subject subject;

    /**
     *
     * 商家
     *
     */
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "merchantId")
    private Merchant merchant;

    /**
     *
     * 用户
     *
     */
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    /**
     *
     * 佣金ID
     *
     */
    private Long commissionId;

    /**
     *
     * 门店ID
     *
     */
    private Long storeId;


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
     * 服务内容
     *
     */
    private String business;

    /**
     *
     * 服务佣金
     * 只用于显示
     *
     */
    private String fees;

    /**
     *
     * 员工H5地址
     *
     */
    private String url;

    /**
     *
     * 二维码地址，扫码访问经纪人首页
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
     * 经纪人口令
     *
     */
    @Column(unique = true)
    private String code;

    /**
     *
     * 状态 0--正常 1--禁用 2--未审核 3--审核中 4--审核成功 5--审核失败 6--删除
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

    @Override
    public Item toItem() {
        return new Item(id,"MerchantBroker",name);
    }


}
