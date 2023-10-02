package com.outmao.ebs.mall.store.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.outmao.ebs.common.configuration.constant.Status;
import com.outmao.ebs.common.vo.Item;
import com.outmao.ebs.bbs.common.data.BindingSubject;
import com.outmao.ebs.bbs.entity.Subject;
import com.outmao.ebs.mall.merchant.entity.Merchant;
import com.outmao.ebs.org.common.data.BindingOrg;
import com.outmao.ebs.user.entity.User;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 *
 * 实体店
 *
 */
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@Data
@Entity
@Table(name = "ebs_Store")
public class Store implements Serializable, BindingSubject, BindingOrg {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     *
     * 店铺ID
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     *
     * 组织ID
     *
     */
    private Long orgId;


    /**
     *
     * 0--门店 1--仓库
     *
     */
    private int type;


    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "subjectId")
    private Subject subject;

    /**
     *
     * 店铺所属用户ID
     *
     */
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    /**
     *
     * 店铺所属商家
     *
     */
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "merchantId")
    private Merchant merchant;

    /**
     *
     * 联系信息
     *
     */
    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "contactId")
    private StoreContact contact;

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
     * 店铺标题
     *
     */
    @Column(unique = true)
    private String title;

    /**
     *
     * 店铺副标题
     *
     */
    private String subtitle;

    /**
     *
     * 店铺简介
     *
     */
    @Lob
    private String intro;

    /**
     *
     * 店铺图标
     *
     */
    private String logo;

    /**
     *
     * 店铺封面
     *
     */
    private String image;

    /**
     *
     * 主营区域
     *
     */
    private String businessArea;

    /**
     *
     * 店铺H5地址
     *
     */
    private String url;

    /**
     *
     * 店铺二维码地址，扫码访问店铺首页
     *
     */
    private String qrCode;

    /**
     *
     * 店铺状态
     *
     */
    private int status= Status.NotAudit.getStatus();

    /**
     *
     * 店铺备注
     *
     */
    private String statusRemark= Status.NotAudit.getStatusRemark();


    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;


    @Override
    public Long getUserId() {
        return user.getId();
    }

    @Override
    public Long getParentOrgId() {
        return merchant.getOrgId();
    }

    @Override
    public Item toItem() {
        return new Item(id,"Store",title);
    }
}
