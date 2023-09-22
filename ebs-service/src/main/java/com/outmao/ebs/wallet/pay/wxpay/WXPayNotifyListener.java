package com.outmao.ebs.wallet.pay.wxpay;

import java.util.Map;

public interface WXPayNotifyListener {

    public void notify(Map<String, String> requestMap);

    public void notifyRefund(Map<String, String> requestMap);


}
