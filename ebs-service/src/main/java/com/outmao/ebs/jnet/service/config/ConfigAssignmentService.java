package com.outmao.ebs.jnet.service.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 从数据库中读取外发相关的配置
 * @author: yeyi
 * @date: 2019/11/01 14:08
 **/
@Slf4j
@Service
public class ConfigAssignmentService extends ConfigBaseService {
	
	final static private Logger logger = LoggerFactory.getLogger(ConfigAssignmentService.class);
	
    private static String [] avatars = null;
    
	/**
	 * @return 机器人统一登录密码
	 * @author yeyi
	 * @date 2019年11月3日
	 */
	public String getRobotPassword() {
		return this.get("assignment.robot.pwd");
	}
	
	/**
	 * 外发机器人用生产品类ID集合（z_production_category子ID）
	 * @return
	 * @author yeyi
	 * @date 2019年11月3日
	 */
	public String[] getAllCategoryId() {
		String s = this.get("assignment.robot.productCategoryIds");
		if(StringUtils.isBlank(s)) {
			log.error("assignment.robot.productCategoryId empty");
			return new String[0];
		}
		
		return s.split(",");
	}
	
	public String getTechnologyJson() {
		return this.get("assignment.robot.technologyJson");
	}
	
	public synchronized String[] getAvatars() {
		if(null!=avatars) {
			return avatars;
		}
		
		// 从文件中读
		BufferedReader in = null;
    	try {
    		InputStream path = this.getClass().getResourceAsStream("/assignmentRobotApplierAvatar.txt");
            in = new BufferedReader(new InputStreamReader(path));
            String line;
            line = in.readLine();
            if(StringUtils.isBlank(line)) {
            	log.error("readRobotApplierAvata empty");
            	return null;
            }
        	avatars = line.split(",");
            log.info("readRobotApplierAvata words num: {}", avatars.length);
		} catch (Exception e) {
			log.error("readRobotApplierAvata err: ", e);
		} finally {
			if(null!=in) {
				try {
					in.close();
				} catch (IOException e2) {
					e2.printStackTrace();
					log.error("readRobotApplierAvata close err: ", e2);
				}
			}
		}
    	
    	return avatars;
	}
	
	/**
	 * @return 新上功能使用人数每次增加随机最大值
	 * @author yeyi
	 * @date 2019年11月5日
	 */
	public String getIndexFunctionUseCountAddMax() {
		return this.get("index.function.useCountAddMax");
	}
	
	/**
	 * @return 外发机器人增加访问每次增加随机最大值
	 * @author yeyi
	 * @date 2019年11月5日
	 */
	public String getAssignmentVisitNumAddMax() {
		return this.get("assignment.robot.visitNumAddMax");
	}
}
