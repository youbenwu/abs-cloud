package com.outmao.ebs.wallet.vo;


import com.outmao.ebs.wallet.entity.CashAlipayAccount;
import com.outmao.ebs.wallet.entity.CashBankAccount;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class CashVO {

    private Long id;

    private Long walletId;

    //订单号
    private String orderNo;

    //提现状态 0--未支付 1--已支付 2--交易完成 3--交易关闭
    @ApiModelProperty(name = "status", value = "提现状态 1--待审核 2--已完成 3--取消，审核不通过")
    private int status;

    //订单状态备注
    private String statusRemark;

    //外部状态 0--未处理 1--已打款 2--打款中 3--打款出错
    private int outStatus;

    //外部状态
    private String outStatusRemark;

    //支付宝帐号
    private CashAlipayAccount alipayAccount;

    //提现到银行卡
    private CashBankAccount bankAccount;

    //支出的金额
    private double totalAmount;

    //实际拿到的金额
    private double amount;

    //手续费
    private double fee;

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
