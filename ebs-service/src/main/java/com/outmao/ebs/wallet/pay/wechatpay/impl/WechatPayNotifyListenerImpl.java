package com.outmao.ebs.wallet.pay.wechatpay.impl;

import com.outmao.ebs.wallet.pay.wechatpay.WechatPayNotifyListener;
import com.outmao.ebs.wallet.service.TradeService;
import com.wechat.pay.java.service.payments.model.Transaction;
import com.wechat.pay.java.service.refund.model.RefundNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class WechatPayNotifyListenerImpl implements WechatPayNotifyListener {

    @Autowired
    private TradeService tradeService;


    /**
     *
     * @SerializedName("SUCCESS")
     *         SUCCESS,
     *         @SerializedName("REFUND")
     *         REFUND,
     *         @SerializedName("NOTPAY")
     *         NOTPAY,
     *         @SerializedName("CLOSED")
     *         CLOSED,
     *         @SerializedName("REVOKED")
     *         REVOKED,
     *         @SerializedName("USERPAYING")
     *         USERPAYING,
     *         @SerializedName("PAYERROR")
     *         PAYERROR,
     *         @SerializedName("ACCEPT")
     *         ACCEPT;
     * **/
    @Override
    public void notify(Transaction transaction) {
        switch(transaction.getTradeState()){
            case SUCCESS:
                tradeService.tradePay(transaction.getOutTradeNo());
                break;
            case CLOSED:
                tradeService.tradeClose(transaction.getOutTradeNo());
                break;
        }
    }


    /**
     *
     * @SerializedName("SUCCESS")
     *     SUCCESS,
     *     @SerializedName("CLOSED")
     *     CLOSED,
     *     @SerializedName("PROCESSING")
     *     PROCESSING,
     *     @SerializedName("ABNORMAL")
     *     ABNORMAL;
     *
     * **/
    @Override
    public void notifyRefund(RefundNotification refundNotification) {
        switch(refundNotification.getRefundStatus()){
            case SUCCESS:

                break;
            case CLOSED:

                break;
        }
    }


}
