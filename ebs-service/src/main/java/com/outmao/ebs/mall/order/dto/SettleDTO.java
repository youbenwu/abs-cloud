package com.outmao.ebs.mall.order.dto;

import lombok.Data;

import java.util.List;

@Data
public class SettleDTO {

    private Long id;

    private Long userId;

    private Long storeId;

    private Long lookId;

    private Long addressId;

    private String payChannel;

    private List<SettleShopDTO> shops;


}
