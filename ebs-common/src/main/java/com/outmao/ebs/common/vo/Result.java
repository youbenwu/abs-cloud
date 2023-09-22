package com.outmao.ebs.common.vo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.outmao.ebs.common.configuration.constant.Errors;
import com.outmao.ebs.common.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value= Include.NON_NULL)
public class Result<T> {

	private int status;

	private int code;

	private String message;
	
	private String error;

	private T data;


	public static Result result(BusinessException e) {
		return new Result(e.getStatus(),e.getCode(),e.getMessage(),e.getError(),null);
	}

	public static Result successResult(Object data) {
		return new Result(0,0,"操作成功",null,data);
	}

	public static Result successResult(Object data, String message) {
		return new Result(0,0,message,null,data);
	}
	
	public static Result failureResult() {
		return new Result(1,1,"请求失败",null,null);
	}

	public static Result failureResult(String message) {
		return new Result(1,1,message,null,null);
	}

	public static Result result(Errors error){
		return new Result(error.getStatus(),error.getCode(),error.getMessage(),null,null);


	}

}
