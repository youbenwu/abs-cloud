package com.outmao.ebs.wallet.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SetCashStatusDTO {

    private String orderNo;

    private int status;

    private String statusRemark;

}
