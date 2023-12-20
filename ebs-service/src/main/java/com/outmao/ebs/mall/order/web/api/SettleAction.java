package com.outmao.ebs.mall.order.web.api;


import com.outmao.ebs.mall.order.dto.*;
import com.outmao.ebs.mall.order.entity.Order;
import com.outmao.ebs.mall.order.service.OrderService;
import com.outmao.ebs.mall.order.service.SettleService;
import com.outmao.ebs.mall.order.vo.OrderVO;
import com.outmao.ebs.mall.order.vo.QyPadToOrderVO;
import com.outmao.ebs.mall.order.vo.SettleVO;
import com.outmao.ebs.mall.order.vo.ToOrderVO;
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


@Api(value = "mall-order-settle", tags = "电商-订单-结算")
@RestController
@RequestMapping("/api/mall/order/settle")
public class SettleAction {


    @Autowired
    private SettleService settleService;


    @PreAuthorize("principal.id.equals(#request.userId)")
    @ApiOperation(value = "创建结算", notes = "创建结算")
    @PostMapping("/create")
    public SettleVO createSettle(@RequestBody CreateSettleDTO request){
        return settleService.createSettle(request);
    }

    @ApiOperation(value = "更新结算", notes = "更新结算")
    @PostMapping("/update")
    public SettleVO updateSettle(@RequestBody UpdateSettleDTO request){
        return settleService.updateSettle(request);
    }


    @ApiOperation(value = "下单", notes = "下单")
    @PostMapping("/buy")
    public ToOrderVO buy(@RequestBody ToOrderDTO request){
        return settleService.buy(request);
    }


    @ApiOperation(value = "迁眼平板下单支付", notes = "迁眼平板下单支付")
    @PostMapping("/qy/buy")
    public QyPadToOrderVO toOrderAndPay(QyPadToOrderDTO request){
        return settleService.toOrderAndPay(request);
    }


}
