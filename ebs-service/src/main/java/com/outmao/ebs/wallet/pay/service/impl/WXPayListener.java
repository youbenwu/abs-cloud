package com.outmao.ebs.wallet.pay.service.impl;

import com.outmao.ebs.wallet.pay.wxpay.WXPayNotifyListener;
import com.outmao.ebs.wallet.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
public class WXPayListener implements WXPayNotifyListener {

    @Autowired
    private TradeService tradeService;

    @Override
    public void notify(Map<String, String> data) {
        String return_code = data.get("return_code");
        if (!return_code.equals("SUCCESS"))
            return;
        String result_code = data.get("result_code");
        String out_trade_no = data.get("out_trade_no");
        if (result_code.equals("SUCCESS")) {
            //tradeService.tradePay(out_trade_no);
        } else if (result_code.equals("FAIL")) {
            //tradeService.tradeClose(out_trade_no);
        }

    }

    @Override
    public void notifyRefund(Map<String, String> requestMap) {

    }


}
