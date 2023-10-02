package com.outmao.ebs.mall.order.service.impl;

import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.mall.order.domain.OrderLogisticsDomain;
import com.outmao.ebs.mall.order.dto.OrderLogisticsDTO;
import com.outmao.ebs.mall.order.dto.OrderLogisticsStatusDTO;
import com.outmao.ebs.mall.order.dto.OrderLogisticsStatusItemDTO;
import com.outmao.ebs.mall.order.entity.OrderLogistics;
import com.outmao.ebs.mall.order.entity.OrderLogisticsStatus;
import com.outmao.ebs.mall.order.entity.OrderLogisticsStatusItem;
import com.outmao.ebs.mall.order.service.OrderLogisticsService;
import com.outmao.ebs.mall.order.vo.OrderLogisticsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class OrderLogisticsServiceImpl extends BaseService implements OrderLogisticsService {

    @Autowired
    private OrderLogisticsDomain orderLogisticsDomain;

    @Override
    public OrderLogistics saveOrderLogistics(OrderLogisticsDTO request) {
        return orderLogisticsDomain.saveOrderLogistics(request);
    }


    @Override
    public OrderLogisticsVO getOrderLogisticsVOById(Long id) {
        return orderLogisticsDomain.getOrderLogisticsVOById(id);
    }


    @Override
    public OrderLogisticsStatus saveOrderLogisticsStatus(OrderLogisticsStatusDTO request) {
        return orderLogisticsDomain.saveOrderLogisticsStatus(request);
    }


    @Override
    public OrderLogisticsStatusItem saveOrderLogisticsStatusItem(OrderLogisticsStatusItemDTO request) {
        return orderLogisticsDomain.saveOrderLogisticsStatusItem(request);
    }


}
