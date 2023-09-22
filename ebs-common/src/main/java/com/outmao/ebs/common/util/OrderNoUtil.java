package com.outmao.ebs.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class OrderNoUtil {

	public static String generateOrderNo() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String result = sdf.format(new Date());
		int uuid = UUID.randomUUID().toString().hashCode();
		if (uuid < 0) {//有可能是负数
			uuid = -uuid;
		}
		// 0 代表前面补充0
		// 11 代表长度为11
		// d 代表参数为正数型
		return result + String.format("%011d", uuid);
	}

}
