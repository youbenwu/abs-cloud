package com.outmao.ebs.hotel.entity;


import com.outmao.ebs.common.vo.Item;
import com.outmao.ebs.org.common.data.BindingOrg;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * 酒店
 *
 */
@Data
@Entity
@Table(name = "ebs_Hotel",uniqueConstraints = {
        @UniqueConstraint(columnNames = { "name", "area" }) })
public class Hotel implements Serializable ,BindingOrg {

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
    private Long orgId;

    /**
     * 用户ID
     */
    @Column(nullable = false,unique = true)
    private Long userId;

    /**
     * 商家ID
     */
    private Long merchantId;

    /**
     * 店铺ID
     */
    private Long shopId;

    /**
     *
     * 酒店状态  0--正常 1--禁用 2--未审核 3--审核中 4--审核失败
     *
     */
    private int status;

    /**
     *
     * 酒店状态备注
     *
     */
    private String statusRemark;


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
     * 酒店名称
     *
     */
    @Column(nullable = false)
    private String name;

    /**
     *
     * 酒店简介
     *
     */
    private String intro;

    /**
     *
     * 酒店LOGO
     *
     */
    private String logo;

    /**
     *
     * 酒店图片
     *
     */
    private String image;

    /**
     *
     * 酒店视频
     *
     */
    private String video;

    /**
     *
     *
     *
     */
    @Column(length = 1200)
    private String roomImages;

    /**
     *
     *
     *
     */
    @Column(length = 1200)
    private String images;


    /**
     *
     * 酒店服务说明
     *
     */
    private String business;

    /**
     *
     * 星级 1～5
     *
     */
    private int star;

    /**
     *
     *  酒店标记
     *
     */
    private String mark;

    /**
     *
     *  酒店成立时间
     *
     */
    private Date estTime;

    /**
     *
     * 营业执照
     *
     */
    private String license;

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
     * 联系信息
     *
     */
    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "contactId")
    private HotelContact contact;



    @ApiModelProperty(name = "latitude", value = "纬度")
    private Double latitude;// 纬度

    @ApiModelProperty(name = "longitude", value = "经度")
    private Double longitude;

    /**
     *
     * 地区 省市区
     *
     */
    private String area;


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
    public Long getParentOrgId() {
        return null;
    }


    @Override
    public Item toItem() {
        return new Item(id,"Hotel",name);
    }


}
