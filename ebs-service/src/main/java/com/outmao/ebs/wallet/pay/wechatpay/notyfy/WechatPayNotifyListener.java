package com.outmao.ebs.wallet.pay.wechatpay.notyfy;


import com.wechat.pay.java.service.payments.model.Transaction;
import com.wechat.pay.java.service.refund.model.RefundNotification;


public interface WechatPayNotifyListener {

    public void notify(Transaction transaction);

    public void notifyRefund(RefundNotification refundNotification);


}
