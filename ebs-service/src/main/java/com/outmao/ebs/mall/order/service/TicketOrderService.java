package com.outmao.ebs.mall.order.service;

import com.outmao.ebs.mall.order.dto.*;
import com.outmao.ebs.mall.order.entity.TicketOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TicketOrderService {


    public TicketOrder createTicketOrder(CreateTicketOrderDTO request);


    public TicketOrder saveTicketOrder(TicketOrderDTO request);


    public TicketOrder getTicketOrderByOrderNo(String orderNo);


    public TicketOrder setTicketOrderStatus(SetTicketOrderStatusDTO request);


    public TicketOrder setTicketOrderOutStatus(SetTicketOrderOutStatusDTO request);


    public Page<TicketOrder> getTicketOrderPage(GetTicketOrderListDTO request, Pageable pageable);



}
