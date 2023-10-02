package com.outmao.ebs.clients.shpanhe.api;


import com.outmao.ebs.clients.shpanhe.vo.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(url = "${shpanhe.ticket-api}", name = "shpanhe.ticket")
public interface ShpanheTicketClient {


    @RequestMapping(value="/ticket/searchScenicList?appKey=${shpanhe.app-key}",method= RequestMethod.GET)
    public PanheResponse<Scenic> searchScenicList(SearchScenicListRequest request);

    @RequestMapping(value="/ticket/getScenicDetail?appKey=${shpanhe.app-key}",method= RequestMethod.GET)
    public PanheResponse<ScenicDetail> getScenicDetail(String scenicID);


    @RequestMapping(value="/ticket/getTicketDetail?appKey=${shpanhe.app-key}",method= RequestMethod.GET)
    public PanheResponse<TicketDetail> getTicketDetail(String productId);


    @RequestMapping(value="/ticket/createOrder?appKey=${shpanhe.app-key}",method= RequestMethod.POST)
    public CreateOrderResponse createOrder(@RequestBody CreateOrderRequest request);


    @RequestMapping(value="/ticket/cancelOrder?appKey=${shpanhe.app-key}",method= RequestMethod.POST)
    public PanheResponse<Object> cancelOrder(String orderNo);

    @RequestMapping(value="/ticket/autoPay?appKey=${shpanhe.app-key}",method= RequestMethod.POST)
    public PanheResponse<Object> autoPay(AutoPayRequest request);


    @RequestMapping(value="/ticket/ApplyRefundTicket?appKey=${shpanhe.app-key}",method= RequestMethod.POST)
    public PanheResponse<Object> applyRefundTicket(String orderNo);


    @RequestMapping(value="/ticket/getOrderDetail?appKey=${shpanhe.app-key}",method= RequestMethod.GET)
    public PanheResponse<OrderDetail> getOrderDetail(String orderNo);


}
