package com.outmao.ebs.wallet.common.exception;


import com.outmao.ebs.common.configuration.constant.Errors;
import com.outmao.ebs.common.exception.BusinessException;

public class WalletException extends BusinessException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WalletException() {
		super(Errors.FailureWalletError);
	}

	public WalletException(String message) {
		super(Errors.FailureWalletError.getStatus(),Errors.FailureWalletError.getCode(),message,null);
	}

	public WalletException(String message, String error) {
		super(Errors.FailureWalletError.getStatus(),Errors.FailureWalletError.getCode(),message,error);
	}

	public WalletException(Errors err) {
		super(err);
	}

	public WalletException(Errors err,String message) {
		super(err,message);
	}


}
