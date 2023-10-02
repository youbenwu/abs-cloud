package com.outmao.ebs.mall.order.dao;

import com.outmao.ebs.mall.order.entity.OrderContract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderContractDao extends JpaRepository<OrderContract,Long> {

    public void deleteAllByOrderId(Long orderId);

}
