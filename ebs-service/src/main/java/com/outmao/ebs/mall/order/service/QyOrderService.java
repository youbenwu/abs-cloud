package com.outmao.ebs.mall.order.service;

import com.outmao.ebs.mall.order.dto.*;
import com.outmao.ebs.mall.order.entity.Order;
import com.outmao.ebs.mall.order.vo.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface QyOrderService {




    public QyDeviceLeaseOrderVO getQyDeviceLeaseOrderVOById(Long id);

    public Page<QyDeviceLeaseOrderVO> getQyDeviceLeaseOrderVOPage(GetOrderListDTO request, Pageable pageable);


    public QyAdvertOrderVO getQyAdvertOrderVOById(Long id);

    public Page<QyAdvertOrderVO> getQyAdvertOrderVOPage(GetOrderListDTO request, Pageable pageable);



}
