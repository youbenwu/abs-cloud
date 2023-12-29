package com.outmao.ebs.mall.distribution.entity;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 *
 * 迁眼平台分销设置
 *
 * */
@Data
@Entity
@Table(name = "QyDistributionConfig")
public class QyDistributionConfig implements Serializable {

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
     * 0--启用 1--禁用
     */
    private int status;

    //4个等级
    //设备数量
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="deviceNumberFrom", column=@Column(name="deviceNumberFromA")),
            @AttributeOverride(name="deviceNumberTo", column=@Column(name="deviceNumberToA")),
            @AttributeOverride(name="partnerAmount", column=@Column(name="partnerAmountA")),
    })
    private QyDistributionConfigLevel levelA;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="deviceNumberFrom", column=@Column(name="deviceNumberFromB")),
            @AttributeOverride(name="deviceNumberTo", column=@Column(name="deviceNumberToB")),
            @AttributeOverride(name="partnerAmount", column=@Column(name="partnerAmountB")),
    })
    private QyDistributionConfigLevel levelB;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="deviceNumberFrom", column=@Column(name="deviceNumberFromC")),
            @AttributeOverride(name="deviceNumberTo", column=@Column(name="deviceNumberToC")),
            @AttributeOverride(name="partnerAmount", column=@Column(name="partnerAmountC")),
    })
    private QyDistributionConfigLevel levelC;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="deviceNumberFrom", column=@Column(name="deviceNumberFromD")),
            @AttributeOverride(name="deviceNumberTo", column=@Column(name="deviceNumberToD")),
            @AttributeOverride(name="partnerAmount", column=@Column(name="partnerAmountD")),
    })
    private QyDistributionConfigLevel levelD;

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
