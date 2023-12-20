package com.outmao.ebs.mall.order.service;

import com.outmao.ebs.mall.order.dto.*;
import com.outmao.ebs.mall.order.entity.Order;
import com.outmao.ebs.mall.order.vo.QyDeviceLeaseOrderVO;
import com.outmao.ebs.mall.order.vo.StatsOrderStatusVO;
import com.outmao.ebs.mall.order.vo.StatsOrderVO;
import com.outmao.ebs.mall.order.vo.OrderVO;
import com.outmao.ebs.wallet.vo.TradeVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface OrderService {


    public Order saveOrder(OrderDTO request);

    public Order setOrderStatus(SetOrderStatusDTO request);

    public Order closeOrder(CloseOrderDTO request);

    public void deleteOrderById(Long id);

    public long getOrderCount();

    public double getOrderAmount();

    public OrderVO getOrderVOById(Long id);

    public OrderVO getOrderVOByOrderNo(String orderNo);

    public Page<OrderVO> getOrderVOPage(GetOrderListDTO request, Pageable pageable);

    public List<StatsOrderVO> getStatsOrderVOListByDays(Date fromTime, Date toTime);

    public List<StatsOrderVO> getStatsOrderVOListByUserIdIn(Collection<Long> userIdIn);

    public TradeVO payPrepare(OrderPayPrepareDTO request);

    public List<StatsOrderStatusVO> getStatsOrderStatusVOList(GetStatsOrderStatusListDTO request);


    public QyDeviceLeaseOrderVO getQyDeviceLeaseOrderVOById(Long id);

    public Page<QyDeviceLeaseOrderVO> getQyDeviceLeaseOrderVOPage(GetOrderListDTO request, Pageable pageable);



}
