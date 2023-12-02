package com.outmao.ebs.jnet.util;



import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * BigDecimal 四舍五入工具类
 * 
 * @author: yeyi
 * @date: 2018年11月2日
 */
public class BigDecimalUtil {
	
	/**
	 * 保留小数位数
	 */
	private static int scale = 4;
	
	/**
	 * 四舍五入输入参数
	 * @param val
	 * @return
	 * @author: yeyi
	 * @date: 2018年11月1日
	 */
	public static BigDecimal rounding4_5(BigDecimal val) {
		if(null==val) {
			return null;
		}
		
		return val.setScale(scale, BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * 返回结果如果是整数则去掉小数点
	 * @param val
	 * @return
	 * @author: yeyi
	 * @date: 2018年11月16日
	 */
	public static String rounding4_5Cut0Integer(BigDecimal val) {
		if(null==val) {
			return null;
		}
		
		return cut0Integer(rounding4_5(val).toString());
	}
	
	/**
	 * 四舍五入后去掉后边所有多余的0
	 * @author: yeyi
	 * @date: 2019年10月24日
	 */
	public static String rounding4_5Cut0(BigDecimal val) {
		if(null==val) {
			return null;
		}
		
		return rounding4_5(val).stripTrailingZeros().toPlainString();
	}
	
	public static Double rounding4_5(Double val) {
		if(null==val) {
			return null;
		}
		
		BigDecimal t = new BigDecimal(val);
		t = rounding4_5(t);
		
		return t.doubleValue();
	}
	
	public static String rounding4_5(String val) {
		if(StringUtils.isEmpty(val)) {
			return val;
		}
		
		BigDecimal t = new BigDecimal(val);
		t = rounding4_5(t);
		
		return t.toString();
	}
	
	public static int getScale() {
		return scale;
	}

	// 多线程的时候还是不能乱改这个
//	public static void setScale(int scale) {
//		BigDecimalUtil.scale = scale;
//	}
	
	/**
	 * 生成一个格式化小数位数的格式字符串
	 * 如果考虑性能，这个结果可以一开始就缓存起来
	 * @return
	 * @author: yeyi
	 * @date: 2018年11月5日
	 */
	public static StringBuilder makeFormatStr() {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<scale; i++) {
			if(0==i) {
				sb.append(".0");
			} else {
				sb.append("0");
			}
		}
		
		return sb;
	}
	
	/**
	 * 千分位且保留小数点: 10000.123 -> 10,000.13
	 * @param val
	 * @return
	 * @author: yeyi
	 * @date: 2018年11月5日
	 */
	public static String thousandRounding4_5(BigDecimal val) {
		if(null==val) {
			return "";
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("###,##0");
		sb.append(makeFormatStr());
		
		DecimalFormat df = new DecimalFormat(sb.toString());
		
		return df.format(val.doubleValue());
	}
	
	/**
	 * 四舍五入且删除整数后小数点（.00）
	 * @author: yeyi
	 * @date: 2019年10月24日
	 */
	public static String thousandRounding4_5Cut0Integer(BigDecimal val) {
		String result = thousandRounding4_5(val);
		return cut0Integer(result);
	}
	
	/**
	 * 删除整数后面的小数点（.00）
	 * @author: yeyi
	 * @date: 2018年11月16日
	 */
	private static String cut0Integer(String val) {
		String formatStr = makeFormatStr().toString();
		if(!val.endsWith(formatStr)) { // val 返回肯定不为 null 所以不用判断空 
			return val;	
		}

		return val.substring(0, val.length()-formatStr.length());
	}
}
