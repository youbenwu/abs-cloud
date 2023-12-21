package com.outmao.ebs.mall.order.web.api;


import com.outmao.ebs.mall.order.dto.GetOrderListDTO;
import com.outmao.ebs.mall.order.service.QyOrderService;
import com.outmao.ebs.mall.order.vo.QyAdvertOrderVO;
import com.outmao.ebs.mall.order.vo.QyDeviceLeaseOrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(value = "mall-order-qy", tags = "电商-订单-迁眼")
@RestController
@RequestMapping("/api/mall/order")
public class QyOrderAction {


    @Autowired
    private QyOrderService qyOrderService;



    @ApiOperation(value = "获取迁眼设备租赁订单信息", notes = "获取迁眼设备租赁订单信息")
    @PostMapping("/qy_device_lease/get")
    public QyDeviceLeaseOrderVO getQyDeviceLeaseOrderVOById(Long id){
        return qyOrderService.getQyDeviceLeaseOrderVOById(id);
    }

    @ApiOperation(value = "获取迁眼设备租赁订单信息列表", notes = "获取迁眼设备租赁订单信息列表")
    @PostMapping("/qy_device_lease/page")
    public Page<QyDeviceLeaseOrderVO> getQyDeviceLeaseOrderVOPage(GetOrderListDTO request,@PageableDefault(sort = {"createTime"}, direction = Sort.Direction.DESC) Pageable pageable){
        return qyOrderService.getQyDeviceLeaseOrderVOPage(request,pageable);
    }

    @ApiOperation(value = "获取迁眼广告投放订单信息", notes = "获取迁眼广告投放订单信息")
    @PostMapping("/qy_advert_buy/get")
    public QyAdvertOrderVO getQyAdvertOrderVOById(Long id){
        return qyOrderService.getQyAdvertOrderVOById(id);
    }

    @ApiOperation(value = "获取迁眼广告投放订单信息列表", notes = "获取迁眼广告投放订单信息列表")
    @PostMapping("/qy_advert_buy/page")
    public Page<QyAdvertOrderVO> getQyAdvertOrderVOPage(GetOrderListDTO request,@PageableDefault(sort = {"createTime"}, direction = Sort.Direction.DESC) Pageable pageable){
        return qyOrderService.getQyAdvertOrderVOPage(request,pageable);
    }



}
