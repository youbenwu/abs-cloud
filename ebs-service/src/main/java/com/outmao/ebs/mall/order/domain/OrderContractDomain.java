package com.outmao.ebs.mall.order.domain;

import com.outmao.ebs.mall.order.dto.GetOrderContractListDTO;
import com.outmao.ebs.mall.order.dto.OrderContractDTO;
import com.outmao.ebs.mall.order.entity.OrderContract;
import com.outmao.ebs.mall.order.vo.OrderContractVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderContractDomain {

    public OrderContract saveOrderContract(OrderContractDTO request);

    public void deleteOrderContractById(Long id);

    public void deleteOrderContractListByOrderId(Long orderId);

    public OrderContractVO getOrderContractVOById(Long id);

    public List<OrderContractVO> getOrderContractVOListByOrderId(Long orderId);

    public Page<OrderContractVO> getOrderContractVOPage(GetOrderContractListDTO request, Pageable pageable);

}
