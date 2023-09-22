package com.outmao.ebs.wallet.pay.alipay;

public interface AlipayNotifyListener {

    public void notify(String tradeStatus, String out_trade_no, String trade_no, String total_amount);

}
