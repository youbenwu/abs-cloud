package com.outmao.ebs.mall.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateSettleProductDTO {

    private Long skuId;

    private int quantity;

}
