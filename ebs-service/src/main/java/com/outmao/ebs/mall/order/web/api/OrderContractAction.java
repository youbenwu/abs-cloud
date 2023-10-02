package com.outmao.ebs.mall.order.web.api;


import com.outmao.ebs.mall.order.dto.GetOrderContractListDTO;
import com.outmao.ebs.mall.order.dto.OrderContractDTO;
import com.outmao.ebs.mall.order.service.OrderContractService;
import com.outmao.ebs.mall.order.vo.OrderContractVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@Api(value = "mall-order-contract", tags = "电商-订单-合同")
@RestController
@RequestMapping("/api/mall/order/contract")
public class OrderContractAction {


    @Autowired
    private OrderContractService orderContractService;



    @ApiOperation(value = "保存订单合同", notes = "保存订单合同")
    @PostMapping("/save")
    public void saveOrderContract(OrderContractDTO request){
        orderContractService.saveOrderContract(request);
    }


    @ApiOperation(value = "删除订单合同", notes = "删除订单合同")
    @PostMapping("/delete")
    public void deleteOrderContractById(Long id){
        orderContractService.deleteOrderContractById(id);
    }


    @ApiOperation(value = "获取订单合同", notes = "获取订单合同")
    @PostMapping("/get")
    public OrderContractVO getOrderContractVOById(Long id){
        return orderContractService.getOrderContractVOById(id);
    }


    @ApiOperation(value = "获取订单合同列表", notes = "获取订单合同列表")
    @PostMapping("/list")
    public List<OrderContractVO> getOrderContractVOListByOrderId(Long orderId){
        return orderContractService.getOrderContractVOListByOrderId(orderId);
    }

    @ApiOperation(value = "获取订单合同列表", notes = "获取订单合同列表")
    @PostMapping("/page")
    public Page<OrderContractVO> getOrderContractVOPage(GetOrderContractListDTO request, Pageable pageable){
        return getOrderContractVOPage(request,pageable);
    }


}
