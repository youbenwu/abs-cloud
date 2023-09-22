package com.outmao.ebs.wallet.common.exception;


import com.outmao.ebs.common.configuration.constant.Errors;

public class WalletBalanceNotEnoughException extends WalletException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WalletBalanceNotEnoughException() {
		super(Errors.FailureWalletBalanceNotEnoughError);
	}

}
