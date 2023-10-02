package com.outmao.ebs.mall.order.domain;

import com.outmao.ebs.mall.order.dto.GetTicketOrderListDTO;
import com.outmao.ebs.mall.order.dto.SetTicketOrderOutStatusDTO;
import com.outmao.ebs.mall.order.dto.SetTicketOrderStatusDTO;
import com.outmao.ebs.mall.order.dto.TicketOrderDTO;
import com.outmao.ebs.mall.order.entity.TicketOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TicketOrderDomain {


    public TicketOrder saveTicketOrder(TicketOrderDTO request);


    public TicketOrder getTicketOrderByOrderNo(String orderNo);


    public TicketOrder setTicketOrderStatus(SetTicketOrderStatusDTO request);


    public TicketOrder setTicketOrderOutStatus(SetTicketOrderOutStatusDTO request);


    public Page<TicketOrder> getTicketOrderPage(GetTicketOrderListDTO request, Pageable pageable);



}
