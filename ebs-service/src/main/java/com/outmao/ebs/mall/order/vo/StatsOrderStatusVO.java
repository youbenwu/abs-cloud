package com.outmao.ebs.mall.order.vo;

import lombok.Data;

@Data
public class StatsOrderStatusVO {

    private Integer status;

    private Long count;

    private Double amount;

}
