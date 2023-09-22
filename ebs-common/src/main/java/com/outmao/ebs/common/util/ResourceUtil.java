package com.outmao.ebs.common.util;

import com.outmao.ebs.common.exception.BusinessException;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import java.io.IOException;


public class ResourceUtil {

	public static String getResourceString(String path) {
		try {
			ClassPathResource resource = new ClassPathResource(path);
			String string = IOUtils.toString(resource.getInputStream());
			return string;
		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}


}
