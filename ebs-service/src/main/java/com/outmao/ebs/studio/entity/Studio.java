package com.outmao.ebs.studio.entity;

import com.outmao.ebs.common.vo.Contact;
import com.outmao.ebs.common.vo.Item;
import com.outmao.ebs.mall.merchant.common.data.BindingMerchant;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * 影视公司
 */
@Data
@Entity
@Table(name = "ebs_Studio")
public class Studio  implements Serializable, BindingMerchant {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 自动ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * 组织ID
     */
    private Long orgId;

    /**
     * 商家ID
     */
    private Long merchantId;

    /**
     * 店铺ID
     */
    private Long shopId;

    /**
     * 商家钱包ID
     */
    private Long walletId;

    /**
     * 所属用户ID
     */
    @Column(nullable = false,updatable = false,unique = true)
    private Long userId;

    /**
     *
     * 搜索关键字
     *
     */
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String keyword;

    /**
     * 名称
     */
    @Column(nullable = false)
    private String name;

    /**
     * 简介
     */
    private String intro;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    @Override
    public Contact getContact() {
        return null;
    }

    @Override
    public Item toItem() {
        return new Item(id,"Studio",name);
    }


}
