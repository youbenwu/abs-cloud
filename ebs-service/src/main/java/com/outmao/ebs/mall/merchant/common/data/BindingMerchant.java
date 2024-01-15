package com.outmao.ebs.mall.merchant.common.data;

import com.outmao.ebs.common.vo.Contact;
import com.outmao.ebs.common.vo.Itemable;

public interface BindingMerchant extends Itemable {

    public Long getUserId();
    public Contact getContact();
    public Long getMerchantId();


    public void setMerchantId(Long merchantId);
    public void setShopId(Long shopId);
    public void setWalletId(Long walletId);
    public void setOrgId(Long orgId);


}
