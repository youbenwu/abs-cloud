package com.outmao.ebs.org.vo;


import lombok.Data;
import java.util.Date;


/**
 *
 * 会员VIP信息
 *
 */
@Data
public class MemberVipVO {


    /**
     * 自动编号
     */
    private Long id;

    /**
     *
     * 会员ID
     *
     */
    private Long memberId;

    /**
     *
     * vip等级
     *
     */
    private int vip;

    /**
     *
     * 到期时间
     *
     */
    private Date expireTime;

    /**
     *
     * 总消费金额
     *
     */
    private double amount;


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
