package com.outmao.ebs.wallet.dto;


import lombok.Data;

@Data
public class GetCashListDTO {


    private Long walletId;

    private Integer[] statusIn;



}
