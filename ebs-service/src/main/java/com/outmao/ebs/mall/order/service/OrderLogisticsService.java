package com.outmao.ebs.mall.order.service;

import com.outmao.ebs.mall.order.dto.OrderLogisticsDTO;
import com.outmao.ebs.mall.order.dto.OrderLogisticsStatusDTO;
import com.outmao.ebs.mall.order.dto.OrderLogisticsStatusItemDTO;
import com.outmao.ebs.mall.order.entity.OrderLogistics;
import com.outmao.ebs.mall.order.entity.OrderLogisticsStatus;
import com.outmao.ebs.mall.order.entity.OrderLogisticsStatusItem;
import com.outmao.ebs.mall.order.vo.OrderLogisticsStatusVO;
import com.outmao.ebs.mall.order.vo.OrderLogisticsVO;

import java.util.Collection;
import java.util.List;

public interface OrderLogisticsService {

    public OrderLogistics saveOrderLogistics(OrderLogisticsDTO request);

    public OrderLogisticsVO getOrderLogisticsVOById(Long id);

    public OrderLogisticsStatus saveOrderLogisticsStatus(OrderLogisticsStatusDTO request);

    public OrderLogisticsStatusItem saveOrderLogisticsStatusItem(OrderLogisticsStatusItemDTO request);


}
