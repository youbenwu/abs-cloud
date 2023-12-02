package com.outmao.ebs.jnet.service.order;

import com.outmao.ebs.jnet.dto.order.OrderParamsDTO;
import com.outmao.ebs.jnet.entity.order.Order;
import com.outmao.ebs.jnet.vo.order.TaskVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ZOrderService {

    public Order saveOrder(OrderParamsDTO params);





    //type 0--当前任务 1--历史任务
    public Page<TaskVO> getTaskVOPage(Integer type, Long userId, Pageable pageable);


}
