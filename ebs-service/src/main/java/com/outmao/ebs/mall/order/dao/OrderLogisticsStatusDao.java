package com.outmao.ebs.mall.order.dao;

import com.outmao.ebs.mall.order.entity.OrderLogisticsStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderLogisticsStatusDao extends JpaRepository<OrderLogisticsStatus,Long> {

    public void deleteAllByLogisticsId(Long logisticsId);

    public List<OrderLogisticsStatus> findAllByLogisticsId(Long logisticsId);

}
