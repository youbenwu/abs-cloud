package com.outmao.ebs.portal.service;

import com.outmao.ebs.mall.order.vo.SettleVO;
import com.outmao.ebs.portal.dto.*;
import com.outmao.ebs.portal.entity.AdvertBuyDisplayOrder;

public interface AdvertBuyDisplayOrderService {

    public AdvertBuyDisplayOrder saveAdvertBuyDisplayOrder(AdvertBuyDisplayOrderDTO request);

    public AdvertBuyDisplayOrder setAdvertBuyDisplayOrderStatus(SetAdvertBuyDisplayOrderStatusDTO request);


    public AdvertBuyDisplayOrder saveAdvertOrder(AdvertOrderDTO request);

    public SettleVO settleAdvertOrder(AdvertOrderSettleDTO request);


}
