package com.outmao.ebs.wallet.vo;


import com.outmao.ebs.wallet.entity.CashBankAccount;
import lombok.Data;

import java.util.Date;

@Data
public class CashVO {

    private Long id;

    private Long walletId;

    //提现到银行卡
    private CashBankAccount bankAccount;

    //订单号
    private String cashNo;

    //订单状态 0--未支付 1--已支付 2--交易完成 3--交易关闭
    private int status;

    //订单状态备注
    private String statusRemark;

    //需要提现的金额
    private long amount;

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
