package com.outmao.ebs.jnet.util;

import org.springframework.util.Assert;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 日期转换工具类 java8
 * @author yeyi
 * @date 2019年7月2日
 */
public class DateUtil {
	
	private final static String defaultPattern = "yyyy-MM-dd HH:mm:ss";

    public static Long strToLong(String time, String formatPattern) { 
        Assert.notNull(time, "time is null");
        DateTimeFormatter ftf = DateTimeFormatter.ofPattern(formatPattern);
        LocalDateTime parse = LocalDateTime.parse(time, ftf);
        return LocalDateTime.from(parse).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }
    
	/**
     * 将字符串转日期成Long类型的时间戳，格式为：yyyy-MM-dd HH:mm:ss
     * https://blog.csdn.net/u012843361/article/details/80496272
     */
    public static Long strToLong(String time) { 
    	return DateUtil.strToLong(time, defaultPattern);
    }
    
    public static Date strToDate(String time, String formatPattern) {
    	return new Date(DateUtil.strToLong(time, formatPattern));
    }
    
    public static Date strToDate(String time) {
    	return new Date(DateUtil.strToLong(time));
    }
    
    public static String longToStr(long time, String formatPattern) {
    	Assert.notNull(time, "time is null");
        DateTimeFormatter ftf = DateTimeFormatter.ofPattern(formatPattern);
        return ftf.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(time),ZoneId.systemDefault()));
    }
    
    public static String longToStr(long time) {
    	Assert.notNull(time, "time is null");
        DateTimeFormatter ftf = DateTimeFormatter.ofPattern(defaultPattern);
        return ftf.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(time),ZoneId.systemDefault()));
    }
    
    public static String dateToStr(Date date) {
    	return DateUtil.longToStr(date.getTime());
    }
    
    public static String dateToStr(Date date, String formatPattern) {
    	return DateUtil.longToStr(date.getTime(), formatPattern);
    }
    
    public static void main(String[] args) {
		String d1 = "2018-08-11 00:00:00";
		Date et = DateUtil.strToDate(d1);
		System.out.println(et);
		System.out.println(DateUtil.dateToStr(et));
	}
}
