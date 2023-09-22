package com.outmao.ebs.common.configuration.web;

import com.outmao.ebs.common.configuration.constant.Errors;
import com.outmao.ebs.common.configuration.web.annotation.IgnoreResponseAdvice;
import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.common.vo.Result;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.net.BindException;
import java.nio.file.AccessDeniedException;
import java.util.Set;

/*
 *
 *
 * 统一包装正常返回结果
 * 
 * */
@ResponseBody
@ControllerAdvice(annotations = { RestController.class })
public class GlobalRestControllerAdvice implements ResponseBodyAdvice<Object> {


	@Override
	public Object beforeBodyWrite(Object body,
								  MethodParameter returnType,
								  MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
								  ServerHttpRequest request,
                                  ServerHttpResponse response
	) {
		if (selectedContentType.includes(MediaType.APPLICATION_JSON)) {
			System.out.println("/* beforeBodyWrite */");
			if (body == null || body.getClass() != Result.class)
				return Result.successResult(body);
		}
		return body;
	}


	// 可以指定需要处理的方法
	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		if (returnType.getDeclaringClass().isAnnotationPresent(
				IgnoreResponseAdvice.class
		)) {
			return false;
		}

		if (returnType.getMethod().isAnnotationPresent(
				IgnoreResponseAdvice.class
		)) {
			return false;
		}
		return true;
	}

	@ExceptionHandler(AccessDeniedException.class)
	public Result handleException(AccessDeniedException ex) {
		System.out.println("/* ExceptionHandler AccessDeniedException */");
		ex.printStackTrace();
		return Result.result(Errors.FailureAuthorization);
	}

	// 通用异常的处理
	@ExceptionHandler(BusinessException.class)
	public Result handleServiceException(BusinessException ex) {
		System.out.println("/* ExceptionHandler BusinessException */");
		return Result.result(ex);
	}

	// 通用异常的处理
	@ExceptionHandler(Exception.class)
	public Result handleException(Exception ex) {
		System.out.println("/* handleException */");
		ex.printStackTrace();
		return Result.failureResult(ex.getMessage());
	}


	@ExceptionHandler(BindException.class)
	public Result resolveConstraintViolationException(BindException ex){
		System.out.println("/* BindException */");
		ex.printStackTrace();
		return Result.failureResult();
	}
	/**
	 * 用来处理bean validation异常
	 * @param ex
	 * @return
	 */

	@ExceptionHandler(ConstraintViolationException.class)
	public Result resolveConstraintViolationException(ConstraintViolationException ex){
		System.out.println("/* resolveConstraintViolationException */");
		Result errorWebResult = Result.failureResult();
		Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
		if(!CollectionUtils.isEmpty(constraintViolations)){
			StringBuilder msgBuilder = new StringBuilder();
			for(ConstraintViolation<?> constraintViolation :constraintViolations){
				msgBuilder.append(constraintViolation.getMessage()).append(",");
			}
			String errorMessage = msgBuilder.toString();
			if(errorMessage.length()>1){
				errorMessage = errorMessage.substring(0,errorMessage.length()-1);
			}
			errorWebResult.setMessage(errorMessage);
			return errorWebResult;
		}
		errorWebResult.setError(ex.getMessage());
		return errorWebResult;
	}



}
