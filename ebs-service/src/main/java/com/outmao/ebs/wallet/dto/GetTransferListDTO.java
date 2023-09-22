package com.outmao.ebs.wallet.dto;


import lombok.Data;

@Data
public class GetTransferListDTO {

    private Long walletId;

    private String currencyId;


}
