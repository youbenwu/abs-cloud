package com.outmao.ebs.jnet.dao.order;

import com.outmao.ebs.jnet.entity.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component("ZOrderDao")
public interface OrderDao extends JpaRepository<Order,Long> {



}
