package com.outmao.ebs.mall.order.web.api;


import com.outmao.ebs.mall.order.dto.GetOrderListDTO;
import com.outmao.ebs.mall.order.dto.OrderDTO;
import com.outmao.ebs.mall.order.entity.Order;
import com.outmao.ebs.mall.order.service.OrderService;
import com.outmao.ebs.mall.order.vo.OrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@Api(value = "mall-order", tags = "电商-订单")
@RestController
@RequestMapping("/api/mall/order")
public class OrderAction {


    @Autowired
    private OrderService orderService;


    @ApiOperation(value = "保存订单信息", notes = "保存订单信息")
    @PostMapping("/save")
    public Order saveOrder(@RequestBody OrderDTO request) {
        return orderService.saveOrder(request);
    }


    //@PostAuthorize("principal.id.equals(returnObject.userId)")
    @ApiOperation(value = "获取订单信息", notes = "获取订单信息")
    @PostMapping("/get")
    public OrderVO getOrderVOById(Long id) {
        return orderService.getOrderVOById(id);
    }


    //@PreAuthorize("principal.id.equals(#request.userId)")
    @ApiOperation(value = "获取订单信息列表", notes = "获取订单信息列表")
    @PostMapping("/page")
    public Page<OrderVO> getOrderVOPage(GetOrderListDTO request, Pageable pageable) {
        return orderService.getOrderVOPage(request,pageable);
    }



}
