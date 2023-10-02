package com.outmao.ebs.mall.order.domain;

import com.outmao.ebs.mall.order.dto.OrderLogisticsDTO;
import com.outmao.ebs.mall.order.dto.OrderLogisticsStatusDTO;
import com.outmao.ebs.mall.order.dto.OrderLogisticsStatusItemDTO;
import com.outmao.ebs.mall.order.entity.OrderLogistics;
import com.outmao.ebs.mall.order.entity.OrderLogisticsStatus;
import com.outmao.ebs.mall.order.entity.OrderLogisticsStatusItem;
import com.outmao.ebs.mall.order.vo.OrderLogisticsStatusVO;
import com.outmao.ebs.mall.order.vo.OrderLogisticsVO;
import java.util.Collection;
import java.util.List;

public interface OrderLogisticsDomain {

    public OrderLogistics saveOrderLogistics(OrderLogisticsDTO request);

    public void deleteOrderLogisticsById(Long id);

    public OrderLogisticsVO getOrderLogisticsVOById(Long id);

    public List<OrderLogisticsVO> getOrderLogisticsVOListByIdIn(Collection<Long> idIn);


    public OrderLogisticsStatus saveOrderLogisticsStatus(OrderLogisticsStatusDTO request);

    public List<OrderLogisticsStatusVO> getOrderLogisticsStatusVOListByLogisticsId(Long logisticsId);

    public OrderLogisticsStatusItem saveOrderLogisticsStatusItem(OrderLogisticsStatusItemDTO request);

    public List<OrderLogisticsStatusItem> getOrderLogisticsStatusItemListByLogisticsId(Long logisticsId);


}
