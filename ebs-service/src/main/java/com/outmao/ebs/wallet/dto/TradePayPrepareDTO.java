package com.outmao.ebs.wallet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TradePayPrepareDTO {

    private String tradeNo;

    private int payChannel;
    private int outPayType;

}
