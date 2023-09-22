package com.outmao.ebs.common.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RequestUtil {

	public static int getParameterInt(HttpServletRequest request, String name){
		String value=request.getParameter(name);
		if(value!=null){
			try {
				return Integer.parseInt(value);
			}catch (Exception e){

			}
		}
		return 0;
	}

	public static double getParameterDouble(HttpServletRequest request, String name){
		String value=request.getParameter(name);
		if(value!=null){
			try {
				return Double.parseDouble(value);
			}catch (Exception e){

			}
		}
		return 0.0;
	}

	public static Long getParameterLong(HttpServletRequest request, String name){
		String value=request.getParameter(name);
		if(value!=null){
			try {
				return Long.parseLong(value);
			}catch (Exception e){

			}
		}
		return 0L;
	}

	public static Long getParameterLong(HttpServletRequest request, String name,Long def){
		String value=request.getParameter(name);
		if(value!=null){
			try {
				return Long.parseLong(value);
			}catch (Exception e){
			}
		}
		return def;
	}

	public static String get(String name){
		HttpServletRequest request=request();
		if(request==null)
			return null;
		String value=request.getParameter(name);
		if(value!=null&&!value.isEmpty())
			return value;
		value=getHeader(request,name);
		return value;
	}

	public static String get(String name,String def){
		HttpServletRequest request=request();
		if(request==null)
			return def;
		String value=request.getParameter(name);
		if(value!=null&&!value.isEmpty())
			return value;
		value=getHeader(request,name);
		if(value!=null&&!value.isEmpty())
			return value;
		return def;
	}

	public static Long getLong(String name){
		HttpServletRequest request=request();
		if(request==null)
			return null;
		String value=request.getParameter(name);
		if(value==null||value.isEmpty()) {
			value = request.getHeader(name);
		}
		if(value!=null&&!value.isEmpty()) {
			try {
				return Long.parseLong(value);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static Long getLong(String name,Long def){
		HttpServletRequest request=request();
		if(request==null)
			return def;
		String value=request.getParameter(name);
		if(value==null||value.isEmpty()) {
			value = request.getHeader(name);
		}
		if(value!=null&&!value.isEmpty()) {
			try {
				return Long.parseLong(value);
			} catch (Exception e) {

			}
		}
		return def;
	}

	public static Map<String,Object> getHeaders(){
		Map<String,Object> headers=new HashMap<>();
		HttpServletRequest request=request();
		Enumeration<String> names=request.getHeaderNames();
		while (names.hasMoreElements()){
			String name=names.nextElement();
			String value = request.getHeader(name);
			if (value != null) {
				try {
					value = RequestUtil.URLDecoder(value);
				}catch (Exception e){
					e.printStackTrace();
				}
				headers.put(name,value);
			}
		}
		return headers;
	}
	
	public static String getHeader( String name) {
		return getHeader(request(), name);
	}

	public static String getHeader(HttpServletRequest request, String name) {
		try {
			String value = request.getHeader(name);
			if (value != null) {
				value = RequestUtil.URLDecoder(value);
			}
			return value;
		} catch (Exception e) {
		}
		return null;
	}

	public static Long getHeaderLong(String name) {
		try {
			String value = getHeader(name);
			return Long.parseLong(value);
		} catch (Exception e) {
		}
		return null;
	}

	public static int getHeaderInt(String name){
		try {
			String value = getHeader(name);
			return Integer.parseInt(value);
		} catch (Exception e) {
		}
		return 0;
	}

	private static String URLDecoder(String value) throws UnsupportedEncodingException {
		if (value.contains("\\u")) {
			StringBuffer buf = new StringBuffer();
			Matcher m = Pattern.compile("\\\\u([0-9A-Fa-f]{4})").matcher(value);
			while (m.find()) {
				try {
					int cp = Integer.parseInt(m.group(1), 16);
					m.appendReplacement(buf, "");
					buf.appendCodePoint(cp);
				} catch (NumberFormatException e) {
				}
			}
			m.appendTail(buf);
			value = buf.toString();
		}
		return URLDecoder.decode(value, "UTF-8");
	}


	
	public static HttpSession session() {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		if(requestAttributes==null)
			return null;
		HttpServletRequest request = requestAttributes.getRequest();
		return request.getSession();
	}

	public static HttpServletRequest request() {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		if(requestAttributes==null)
			return null;
		HttpServletRequest request = requestAttributes.getRequest();
		return request;
	}

	public static HttpServletResponse response() {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		if(requestAttributes==null)
			return null;
		HttpServletResponse response = requestAttributes.getResponse();
		return response;
	}

	public static String getCookie(HttpServletRequest request, String cookieName) {

		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(cookieName)) {
					return cookie.getValue();
				}
			}
		}

		return null;
	}

	public static void writeCookie(HttpServletResponse response, String cookieName, String value) {
		Cookie cookie = new Cookie(cookieName, value);
		cookie.setPath("/");
		cookie.setMaxAge(3600);
		response.addCookie(cookie);
	}

}
