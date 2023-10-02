package com.outmao.ebs.mall.order.dao;

import com.outmao.ebs.mall.order.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderProductDao extends JpaRepository<OrderProduct,Long> {

    public void deleteAllByOrderId(Long orderId);

    public List<OrderProduct> findAllByOrderId(Long orderId);

}
