package com.outmao.ebs.mall.order.vo;

import lombok.Data;

import java.util.List;

@Data
public class ToOrderVO {

    private List<Long> orders;

    private String payChannel;

    private String tradeNo;

    private double totalAmount;

}
