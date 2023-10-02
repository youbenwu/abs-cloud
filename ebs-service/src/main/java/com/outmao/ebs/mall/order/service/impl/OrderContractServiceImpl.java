package com.outmao.ebs.mall.order.service.impl;

import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.mall.order.domain.OrderContractDomain;
import com.outmao.ebs.mall.order.dto.GetOrderContractListDTO;
import com.outmao.ebs.mall.order.dto.OrderContractDTO;
import com.outmao.ebs.mall.order.entity.OrderContract;
import com.outmao.ebs.mall.order.service.OrderContractService;
import com.outmao.ebs.mall.order.vo.OrderContractVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderContractServiceImpl extends BaseService implements OrderContractService {


    @Autowired
    private OrderContractDomain orderContractDomain;


    @Override
    public OrderContract saveOrderContract(OrderContractDTO request) {
        return orderContractDomain.saveOrderContract(request);
    }

    @Override
    public void deleteOrderContractById(Long id) {
        orderContractDomain.deleteOrderContractById(id);
    }

    @Override
    public OrderContractVO getOrderContractVOById(Long id) {
        return orderContractDomain.getOrderContractVOById(id);
    }

    @Override
    public List<OrderContractVO> getOrderContractVOListByOrderId(Long orderId) {
        return orderContractDomain.getOrderContractVOListByOrderId(orderId);
    }

    @Override
    public Page<OrderContractVO> getOrderContractVOPage(GetOrderContractListDTO request, Pageable pageable) {
        return orderContractDomain.getOrderContractVOPage(request,pageable);
    }
}
