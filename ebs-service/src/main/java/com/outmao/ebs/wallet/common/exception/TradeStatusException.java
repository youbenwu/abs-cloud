package com.outmao.ebs.wallet.common.exception;


import com.outmao.ebs.common.configuration.constant.Errors;

public class TradeStatusException extends WalletException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public TradeStatusException() {
        super(Errors.FailureWalletTradeStatusError);
    }

}