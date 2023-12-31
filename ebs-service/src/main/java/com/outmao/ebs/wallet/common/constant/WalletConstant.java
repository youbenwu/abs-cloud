package com.outmao.ebs.wallet.common.constant;

public class WalletConstant {

    public static final String action_key ="wallet_action_key";

    /*
     *
     * 自定义业务类型
     * 100--手续费
     * 101--充值支付
     * 102--充值
     * 103--提现
     * 200--发红包支付
     * 201--领取红包
     * 300--商品支付
     * 301--门票支付
     *
     */
    //手续费
    public static final int business_type_fee =100;
    //充值支付
    public static final int business_type_recharge_pay =101;
    //充值
    public static final int business_type_recharge =102;
    //提现
    public static final int business_type_cash =103;
    //发红包
    public static final int business_type_redpacket_pay =200;
    //领取红包
    public static final int business_type_redpacket_recv =201;

    //商品支付
    public static final int business_type_pay =300;

    //门票支付
    public static final int business_type_ticket_pay =301;



}
