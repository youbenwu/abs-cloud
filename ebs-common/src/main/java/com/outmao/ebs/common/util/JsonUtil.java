package com.outmao.ebs.common.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.TimeZone;

public class JsonUtil {

	/**
	 * .扩展jackson日期格式化支持格式
	 */
	public static class ObjectMapperDateFormatExtend extends DateFormat {

		private static final long serialVersionUID = 1L;

		private DateFormat dateFormat;

		private SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


		public ObjectMapperDateFormatExtend(DateFormat dateFormat) {
			//构造函数传入objectmapper默认的dateformat
			this.dateFormat = dateFormat;
			format1.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
		}
		//序列化时会执行这个方法
		@Override
		public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) {
			return format1.format(date,toAppendTo,fieldPosition);
		}
		//反序列化时执行此方法，我们先让他执行我们自己的format，如果异常则执执行他的
		//当然这里只是简单实现，可以有更优雅的方式来处理更多的格式
		@Override
		public Date parse(String source, ParsePosition pos) {
			Date date;

			try {
				date = format1.parse(source, pos);
			} catch (Exception e) {
				date = dateFormat.parse(source, pos);
			}
			return date;
		}
		//此方法在objectmapper 默认的dateformat里边用到，这里也要重写
		@Override
		public Object clone() {
			DateFormat dateFormat = (DateFormat) this.dateFormat.clone();
			return new ObjectMapperDateFormatExtend(dateFormat);
		}
	}


	
	private static final ObjectMapper mapper = new ObjectMapper();
	static {
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		DateFormat dateFormat = mapper.getDateFormat();
		mapper.setConfig(mapper.getDeserializationConfig().with(new ObjectMapperDateFormatExtend(dateFormat)));//反序列化扩展日期格式支持
		mapper.setDateFormat(new ObjectMapperDateFormatExtend(dateFormat));
	}
	/**
	 * 将对象转化为json
	 * @author yangwenkui
	 * @time 2017年3月16日 下午2:55:10
	 * @param obj 待转化的对象
	 * @return 当转化发生异常时返回null
	 */
	public static String toJson(Object obj){
	    if(obj == null){
	        return null;
	    }
	    try {
	        return mapper.writeValueAsString(obj);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	/**
	 * 将json转化为对象
	 * @author yangwenkui
	 * @time 2017年3月16日 下午2:56:26
	 * @param json json对象
	 * @param clazz 待转化的对象类型
	 * @return 当转化发生异常时返回null
	 */
	public static <T> T fromJson(String json,Class<T> clazz){
	    if(json == null){
	        return null;
	    }
	    try {
	        return mapper.readValue(json, clazz);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	/**
	 * 将json对象转化为集合类型
	 * @author yangwenkui
	 * @time 2017年3月16日 下午2:57:15
	 * @param json json对象
	 * @param collectionClazz 具体的集合类的class，如：ArrayList.class
	 * @param clazz 集合内存放的对象的class
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static <T> Collection<T> fromJson(String json,Class<? extends Collection> collectionClazz,Class<T> clazz){
	    if(json == null){
	        return null;
	    }
	    try {
	        Collection<T> collection =  mapper.readValue(json, getCollectionType(collectionClazz,clazz));
	        return collection;
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return null;
	}

	private static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
	    return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);   
	}


}
