package com.outmao.ebs.mall.inquiry.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * 用户询盘
 *
 */
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@Data
@Entity
@Table(name = "ebs_Inquiry")
public class Inquiry  implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;


    /**
     *
     * ID
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     *
     * 商家ID
     *
     */
    private Long merchantId;

    /**
     *
     * 状态 0--未回访 1--已回访
     *
     */
    private int status;

    /**
     *
     * 状态备注
     *
     */
    private String statusRemark;

    /**
     *
     * ID
     *
     */
    private Long userId;

    /**
     *
     * 商品ID
     *
     */
    private Long productId;

    /**
     *
     * 商品名称
     *
     */
    private String productTitle;

    /**
     *
     * 商品图片
     *
     */
    private String productImage;


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
     * 姓名
     *
     */
    private String name;

    /**
     *
     * 手机号码
     *
     */
    private String phone;

    /**
     *
     * 意向购买数量
     *
     */
    private Integer quantity;

    /**
     *
     * 用户备注
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
     * 更新时间
     *
     */
    private Date updateTime;


}
