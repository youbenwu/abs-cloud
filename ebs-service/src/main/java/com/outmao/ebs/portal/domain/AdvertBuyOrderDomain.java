package com.outmao.ebs.portal.domain;

import com.outmao.ebs.portal.dto.AdvertBuyOrderDTO;
import com.outmao.ebs.portal.dto.SetAdvertBuyOrderStatusDTO;
import com.outmao.ebs.portal.entity.AdvertBuyOrder;

import java.util.Collection;
import java.util.List;

public interface AdvertBuyOrderDomain {

    public AdvertBuyOrder saveAdvertBuyOrder(AdvertBuyOrderDTO request);

    public AdvertBuyOrder setAdvertBuyOrderStatus(SetAdvertBuyOrderStatusDTO request);

    public List<Long> getAdvertIdListByOrderNoIn(Collection<String> orderNoIn);

    public List<AdvertBuyOrder> getAdvertBuyOrderListByOrderNoIn(Collection<String> orderNoIn);

}
