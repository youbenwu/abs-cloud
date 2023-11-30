package com.outmao.ebs.wallet.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class StatsTransferVO {

    @ApiModelProperty(name = "realName", value = "收入-支出")
    private long amount;

    @ApiModelProperty(name = "fromAmount", value = "总收入")
    private long fromAmount;

    @ApiModelProperty(name = "toAmount", value = "总支出")
    private long toAmount;

}
