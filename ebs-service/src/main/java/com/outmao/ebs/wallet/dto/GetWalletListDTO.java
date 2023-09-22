package com.outmao.ebs.wallet.dto;


import lombok.Data;

@Data
public class GetWalletListDTO {

    private Integer type;

    private Integer[] statusIn;

}
