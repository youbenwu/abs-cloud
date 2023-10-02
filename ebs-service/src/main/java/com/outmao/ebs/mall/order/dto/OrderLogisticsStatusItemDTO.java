package com.outmao.ebs.mall.order.dto;

import lombok.Data;

@Data
public class OrderLogisticsStatusItemDTO {

    /**
     * 自动编号
     */
    private Long id;

    /**
     *
     * 物流ID
     *
     */
    private Long logisticsId;

    /**
     *
     * 物流状态ID
     *
     */
    private Long statusId;

    /**
     *
     * 跟踪信息
     *
     */
    private String content;


}
