package com.outmao.ebs.wallet.common.data;


import com.outmao.ebs.common.vo.Itemable;

public interface BindingWallet extends Itemable {

    public Long getUserId();

    public Long getWalletId();

    public void setWalletId(Long walletId);


}
