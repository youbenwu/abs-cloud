package com.outmao.ebs.jnet.robot;

import com.alibaba.fastjson.JSON;
import com.outmao.ebs.user.entity.User;
import com.outmao.ebs.jnet.domain.assignment.AssignmentDomain;
import com.outmao.ebs.jnet.dto.assignment.AssignmentSaveParamsDTO;
import com.outmao.ebs.jnet.entity.asssignment.MyAssignmentRobot;
import com.outmao.ebs.jnet.entity.asssignment.ZMyAssignment;
import com.outmao.ebs.jnet.enums.assignment.AssignmentUserTypeEnum;
import com.outmao.ebs.jnet.service.config.ConfigAssignmentService;
import com.outmao.ebs.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * 外发机器人基本操作
 * 
 * @author yeyi
 * @date 2019年11月3日
 */
@Slf4j
@Component
public class AssignmentRobot {
	
	@Autowired
	UserService userService;
	
	@Autowired
	ConfigAssignmentService configAssignmentService;
	
	@Autowired
	AssignmentDomain assignmentDomain;

	final static private Logger logger = LoggerFactory.getLogger(AssignmentRobot.class);
	
	/**
	 * 发外发
	 * 
	 * @author yeyi
	 * @date 2019年11月3日
	 */
	public void createAssignment() {
		User user = this.getRobotUser();
		if(null==user) {
			return;
		}
		
		log.info("createAssignmen getRobotUse: {}", user.getId());
		
		try {
			AssignmentSaveParamsDTO dto = this.makeAssignmentSaveParamsDTO();
 			ZMyAssignment result = assignmentDomain.saveAssignment(dto, user.getId());
			if(null==result) {
				log.error("createAssignmen err1: ");
			}else{
				log.info("createAssignmen success: {}", result.getId());
			}
		} catch (Exception e) {
			log.error("createAssignmen err: ", e);
		}
	}
	
	/**
	 * 申请其它机器人的外发
	 * 
	 * @author yeyi
	 * @date 2019年11月3日
	 */
	public void applyAssignment() {
		
	}
	
	/**
	 * 刷浏览量
	 * 
	 * @author yeyi
	 * @date 2019年11月3日
	 */
	public void addVisitNum() {
		
	}
	
	/**
	 * 从所有机器人中随机取一个登录
	 * @return 失败返回 null
	 * @author yeyi
	 * @date 2019年11月3日
	 */
	private User getRobotUser() {
		try {
			final PageRequest pageable = PageRequest.of(0, 99999);
			Page<User> users = userService.getUserPageByType(AssignmentUserTypeEnum.ROBOT.getValue(), pageable);
			if(null==users || CollectionUtils.isEmpty(users.getContent())) {
				log.error("getUserPageByType err: {}", null==users? "": JSON.toJSONString(users, true));
				return null;
			}
			
			int index = 0;
			// 随机找一个用户
			if(users.getContent().size()>1) {
				Random r = new Random(System.currentTimeMillis());
				index = r.nextInt(users.getContent().size());
			}

			User user = users.getContent().get(index);
			log.info("logined userId: {}", user.getId());
			return user;
		} catch (Exception e) {
			log.error("getRobotUse err: ", e);
			return null;
		}
	}
	
	/**
	 * 创建机器人外发参数
	 * @return
	 * @author yeyi
	 * @date 2019年11月3日
	 */
	private AssignmentSaveParamsDTO makeAssignmentSaveParamsDTO() {
		AssignmentSaveParamsDTO dto = new AssignmentSaveParamsDTO();
		dto.setUserType(AssignmentUserTypeEnum.ROBOT.getValue());
		dto.setAddress("白云太和工业圈");
		dto.setArea("白云区");

		Random r = new Random(System.currentTimeMillis());
		long categoryId = 1286L;
		try {
			List<MyAssignmentRobot> robots = assignmentDomain.getAllAssignmentRobot();
			if(CollectionUtils.isEmpty(robots)) {
				log.error("getAllAssignmentRobo empty");
				return null;
			}
			
			MyAssignmentRobot robot = robots.get(r.nextInt(robots.size()));
			if(null!=robot.getCategoryId() && 0!=robot.getCategoryId()) {
				categoryId = robot.getCategoryId();
			}else {
				String[] categoryArray = configAssignmentService.getAllCategoryId();
				if(categoryArray.length>0) {
					if(1==categoryArray.length) {
						categoryId = Long.parseLong(categoryArray[0]);
					}else {
						categoryId = Long.parseLong(categoryArray[r.nextInt(categoryArray.length)]);
					}
				}
			}
			
			dto.setCategoryId(categoryId);
			dto.setCity("广州市");
			dto.setImgUrl(robot.getImgUrl());
			dto.setLatitude("23.308137");
			dto.setLongitude("113.363469");
			dto.setProvince("广东省");
			dto.setTechnologyJson(configAssignmentService.getTechnologyJson());
			dto.setText(robot.getText());
			dto.setUserType(AssignmentUserTypeEnum.ROBOT.getValue());
			dto.setVisitsNum((long)r.nextInt(512));
			String[] avatars = configAssignmentService.getAvatars();
			if(null!=avatars) {
				StringBuilder sb = new StringBuilder();
				int applierNum = r.nextInt(19)+1;
				for(int i=0; i<applierNum; i++) {
					if(i>0) {
						sb.append(",");
					}
					sb.append(avatars[r.nextInt(avatars.length)]);
				}
				dto.setLatestApplicantAvatar(sb.toString());
				log.info("getAllAssignmentRobo avatars: {}", dto.getLatestApplicantAvatar());
			}
			
			// 外发过的就删除
			long deleteNum = assignmentDomain.updateRobotDeleted(true, Arrays.asList(robot.getId()));
			if(deleteNum<=0) {
				log.error("assignmentDomain.updateRobotDelete empty: {}", robot.getId());
			}
			
		} catch (Exception e) {
			log.error("getAllCategoryI err: ", e);
		}
		
		log.info("makeAssignmentSaveParamsDT: {},{},{},{}", dto.getCategoryId(), dto.getVisitsNum(), dto.getImgUrl(), dto.getText());
		return dto;
	}
}
