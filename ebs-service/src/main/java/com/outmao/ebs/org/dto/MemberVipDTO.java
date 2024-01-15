package com.outmao.ebs.org.dto;


import com.outmao.ebs.common.vo.TimeSpan;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 *
 * 充会员
 *
 */
@Data
public class MemberVipDTO {


    private Long memberId;

    /**
     *
     * vip等级
     *
     */
    private int vip;

    /**
     *
     * 充值期间
     *
     */
    private TimeSpan time;

    /**
     *
     * 消费金额
     *
     */
    private double amount;




}
