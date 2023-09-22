package com.outmao.ebs.wallet.common.exception;


import com.outmao.ebs.common.configuration.constant.Errors;

public class TradeNoFoundException extends WalletException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public TradeNoFoundException() {
        super(Errors.FailureWalletTradeNoFoundError);
    }

}