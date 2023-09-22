package com.outmao.ebs.wallet.dto;



import lombok.Data;


@Data
public class GetTradeListDTO {


    private Long walletId;

    private Integer type;

    private Integer[] statusIn;


}
