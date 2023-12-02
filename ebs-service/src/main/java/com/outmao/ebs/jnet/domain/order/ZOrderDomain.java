package com.outmao.ebs.jnet.domain.order;

import com.outmao.ebs.jnet.dto.order.OrderParamsDTO;
import com.outmao.ebs.jnet.entity.order.Order;
import com.outmao.ebs.jnet.entity.order.Task;
import com.outmao.ebs.jnet.vo.order.OrderVO;
import com.outmao.ebs.jnet.vo.order.TaskVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;


public interface ZOrderDomain {

    //编辑订单
    public Order saveOrder(OrderParamsDTO params);

    public Order saveOrder(Order order);

    //修改订单状态
    public Order updateOrderStatus(Long orderId, int status, int subStatus);

    //更新任务状态
    public Task updateTaskByOrder(Order order);


    public List<OrderVO> getOrderVOListByIdIn(Collection<Long> ids);


    //type 0--当前任务 1--历史任务
    public Page<TaskVO> getTaskVOPage(Integer type, Long userId, Pageable pageable);



}
