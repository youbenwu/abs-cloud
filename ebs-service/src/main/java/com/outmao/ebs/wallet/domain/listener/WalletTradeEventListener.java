package com.outmao.ebs.wallet.domain.listener;

import com.google.common.eventbus.Subscribe;
import com.outmao.ebs.wallet.common.event.WalletTradeEvent;


public interface WalletTradeEventListener  {


    @Subscribe
    void onEvent(WalletTradeEvent event);


}
