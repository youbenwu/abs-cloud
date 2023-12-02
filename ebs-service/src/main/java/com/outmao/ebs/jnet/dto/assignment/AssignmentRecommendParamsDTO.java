package com.outmao.ebs.jnet.dto.assignment;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author yeyi
 * @date 2019/10/8 11:33
 **/
@ApiModel(value = "AssignmentRecommendParamsDTO", description = "推荐外发参数")
public class AssignmentRecommendParamsDTO implements Serializable {

	private static final long serialVersionUID = 5065300913464190189L;
    @ApiModelProperty(name = "resultLength", value = "需要返回多少个随机数据，不传默认6")
	private Long resultLength;

	@ApiModelProperty(name = "longitude", value = "当前经度，如果没登录则必须传")
	private String longitude;

	@ApiModelProperty(name = "latitude", value = "当前纬度，如果没登录则必须传")
	private String latitude;

	@ApiModelProperty(name = "userId", value = "当前登录用户ID，没登录不传")
	private Long userId;

	public Long getResultLength() {
		return resultLength;
	}

	public void setResultLength(Long resultLength) {
		this.resultLength = resultLength;
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
