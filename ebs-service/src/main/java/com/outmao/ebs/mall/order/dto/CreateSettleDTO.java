package com.outmao.ebs.mall.order.dto;

import lombok.Data;

import java.util.List;


@Data
public class CreateSettleDTO {

    private Long userId;

    private Long storeId;

    private Long lookId;

    private List<CreateSettleProductDTO> products;


}
