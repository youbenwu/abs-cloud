package com.outmao.ebs.jnet.vo.assignment;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.outmao.ebs.jnet.entity.asssignment.QZMyAssignment;
import com.outmao.ebs.jnet.util.DateUtil;
import com.querydsl.core.Tuple;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @author yeyi
 * @date 2019年9月7日
 */
@ApiModel(value = "ZMyAssignmentDetailVO", description = "我的外发详情")
@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class ZMyAssignmentDetailVO extends ZMyAssignmentVO implements Serializable{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(name = "applicants", value = "外发申请人列表")
	private List<ZMyAssignmentApplyVO> applicants;
	
	@ApiModelProperty(name = "selectedApplicants", value = "被选中的申请人列表,最多六个")
	private List<ZMyAssignmentApplyVO> selectedApplicants;
	
	@ApiModelProperty(name = "releaseTime", value = "发布时间")
	private String releaseTime;
	
	@ApiModelProperty(name = "visitsNum", value = "浏览数")
	private Long visitsNum = 0L;
	
	@ApiModelProperty(name = "province", value = "省")
	private String province;
	
	@ApiModelProperty(name = "city", value = "市")
	private String city;
	
	@ApiModelProperty(name = "area", value = "区")
	private String area;
	
	@ApiModelProperty(name = "address", value = "不包括省市区的详细地址")
	private String address;
	
	@ApiModelProperty(name = "longitude", value = "经度")
	private String longitude;
	
	@ApiModelProperty(name = "latitude", value = "纬度")
	private String latitude;

	@ApiModelProperty(name = "categoryId", value = "品类ID(z_production_category.id)")
	private Long categoryId;

	@ApiModelProperty(name = "categoryName", value = "品类名")
	private String categoryName;
	
//	private Date updateTime;
//	private Date createTime;
//	private Byte isDeleted;0未删除 1已删除
//	private Long userId;所属用户ID(ebs_user.id)

	public String getReleaseTime() {
		return releaseTime;
	}

	public List<ZMyAssignmentApplyVO> getApplicants() {
		return applicants;
	}

	public void setApplicants(List<ZMyAssignmentApplyVO> applicants) {
		this.applicants = applicants;
	}

	public List<ZMyAssignmentApplyVO> getSelectedApplicants() {
		return selectedApplicants;
	}

	public void setSelectedApplicants(List<ZMyAssignmentApplyVO> selectedApplicants) {
		this.selectedApplicants = selectedApplicants;
	}

	public void setReleaseTime(String releaseTime) {
		this.releaseTime = releaseTime;
	}

	public Long getVisitsNum() {
		return visitsNum;
	}

	public void setVisitsNum(Long visitsNum) {
		this.visitsNum = visitsNum;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public static ZMyAssignmentDetailVO fromTuple(Tuple t, QZMyAssignment e) {
		ZMyAssignmentDetailVO result = new ZMyAssignmentDetailVO();
		result.setApplicantNum(t.get(e.applicantNum));
		result.setAddress(t.get(e.address));
		result.setArea(t.get(e.area));
		result.setCity(t.get(e.city));
		result.setLatestApplicantAvatar(t.get(e.latestApplicantAvatar));
		result.setLatitude(t.get(e.latitude));
		result.setLongitude(t.get(e.longitude));
		result.setProvince(t.get(e.province));
		result.setReleaseTime(DateUtil.dateToStr(t.get(e.releaseTime)));
		result.setVisitsNum(t.get(e.visitsNum));
//		result.setAssignmentApplyId(t.getSubStatus(e.ass));
		result.setId(t.get(e.id));
		result.setImgUrl(t.get(e.imgUrl));
//		result.getPrice()
		result.setStatus(t.get(e.status));
		result.setText(t.get(e.text));
		result.setType(t.get(e.type));
		result.setUserId(t.get(e.userId));
		result.setCategoryId(t.get(e.categoryId));
		result.setUserType(t.get(e.userType));

		return (ZMyAssignmentDetailVO) ZMyAssignmentVO.makeValidityTimeAndStatus(result, t.get(e.releaseTime), t.get(e.validityTime));
	}
}