package com.outmao.ebs.mall.order.dao;

import com.outmao.ebs.mall.order.entity.OrderLogistics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLogisticsDao extends JpaRepository<OrderLogistics,Long> {

    public void deleteAllByOrderId(Long orderId);}
