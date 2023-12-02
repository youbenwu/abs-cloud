package com.outmao.ebs.jnet.vo.assignment;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.outmao.ebs.jnet.entity.asssignment.ZMyAssignment;
import com.outmao.ebs.jnet.enums.assignment.AssignmentStatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author yeyi
 * @date 2019年9月1日
 */
@Slf4j
@ApiModel(value = "ZMyAssignmentVO", description = "我的外发")
@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class ZMyAssignmentVO implements Serializable{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 申请人头像最多保存多少个
	 */
	public static final int MAX_AVATARS_NUM = 20;
	
	/**
	 * 最多可以有多少个人申请同一外发单
	 */
	public static final int MAX_APPLICANT_NUM = 50;
	
	final static private Logger logger = LoggerFactory.getLogger(ZMyAssignmentVO.class);

	@ApiModelProperty(name = "id", value = "外发单ID")
	private Long id;
	
	@ApiModelProperty(name = "assignmentApplyId", value = "外发申请ID")
	private Long assignmentApplyId;

	@ApiModelProperty(name = "type", value = "0大厂 1官方")
	private Byte type;

	@ApiModelProperty(name = "status", value = "0生效中 1已到期 2已满员（上限50人） 3已结束 4已取消 5待完成(包含12状态)")
	private Byte status;

	@ApiModelProperty(name = "text", value = "正文少于500字符")
	private String text;

	@ApiModelProperty(name = "imgUrl", value = "图片url,0~9张，英文逗号分隔")
	private String imgUrl;

	@ApiModelProperty(name = "applicantNum", value = "申请人数")
	private Long applicantNum;

	@ApiModelProperty(name = "validityTime", value = "再过多少秒就过期")
	private Long validityTime;
	
	@ApiModelProperty(name = "technologies", value = "关联的工艺列表")
	private List<ZMyAssignmentApplyTechnonogyVO> technologies;

	@ApiModelProperty(name = "price", value = "外发申请报价")
	private String price;
	
	@ApiModelProperty(name = "userId", value = "所属用户ID(ebs_user.id)")
	private Long userId;

	@ApiModelProperty(name = "latestApplicantAvatar", value = "最近 20 个申请人头像 url, 用英文逗号分隔")
	private String latestApplicantAvatar;
	
	@ApiModelProperty(name = "userType", value = "所属用户的类型（冗余），同 ebs_user.type: 0普通 13外发机器人")
	private Integer userType;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAssignmentApplyId() {
		return assignmentApplyId;
	}

	public void setAssignmentApplyId(Long assignmentApplyId) {
		this.assignmentApplyId = assignmentApplyId;
	}

	public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Long getApplicantNum() {
		return applicantNum;
	}

	public void setApplicantNum(Long applicantNum) {
		this.applicantNum = applicantNum;
	}

	public Long getValidityTime() {
		return validityTime;
	}

	public void setValidityTime(Long validityTime) {
		this.validityTime = validityTime;
	}

	public List<ZMyAssignmentApplyTechnonogyVO> getTechnologies() {
		return technologies;
	}

	public void setTechnologies(List<ZMyAssignmentApplyTechnonogyVO> technologies) {
		this.technologies = technologies;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getLatestApplicantAvatar() {
		return latestApplicantAvatar;
	}

	public void setLatestApplicantAvatar(String latestApplicantAvatar) {
		this.latestApplicantAvatar = latestApplicantAvatar;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	static public ZMyAssignmentVO fromZMyAssignment(ZMyAssignment a) {
		if(null==a) {
			return null;
		}

		ZMyAssignmentVO vo = new ZMyAssignmentVO();
		return ZMyAssignmentVO.fromZMyAssignment(vo, a);
	}

	static public <T extends ZMyAssignmentVO> T fromZMyAssignment(T vo, ZMyAssignment a) {
		if(null==a) {
			return null;
		}

		CopyOptions copyOptions = CopyOptions.create().setIgnoreNullValue(true); // 空值不拷贝
		BeanUtil.copyProperties(a, vo, false, copyOptions);
		if(StringUtils.isNotBlank(a.getTechnologyJson())) {
			try {
				List<ZMyAssignmentApplyTechnonogyVO> technologies = JSON.parseArray(a.getTechnologyJson(), ZMyAssignmentApplyTechnonogyVO.class);
				vo.setTechnologies(technologies);
			} catch (Exception e) {
				log.error("getTechnologyJso parseArra err: {}", a.getId());
				log.error("getTechnologyJso parseArra err2: ", e);
			}
		}

		return ZMyAssignmentVO.makeValidityTimeAndStatus(vo, a.getReleaseTime(), a.getValidityTime());
	}

	/**
	 * 计算还有多久过期（秒）
	 * @author yeyi
	 * @date 2019/9/11 17:06
	 **/
	static public <T extends ZMyAssignmentVO> T makeValidityTimeAndStatus(T vo, final Date releaseTime, final int validityTime) {
		if(AssignmentStatusEnum.ACTIVE.getValue()!=vo.getStatus()) {
			return vo;
		}

		// 过期时间返回的是还有多少秒过期，与数据库存的不一样
		log.info("makeValidityTimeAndStatu in : {}", releaseTime);
		Date curTime = new Date();
		final Integer hour = validityTime; // 是写死的过期小时数（72）
		final long releaseUntilNow = curTime.getTime()-releaseTime.getTime(); // 发布到现在过了多少毫秒

		// 是否过期
		if(releaseUntilNow>1000*60*60*hour) {
			// 定时器没及时扫到可能会有这种情况
			log.error("makeValidityTimeAndStatu Expired: {}", vo.getId());
			vo.setStatus(AssignmentStatusEnum.EXPIRED.getValue()); // 已过期
		} else { // 没过期计算还有多少秒过期
			vo.setValidityTime(60*60*hour - releaseUntilNow/1000);
			log.info("makeValidityTimeAndStatu out: {}", vo.getValidityTime());
		}

		return vo;
	}
}