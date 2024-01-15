package com.outmao.ebs.wallet.pay.wechatpay.notyfy.impl;

import com.outmao.ebs.wallet.common.constant.PayChannel;
import com.outmao.ebs.wallet.dto.TradePayDTO;
import com.outmao.ebs.wallet.entity.Trade;
import com.outmao.ebs.wallet.pay.wechatpay.notyfy.WechatPayNotifyListener;
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
                Trade trade=tradeService.getTradeByTradeNo(transaction.getOutTradeNo());
                TradePayDTO payDTO=new TradePayDTO();
                payDTO.setTradeNo(transaction.getOutTradeNo());
                payDTO.setPayChannel(PayChannel.WxPay.getType());
                payDTO.setOutPayType(trade.getOutPayType());
                payDTO.setReceiptAmount(transaction.getAmount().getTotal());
                tradeService.tradePay(payDTO);
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
