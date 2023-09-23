package com.outmao.ebs.wallet.common.event;


import com.outmao.ebs.common.services.eventBus.ActionEvent;
import com.outmao.ebs.common.services.eventBus.MethodEvent;
import com.outmao.ebs.wallet.entity.Trade;
import lombok.Data;

/**
*
* 交易事件
*
* */
@Data
public class WalletTradeEvent extends ActionEvent {

    private Trade trade;

    @Override
    public void setValue(MethodEvent methodEvent) {
        trade=(Trade) methodEvent.getReturning();
    }


    @Override
    public boolean async() {
        return super.async();
    }


}
