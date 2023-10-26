package com.outmao.ebs.mall.order.domain;

import com.outmao.ebs.mall.order.dto.GetOrderListDTO;
import com.outmao.ebs.mall.order.dto.OrderDTO;
import com.outmao.ebs.mall.order.dto.SetOrderStatusDTO;
import com.outmao.ebs.mall.order.entity.Order;
import com.outmao.ebs.mall.order.vo.OrderVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderDomain {

    public Order saveOrder(OrderDTO request);

    public Order setOrderStatus(SetOrderStatusDTO request);

    public Order getOrderByOrderNo(String orderNo);

    public void deleteOrderById(Long id);

    public long getOrderCount();

    public double getOrderAmount();

    public OrderVO getOrderVOById(Long id);

    public OrderVO getOrderVOByOrderNo(String orderNo);

    public Page<OrderVO> getOrderVOPage(GetOrderListDTO request, Pageable pageable);



}
