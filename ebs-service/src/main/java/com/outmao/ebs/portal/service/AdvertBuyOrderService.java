package com.outmao.ebs.portal.service;

import com.outmao.ebs.mall.order.vo.SettleVO;
import com.outmao.ebs.portal.dto.AdvertBuyOrderDTO;
import com.outmao.ebs.portal.dto.AdvertOrderDTO;
import com.outmao.ebs.portal.dto.AdvertOrderSettleDTO;
import com.outmao.ebs.portal.dto.SetAdvertBuyOrderStatusDTO;
import com.outmao.ebs.portal.entity.AdvertBuyOrder;
import com.outmao.ebs.portal.vo.AdvertVO;

import java.util.Collection;
import java.util.List;

public interface AdvertBuyOrderService {

    public AdvertBuyOrder saveAdvertBuyOrder(AdvertBuyOrderDTO request);

    public AdvertBuyOrder setAdvertBuyOrderStatus(SetAdvertBuyOrderStatusDTO request);


    public AdvertBuyOrder saveAdvertOrder(AdvertOrderDTO request);

    public SettleVO settleAdvertOrder(AdvertOrderSettleDTO request);


    public List<AdvertVO> getAdvertVOListByOrderNoIn(Collection<String> orderNoIn);


}
