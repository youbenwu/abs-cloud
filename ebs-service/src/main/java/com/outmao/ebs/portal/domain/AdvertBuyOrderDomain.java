package com.outmao.ebs.portal.domain;

import com.outmao.ebs.portal.dto.AdvertBuyOrderDTO;
import com.outmao.ebs.portal.dto.SetAdvertBuyOrderStatusDTO;
import com.outmao.ebs.portal.entity.AdvertBuyOrder;

public interface AdvertBuyOrderDomain {

    public AdvertBuyOrder saveAdvertBuyOrder(AdvertBuyOrderDTO request);

    public AdvertBuyOrder setAdvertBuyOrderStatus(SetAdvertBuyOrderStatusDTO request);



}
