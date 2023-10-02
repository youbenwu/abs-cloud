package com.outmao.ebs.mall.order.service.impl;

import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.mall.order.domain.OrderDomain;
import com.outmao.ebs.mall.order.domain.OrderStatsDomain;
import com.outmao.ebs.mall.order.dto.GetOrderListDTO;
import com.outmao.ebs.mall.order.dto.OrderDTO;
import com.outmao.ebs.mall.order.dto.SetOrderStatusDTO;
import com.outmao.ebs.mall.order.entity.Order;
import com.outmao.ebs.mall.order.service.OrderService;
import com.outmao.ebs.mall.order.vo.StatsOrderVO;
import com.outmao.ebs.mall.order.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;
import java.util.List;


@Service
public class OrderServiceImpl extends BaseService implements OrderService {

    @Autowired
    private OrderDomain orderDomain;

    @Autowired
    private OrderStatsDomain orderStatsDomain;


    @Transactional
    @Override
    public Order saveOrder(OrderDTO request) {
        return orderDomain.saveOrder(request);
    }

    @Override
    public Order setOrderStatus(SetOrderStatusDTO request) {
        return orderDomain.setOrderStatus(request);
    }

    @Override
    public void deleteOrderById(Long id) {
        orderDomain.deleteOrderById(id);
    }

    @Override
    public OrderVO getOrderVOById(Long id) {
        return orderDomain.getOrderVOById(id);
    }

    @Override
    public Page<OrderVO> getOrderVOPage(GetOrderListDTO request, Pageable pageable) {
        return orderDomain.getOrderVOPage(request,pageable);
    }

    @Override
    public long getOrderCount() {
        return orderDomain.getOrderCount();
    }

    @Override
    public double getOrderAmount() {
        return orderDomain.getOrderAmount();
    }

    @Override
    public List<StatsOrderVO> getStatsOrderVOListByDays(Date fromTime, Date toTime) {
        return orderStatsDomain.getStatsOrderVOListByDays(fromTime,toTime);
    }

    @Override
    public List<StatsOrderVO> getStatsOrderVOListByUserIdIn(Collection<Long> userIdIn) {
        return orderStatsDomain.getStatsOrderVOListByUserIdIn(userIdIn);
    }


}
