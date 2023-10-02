package com.outmao.ebs.mall.takeLook.entity;


import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * 带看
 *
 * */
@Data
@Entity
@Table(name = "ebs_TakeLook")
public class TakeLook  implements Serializable {

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
     * 商家Id
     */
    @Column(nullable = false)
    private Long merchantId;

    /**
     * 门店ID
     */
    @Column(nullable = false)
    private Long storeId;

    /**
     *
     * 经纪人ID
     *
     */
    @Column(nullable = false)
    private Long brokerId;

    /**
     *
     * 带看经纪人ID
     *
     */
    private Long waiterId;

    /**
     *
     * 客户ID
     *
     */
    @Column(nullable = false)
    private Long customerId;

    /**
     *
     * 客户用户ID
     *
     */
    @Column(nullable = false)
    private Long userId;

    /**
     *
     * 客户意见
     *
     */
    private String feedback;

    /**
     *
     * 状态 0--未确认 1--待带看 2--带看中 3--带看完成 4--取消关闭
     *
     */
    private int status;

    /**
     *
     * 商品类型 0普通商品 11新楼盘 12二手房 13出租房
     *
     */
    private int productType;

    /**
     *
     * 预约时间
     *
     */
    private Date appointmentTime;

    /**
     *
     * 带看时间
     *
     */
    private Date lookTime;


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
