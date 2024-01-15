package com.outmao.ebs.mall.order.web.api;


import com.outmao.ebs.mall.order.common.constant.OrderSubStatus;
import com.outmao.ebs.mall.order.dto.*;
import com.outmao.ebs.mall.order.entity.Order;
import com.outmao.ebs.mall.order.service.OrderService;
import com.outmao.ebs.mall.order.vo.OrderVO;
import com.outmao.ebs.mall.order.vo.QyDeviceLeaseOrderVO;
import com.outmao.ebs.wallet.vo.TradeVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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


    @ApiOperation(value = "取消订单", notes = "取消订单")
    @PostMapping("/cancel")
    public void closeOrder(String orderNo){
        CloseOrderDTO dto=new CloseOrderDTO();
        dto.setOrderNo(orderNo);
        dto.setSubStatus(OrderSubStatus.CLOSED_BUYER.getStatus());
        orderService.closeOrder(dto);
    }

    @ApiOperation(value = "完成订单", notes = "完成订单")
    @PostMapping("/finish")
    public void finishOrder(String orderNo){
        FinishOrderDTO dto=new FinishOrderDTO();
        dto.setOrderNo(orderNo);
        dto.setSubStatus(OrderSubStatus.FINISHED_BUYER.getStatus());
        orderService.finishOrder(dto);
    }

    @ApiOperation(value = "订单绑定用户", notes = "订单绑定用户")
    @PostMapping("/bindOwner")
    public Order orderBindOwner(OrderBindOwnerDTO request){
        return orderService.orderBindOwner(request);
    }

    //@PostAuthorize("principal.id.equals(returnObject.userId)")
    @ApiOperation(value = "获取订单信息", notes = "获取订单信息")
    @PostMapping("/get")
    public OrderVO getOrderVOById(Long id) {
        return orderService.getOrderVOById(id);
    }


    @ApiOperation(value = "获取订单信息", notes = "获取订单信息")
    @PostMapping("/getByOrderNo")
    public OrderVO getOrderVOByOrderNo(String orderNo) {
        return orderService.getOrderVOByOrderNo(orderNo);
    }


    //@PreAuthorize("principal.id.equals(#request.userId)")
    @ApiOperation(value = "获取订单信息列表", notes = "获取订单信息列表")
    @PostMapping("/page")
    public Page<OrderVO> getOrderVOPage(GetOrderListDTO request, @PageableDefault(sort = {"createTime"}, direction = Sort.Direction.DESC)Pageable pageable) {
        return orderService.getOrderVOPage(request,pageable);
    }

    @ApiOperation(value = "创建交易", notes = "创建交易")
    @PostMapping("/tradeCreate")
    public TradeVO payPrepare(OrderPayPrepareDTO request){
        return orderService.payPrepare(request);
    }





}
