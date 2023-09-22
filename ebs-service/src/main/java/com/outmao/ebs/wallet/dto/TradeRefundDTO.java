package com.outmao.ebs.wallet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TradeRefundDTO {
    private String tradeNo;
    private long amount;
    private int businessType; //业务类型
    private String business;//业务说明
    private String remark;//转帐备注
}
