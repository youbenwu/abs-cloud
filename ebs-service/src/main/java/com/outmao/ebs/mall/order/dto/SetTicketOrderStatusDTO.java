package com.outmao.ebs.mall.order.dto;


import lombok.Data;

@Data
public class SetTicketOrderStatusDTO {

    private Long id;

    private int status;

    private String statusRemark;


}
