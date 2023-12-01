package com.outmao.ebs.common.configuration.web;


import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import java.text.SimpleDateFormat;
import java.util.Date;

@ControllerAdvice
public class GlobalControllerAdvice {

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// 全局设置接收表单日期格式  yyyy-MM-dd HH:mm:ss
		SimpleDateFormat dateFormat = new DateFormatPlugin();
		// 是否严格解析时间 false则严格解析 true宽松解析
		dateFormat.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}


}
