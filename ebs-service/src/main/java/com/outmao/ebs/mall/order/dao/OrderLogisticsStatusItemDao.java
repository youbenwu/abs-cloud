package com.outmao.ebs.mall.order.dao;

import com.outmao.ebs.mall.order.entity.OrderLogisticsStatusItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderLogisticsStatusItemDao extends JpaRepository<OrderLogisticsStatusItem,Long> {

    public void deleteAllByLogisticsId(Long logisticsId);

    public List<OrderLogisticsStatusItem> findAllByLogisticsId(Long logisticsId);


}
