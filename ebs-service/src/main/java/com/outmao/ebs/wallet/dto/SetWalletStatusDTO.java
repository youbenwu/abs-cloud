package com.outmao.ebs.wallet.dto;

import lombok.Data;

@Data
public class SetWalletStatusDTO {

    private Long id;
    private int status;
    private String statusRemark;

}
