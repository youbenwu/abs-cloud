package com.outmao.ebs.mall.order.web.admin.api;


import com.outmao.ebs.mall.order.dto.OrderLogisticsDTO;
import com.outmao.ebs.mall.order.dto.OrderLogisticsStatusDTO;
import com.outmao.ebs.mall.order.dto.OrderLogisticsStatusItemDTO;
import com.outmao.ebs.mall.order.service.OrderLogisticsService;
import com.outmao.ebs.mall.order.vo.OrderLogisticsVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "account-mall-order-logistics", tags = "后台-电商-订单-物流")
@RestController
@RequestMapping("/api/admin/mall/order/logistics")
public class OrderLogisticsAdminAction {


    @Autowired
    private OrderLogisticsService orderLogisticsService;

    @PreAuthorize("hasPermission('/mall/order','save')")
    @ApiOperation(value = "保存订单物流信息", notes = "保存订单物流信息")
    @PostMapping("/save")
    public void saveOrderLogistics(OrderLogisticsDTO request){
        orderLogisticsService.saveOrderLogistics(request);
    }

    @PreAuthorize("hasPermission('/mall/order','read')")
    @ApiOperation(value = "获取订单物流信息", notes = "获取订单物流信息")
    @PostMapping("/get")
    public OrderLogisticsVO getOrderLogisticsVOById(Long id){
        return orderLogisticsService.getOrderLogisticsVOById(id);
    }

    @PreAuthorize("hasPermission('/mall/order','save')")
    @ApiOperation(value = "保存订单物流状态信息", notes = "保存订单物流状态信息")
    @PostMapping("/status/save")
    public void saveOrderLogisticsStatus(OrderLogisticsStatusDTO request){
        orderLogisticsService.saveOrderLogisticsStatus(request);
    }

    @PreAuthorize("hasPermission('/mall/order','save')")
    @ApiOperation(value = "保存订单物流状态跟踪信息", notes = "保存订单物流状态跟踪信息")
    @PostMapping("/status/item/save")
    public void saveOrderLogisticsStatusItem(OrderLogisticsStatusItemDTO request){
        orderLogisticsService.saveOrderLogisticsStatusItem(request);
    }


}
