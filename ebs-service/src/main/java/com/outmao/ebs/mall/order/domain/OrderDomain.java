package com.outmao.ebs.mall.order.domain;

import com.outmao.ebs.mall.order.dto.*;
import com.outmao.ebs.mall.order.entity.Order;
import com.outmao.ebs.mall.order.vo.OrderVO;
import com.outmao.ebs.mall.order.vo.StatsOrderStatusVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderDomain {

    public Order saveOrder(OrderDTO request);

    public Order setOrderStatus(SetOrderStatusDTO request);

    public Order closeOrder(CloseOrderDTO request);

    public Order getOrderByOrderNo(String orderNo);

    public void deleteOrderById(Long id);

    public long getOrderCount();

    public double getOrderAmount();

    public OrderVO getOrderVOById(Long id);

    public OrderVO getOrderVOByOrderNo(String orderNo);

    public Page<OrderVO> getOrderVOPage(GetOrderListDTO request, Pageable pageable);

    public List<StatsOrderStatusVO> getStatsOrderStatusVOList(GetStatsOrderStatusListDTO request);


}
