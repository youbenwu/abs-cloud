package com.outmao.ebs.wallet.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor@NoArgsConstructor
@Data
public class TradePayToDTO {

    private String tradeNo;
    private Long toId;
    private long amount;
    private int businessType;
    private String business;
    private String remark;

}
