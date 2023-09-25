package com.outmao.ebs.common.configuration.constant;

public enum Errors {
	Success(0, 0, "请求成功"), 
	Failure(1, 1, "请求失败"), 
	FailureParameterError(2, 200, "参数错误"),
	FailureAuthentication(3, 300, "用户未登录"),
	FailureAuthorization(4, 400, "用户权限不足"),
	FailureServiceError(5, 500, "操作失败"),

	//钱包系统异常
	FailureWalletError(6, 600, "钱包操作失败"),
	FailureWalletRegisterRepeatError(6, 601, "同一用户只能注册一个钱包"),
	FailureWalletStatusError(6, 602, "钱包状态异常"),
	FailureWalletPasswordError(6, 603, "密码错误"),
	FailureWalletTradeNoFoundError(6, 604, "交易不存在"),
	FailureWalletTradeStatusError(6, 605, "交易状态异常"),
	FailureWalletBalanceNotEnoughError(6, 606, "佘额不足");

	//状态码
	private int status;

	//业务错误码
	private int code;

	//错误信息
	private String message;

	private Errors(int status, int code, String message) {
		this.status = status;
		this.code = code;
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
