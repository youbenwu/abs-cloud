package com.outmao.ebs.wallet.common.util;

import com.outmao.ebs.wallet.common.constant.TradeStatus;
import com.outmao.ebs.wallet.entity.Trade;

public class TradeUtil {

    public static boolean isSucceed(Trade trade){
        return trade.getStatus()== TradeStatus.TRADE_SUCCEED.getStatus();
    }

    public static boolean isFinished(Trade trade){
        return trade.getStatus()== TradeStatus.TRADE_FINISHED.getStatus();
    }

    public static boolean isClosed(Trade trade){
        return trade.getStatus()== TradeStatus.TRADE_CLOSED.getStatus();
    }

    public static boolean isWaitPay(Trade trade){
        return trade.getStatus()== TradeStatus.TRADE_WAIT_PAY.getStatus();
    }

    public static boolean isNotWaitPay(Trade trade){
        return trade.getStatus()!= TradeStatus.TRADE_WAIT_PAY.getStatus();
    }

}
