package com.outmao.ebs.mall.order.web.admin.api;


import com.outmao.ebs.mall.order.vo.StatsOrderVO;
import com.outmao.ebs.org.common.annotation.AccessPermission;
import com.outmao.ebs.org.common.annotation.AccessPermissionGroup;
import com.outmao.ebs.org.common.annotation.AccessPermissionParent;
import com.outmao.ebs.mall.order.dto.GetOrderListDTO;
import com.outmao.ebs.mall.order.dto.OrderDTO;
import com.outmao.ebs.mall.order.dto.SetOrderStatusDTO;
import com.outmao.ebs.mall.order.entity.Order;
import com.outmao.ebs.mall.order.service.OrderService;
import com.outmao.ebs.mall.order.vo.OrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@AccessPermissionGroup(title="电商",url="/mall",name="",children = {
        @AccessPermissionParent(title = "订单管理",url = "/mall/order",name = "",children = {
                @AccessPermission(title = "保存订单信息",url = "/mall/order",name = "save"),
                @AccessPermission(title = "删除订单信息",url = "/mall/order",name = "delete"),
                @AccessPermission(title = "读取订单信息",url = "/mall/order",name = "read"),
                @AccessPermission(title = "设置订单状态",url = "/mall/order",name = "status"),
        }),
        @AccessPermissionParent(title = "订单合同管理",url = "/mall/order/contract",name = "",children = {
                @AccessPermission(title = "保存订单合同",url = "/mall/order/contract",name = "save"),
                @AccessPermission(title = "删除订单合同",url = "/mall/order/contract",name = "delete"),
                @AccessPermission(title = "读取订单合同",url = "/mall/order/contract",name = "read"),
        }),
})


@Api(value = "account-mall-order", tags = "后台-电商-订单")
@RestController
@RequestMapping("/api/admin/mall/order")
public class OrderAdminAction {


    @Autowired
    private OrderService orderService;


    @PreAuthorize("hasPermission('/mall/order','save')")
    @ApiOperation(value = "保存订单信息", notes = "保存订单信息")
    @PostMapping("/save")
    public Order saveOrder(@RequestBody OrderDTO request) {
        return orderService.saveOrder(request);
    }

    @PreAuthorize("hasPermission('/mall/order','status')")
    @ApiOperation(value = "设置订单状态", notes = "设置订单状态")
    @PostMapping("/setStatus")
    public Order setOrderStatus(SetOrderStatusDTO request) {
        return orderService.setOrderStatus(request);
    }

    @PreAuthorize("hasPermission('/mall/order','delete')")
    @ApiOperation(value = "删除订单信息", notes = "删除订单信息")
    @PostMapping("/delete")
    public void deleteOrderById(Long id) {
        orderService.deleteOrderById(id);
    }

    @ApiOperation(value = "获取订单数量", notes = "获取订单数量")
    @PostMapping("/count")
    public long getOrderCount(){
        return orderService.getOrderCount();
    }

    @ApiOperation(value = "获取订单总金额", notes = "获取订单总金额")
    @PostMapping("/amount")
    public double getOrderAmount(){
        return orderService.getOrderAmount();
    }

    @PreAuthorize("hasPermission('/mall/order','read')")
    @ApiOperation(value = "获取订单信息", notes = "获取订单信息")
    @PostMapping("/get")
    public OrderVO getOrderVOById(Long id) {
        return orderService.getOrderVOById(id);
    }

    @PreAuthorize("hasPermission('/mall/order','read')")
    @ApiOperation(value = "获取订单信息列表", notes = "获取订单信息列表")
    @PostMapping("/page")
    public Page<OrderVO> getOrderVOPage(GetOrderListDTO request, Pageable pageable) {
        return orderService.getOrderVOPage(request,pageable);
    }

    @PreAuthorize("hasPermission('/mall/order','read')")
    @ApiOperation(value = "获取订单数量和金额按天统计", notes = "获取订单数量和金额按天统计")
    @PostMapping("/statsOrder/days")
    public List<StatsOrderVO> getStatsOrderVOListByDays(Date fromTime, Date toTime) {
        return orderService.getStatsOrderVOListByDays(fromTime,toTime);
    }


}
