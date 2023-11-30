package com.outmao.ebs.wallet.dto;


import lombok.Data;

import java.util.Date;

@Data
public class GetTransferListDTO {

    private Long walletId;

    private String currencyId;

    private Date fromTime;

    private Date toTime;

}
