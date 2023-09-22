package com.outmao.ebs.common.exception;

import com.outmao.ebs.common.configuration.constant.Errors;
import lombok.Data;

@Data
public class BusinessException extends IllegalArgumentException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private int status;

	private int code;

	private String error;

	public BusinessException(int status,int code,String message,String error){
		super(message);
		this.error=error;
		this.status=status;
		this.code=code;
	}

	public BusinessException(String message) {
		this(1,1,message,null);
	}

	public BusinessException(String message, String error) {
		this(1,1,message,error);
	}

	public BusinessException(Errors err) {
		this(err.getStatus(),err.getCode(),err.getMessage(),null);
	}

	public BusinessException(Errors err,String message) {
		this(err.getStatus(),err.getCode(),message,null);
	}


}
