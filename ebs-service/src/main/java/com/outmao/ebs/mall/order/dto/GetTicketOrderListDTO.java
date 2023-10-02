package com.outmao.ebs.mall.order.dto;


import lombok.Data;

import java.util.Date;

@Data
public class GetTicketOrderListDTO {


    private String keyword;

    private Date startTime;

    private Date endTime;


}
