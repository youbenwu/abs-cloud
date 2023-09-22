package com.outmao.ebs.wallet.common.exception;


import com.outmao.ebs.common.configuration.constant.Errors;

public class WalletStatusException extends WalletException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public WalletStatusException() {
        super(Errors.FailureWalletStatusError);
    }

    public WalletStatusException(String message) {
        super(Errors.FailureWalletStatusError,message);
    }

}
