package com.outmao.ebs.jnet.action.client;


import com.outmao.ebs.jnet.dto.order.OrderParamsDTO;
import com.outmao.ebs.jnet.entity.order.Order;
import com.outmao.ebs.jnet.service.order.ZOrderService;
import com.outmao.ebs.jnet.vo.order.TaskVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(value = "/order", tags = "织网-订单模块接口")
@RestController("ZOrderAction")
@RequestMapping("/api/z/order")
public class OrderAction {

	@Autowired
	ZOrderService zOrderService;


	@ApiOperation(value = "编辑订单接口", notes = "编辑订单接口")
	@PostMapping(value = "/save")
	public Order saveOrder(@RequestBody OrderParamsDTO params) {
		return zOrderService.saveOrder(params);
	}


	@ApiOperation(value = "任务列表接口、工单列表接口", notes = "任务列表接口type 0--当前任务 1--历史任务")
	@PostMapping(value = "/task/page")
	public Page<TaskVO> getTaskVOPage(Integer type, Long userId, Pageable pageable) {
		return zOrderService.getTaskVOPage(type,userId,pageable);
	}

}
