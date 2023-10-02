package com.outmao.ebs.clients.shpanhe.service;


import com.outmao.ebs.clients.shpanhe.vo.*;


public interface ShpanheTicketService {



    public PanheResponse<Scenic> searchScenicList(SearchScenicListRequest request);


    public PanheResponse<ScenicDetail> getScenicDetail(String scenicID);



    public PanheResponse<TicketDetail> getTicketDetail(String productId);



    public CreateOrderResponse createOrder(CreateOrderRequest request);


    public PanheResponse<Object> cancelOrder(String orderNo);


    public PanheResponse<Object> autoPay(AutoPayRequest request);


    public PanheResponse<Object> applyRefundTicket(String orderNo);


    public PanheResponse<OrderDetail> getOrderDetail(String orderNo);


}
