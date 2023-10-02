package com.outmao.ebs.mall.merchant.dto;

import lombok.Data;

import java.util.Date;

@Data
public class GetUserCommissionRecordListDTO {


    private Long commissionId;

    private Long userId;

    private Date startTime;

    private Date endTime;

}
