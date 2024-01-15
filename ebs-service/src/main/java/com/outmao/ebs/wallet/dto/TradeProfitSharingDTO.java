package com.outmao.ebs.wallet.dto;

import lombok.Data;

import java.util.List;

/**
 *
 * 分账请求
 * */
@Data
public class TradeProfitSharingDTO {


    private String tradeNo;

    private String sharingNo;

    private boolean unfreeze;

    private List<TradeProfitSharingReceiverDTO> receivers;


}
