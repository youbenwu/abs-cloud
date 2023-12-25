package com.outmao.ebs.mall.order.common.event;


import com.outmao.ebs.common.services.eventBus.ActionEvent;
import com.outmao.ebs.common.services.eventBus.MethodEvent;
import com.outmao.ebs.common.util.ServletRequestUtil;
import com.outmao.ebs.wallet.common.constant.WalletConstant;
import lombok.Data;

/**
*
* 交易事件
*
* */
@Data
public class OrderStatusChangeEvent extends ActionEvent {

    private String actionKey;


    @Override
    public void setValue(MethodEvent methodEvent) {
        actionKey= (String) ServletRequestUtil.getAttribute(WalletConstant.action_key);
    }


    @Override
    public boolean async() {
        return super.async();
    }


}
