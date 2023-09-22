package com.outmao.ebs.wallet.dto;

import lombok.Data;

@Data
public class GetRechargeListDTO {

    private Long walletId;

    private Integer[] statusIn;

}
