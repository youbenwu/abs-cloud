package com.outmao.ebs.clients.shpanhe.service.impl;

import com.outmao.ebs.clients.shpanhe.api.ShpanheTicketClient;
import com.outmao.ebs.clients.shpanhe.service.ShpanheTicketService;
import com.outmao.ebs.clients.shpanhe.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ShpanheTicketServiceImpl implements ShpanheTicketService {


    @Autowired
    private ShpanheTicketClient shpanheTicketClient;


    @Override
    public PanheResponse<Scenic> searchScenicList(SearchScenicListRequest request) {
        return shpanheTicketClient.searchScenicList(request);
    }

    @Override
    public PanheResponse<ScenicDetail> getScenicDetail(String scenicID) {
        return shpanheTicketClient.getScenicDetail(scenicID);
    }


    @Override
    public PanheResponse<TicketDetail> getTicketDetail(String productId) {
        return shpanheTicketClient.getTicketDetail(productId);
    }

    @Override
    public CreateOrderResponse createOrder(CreateOrderRequest request) {
        return shpanheTicketClient.createOrder(request);
    }

    @Override
    public PanheResponse<Object> cancelOrder(String orderNo) {
        return shpanheTicketClient.cancelOrder(orderNo);
    }

    @Override
    public PanheResponse<Object> autoPay(AutoPayRequest request) {
        return shpanheTicketClient.autoPay(request);
    }

    @Override
    public PanheResponse<Object> applyRefundTicket(String orderNo) {
        return shpanheTicketClient.applyRefundTicket(orderNo);
    }



    @Override
    public PanheResponse<OrderDetail> getOrderDetail(String orderNo) {
        return shpanheTicketClient.getOrderDetail(orderNo);
    }


}
