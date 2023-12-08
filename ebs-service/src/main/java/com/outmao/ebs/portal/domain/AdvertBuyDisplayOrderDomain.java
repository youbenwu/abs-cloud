package com.outmao.ebs.portal.domain;

import com.outmao.ebs.portal.dto.AdvertBuyDisplayOrderDTO;
import com.outmao.ebs.portal.dto.SetAdvertBuyDisplayOrderStatusDTO;
import com.outmao.ebs.portal.entity.AdvertBuyDisplayOrder;

public interface AdvertBuyDisplayOrderDomain {

    public AdvertBuyDisplayOrder saveAdvertBuyDisplayOrder(AdvertBuyDisplayOrderDTO request);

    public AdvertBuyDisplayOrder setAdvertBuyDisplayOrderStatus(SetAdvertBuyDisplayOrderStatusDTO request);




}
