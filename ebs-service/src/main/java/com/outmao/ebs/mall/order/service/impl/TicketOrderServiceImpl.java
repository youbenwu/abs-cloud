package com.outmao.ebs.mall.order.service.impl;

import com.outmao.ebs.clients.shpanhe.service.ShpanheTicketService;
import com.outmao.ebs.clients.shpanhe.vo.CreateOrderResponse;
import com.outmao.ebs.clients.shpanhe.vo.OrderDetail;
import com.outmao.ebs.clients.shpanhe.vo.PanheResponse;
import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.mall.order.common.constant.OrderStatus;
import com.outmao.ebs.mall.order.domain.TicketOrderDomain;
import com.outmao.ebs.mall.order.dto.*;
import com.outmao.ebs.mall.order.entity.TicketOrder;
import com.outmao.ebs.mall.order.service.TicketOrderService;
import com.outmao.ebs.wallet.common.constant.PayChannel;
import com.outmao.ebs.wallet.common.constant.TradeStatus;
import com.outmao.ebs.wallet.common.constant.TradeType;
import com.outmao.ebs.wallet.common.constant.WalletConstant;
import com.outmao.ebs.wallet.common.listener.TradeStatusListener;
import com.outmao.ebs.wallet.dto.TradePrepareDTO;
import com.outmao.ebs.wallet.entity.Trade;
import com.outmao.ebs.wallet.pay.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class TicketOrderServiceImpl extends BaseService implements TicketOrderService , CommandLineRunner, TradeStatusListener {


    @Autowired
    private TicketOrderDomain ticketOrderDomain;

    @Autowired
    private ShpanheTicketService shpanheTicketService;


    @Autowired
    private PayService payService;


    @Override
    public void run(String... args) throws Exception {
        payService.addStatusListener(this);
    }

    @Transactional
    @Override
    public TicketOrder createTicketOrder(CreateTicketOrderDTO request) {

        //用户下单
        //先去OTA平台下单
        CreateOrderResponse response=shpanheTicketService.createOrder(request);

        if(!response.isSuccess()){
            log.error("OTA平台下单失败",response.getMsg());
            throw  new BusinessException(response.getMsg());
        }


        //获取OTA平台订单详情
        PanheResponse<OrderDetail> orderDetailResponse =shpanheTicketService.getOrderDetail(response.getData().getOrderNo());

        if(!orderDetailResponse.isSuccess()){
            log.error("OTA平台获取订单失败",orderDetailResponse.getMsg());
            throw new BusinessException(orderDetailResponse.getMsg());
        }

        OrderDetail orderDetail=orderDetailResponse.getData();


        TicketOrderDTO orderDTO=new TicketOrderDTO();
        orderDTO.setUserId(request.getUserId());
        orderDTO.setOrderNo(orderDetail.getOrderNo());
        orderDTO.setScenicId(orderDetail.getScenicID());
        orderDTO.setScenicName(orderDetail.getScenicName());
        orderDTO.setProductId(orderDetail.getProductID());
        orderDTO.setProductName(orderDetail.getProductName());
        orderDTO.setQuantity(orderDetail.getQuantity());
        orderDTO.setAmount(orderDetail.getOrderAmount());
        orderDTO.setName(orderDetail.getContact().getName());
        orderDTO.setPhone(orderDetail.getContact().getMobile());


        TicketOrder order=saveTicketOrder(orderDTO);


        TradePrepareDTO dto=new TradePrepareDTO();
        dto.setTradeNo(order.getOrderNo());
        dto.setFromId(request.getWalletId());
        dto.setCurrencyId("RMB");
        dto.setAmount((long) (order.getAmount()*100));
        dto.setRemark("门票");
        dto.setType(TradeType.Pay.getType());
        dto.setBusinessType(WalletConstant.business_type_ticket_pay);
        dto.setBusiness("门票支付");
        dto.setPayChannel(PayChannel.WalletPay.getType());

        payService.tradePrepare(dto);


        return order;
    }


    @Override
    public TicketOrder saveTicketOrder(TicketOrderDTO request) {

        return ticketOrderDomain.saveTicketOrder(request);
    }



    @Override
    public TicketOrder getTicketOrderByOrderNo(String orderNo) {
        return ticketOrderDomain.getTicketOrderByOrderNo(orderNo);
    }


    @Transactional
    @Override
    public TicketOrder setTicketOrderStatus(SetTicketOrderStatusDTO request) {
        TicketOrder order= ticketOrderDomain.setTicketOrderStatus(request);
        if(order.getStatus()==OrderStatus.SUCCESSED.getStatus()){
            //支付成功去OTA平台支付

        }
        return order;
    }



    @Override
    public TicketOrder setTicketOrderOutStatus(SetTicketOrderOutStatusDTO request) {
        return ticketOrderDomain.setTicketOrderOutStatus(request);
    }



    @Override
    public Page<TicketOrder> getTicketOrderPage(GetTicketOrderListDTO request, Pageable pageable) {
        return ticketOrderDomain.getTicketOrderPage(request,pageable);
    }

    @Override
    public void statusChanged(Trade trade) {
        if(trade.getBusinessType()==WalletConstant.business_type_ticket_pay){
            TicketOrder order=getTicketOrderByOrderNo(trade.getTradeNo());
            if(order==null){
                log.error("/*--订单不存在--*/",trade.getTradeNo());
                return;
            }
            if(trade.getStatus()== TradeStatus.TRADE_SUCCEED.getStatus()){
                //支付成功
                SetTicketOrderStatusDTO statusDTO=new SetTicketOrderStatusDTO();
                statusDTO.setId(order.getId());
                statusDTO.setStatus(OrderStatus.SUCCESSED.getStatus());
                statusDTO.setStatusRemark("支付成功");
                setTicketOrderStatus(statusDTO);
            }else if(trade.getStatus()== TradeStatus.TRADE_FINISHED.getStatus()){
                //支付成功
                SetTicketOrderStatusDTO statusDTO=new SetTicketOrderStatusDTO();
                statusDTO.setId(order.getId());
                statusDTO.setStatus(OrderStatus.FINISHED.getStatus());
                statusDTO.setStatusRemark("已完成");
                setTicketOrderStatus(statusDTO);
            }if(trade.getStatus()== TradeStatus.TRADE_CLOSED.getStatus()){
                //支付成功
                SetTicketOrderStatusDTO statusDTO=new SetTicketOrderStatusDTO();
                statusDTO.setId(order.getId());
                statusDTO.setStatus(OrderStatus.CLOSED.getStatus());
                statusDTO.setStatusRemark("已关闭");
                setTicketOrderStatus(statusDTO);
            }

        }
    }


}
