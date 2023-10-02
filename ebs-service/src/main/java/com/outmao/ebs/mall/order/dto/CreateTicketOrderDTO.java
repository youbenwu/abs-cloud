package com.outmao.ebs.mall.order.dto;


import com.outmao.ebs.clients.shpanhe.vo.CreateOrderRequest;
import lombok.Data;

@Data
public class CreateTicketOrderDTO extends CreateOrderRequest {

    private Long userId;

    private Long walletId;


}
