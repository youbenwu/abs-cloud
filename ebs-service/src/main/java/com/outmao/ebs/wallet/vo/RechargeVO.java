package com.outmao.ebs.wallet.vo;

import com.outmao.ebs.wallet.entity.RechargeAmount;
import lombok.Data;

import java.util.Date;

@Data
public class RechargeVO {

    private Long id;

    private Long walletId;

    //订单号
    private String orderNo;

    //充值交易号
    private String rechargeNo;


    //订单状态 0--未支付 1--已支付 2--交易完成 3--交易关闭
    private int status;

    //订单状态备注
    private String statusRemark;

    //需要充值的货币
    private RechargeAmount rechargeAmount;

    //需要支付的金额
    private double amount;

    //备注
    private String remark;

    //创建时间
    private Date createTime;

    //支付时间
    private Date successTime;

    //完成时间
    private Date finishTime;

    //关闭时间
    private Date closeTime;

}
