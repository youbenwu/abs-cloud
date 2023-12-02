package com.outmao.ebs.jnet.dto.assignment;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author yeyi
 * @date 2019/8/29 11:57
 **/
@ApiModel(value = "AssignmentSaveParamsDTO", description = "创建、修改我的外发参数")
public class AssignmentSaveParamsDTO implements Serializable {

    private static final long serialVersionUID = 3283865606597099814L;

    @ApiModelProperty(name = "id", value = "外发ID，创建时不传")
    private Long id;

    @ApiModelProperty(name = "text", value = "正文少于500字符")
    @Size(min=1, max=500)
    private String text;

    @ApiModelProperty(name = "imgUrl", value = "图片url,0~9张，英文逗号分隔")
    @Size(min=1, max=1024)
    private String imgUrl;

    @ApiModelProperty(name = "categoryId", value = "品类ID(z_production_category.id)")
    @NotNull
    private Long categoryId;

    @ApiModelProperty(name = "province", value = "省字符")
    @Size(min=1, max=32)
    @NotNull
    private String province;

    @ApiModelProperty(name = "city", value = "市字符")
    @Size(min=1, max=32)
    @NotNull
    private String city;

    @ApiModelProperty(name = "area", value = "区字符")
    @Size(min=1, max=32)
    @NotNull
    private String area;

    @ApiModelProperty(name = "address", value = "不包括省市区的详细地址")
    @Size(min=1, max=64)
    @NotNull
    private String address;

    @ApiModelProperty(name = "longitude", value = "经度")
    @Size(min=1, max=32)
    @NotNull
    private String longitude;

    @ApiModelProperty(name = "latitude", value = "纬度")
    @Size(min=1, max=32)
    @NotNull
    private String latitude;
    
    @ApiModelProperty(name = "technologyJson", value = "工艺ID与工艺名json <= 256字符: [{\"id\":1504,\"name\":\"18针\"}]")
    @Size(min=1, max=256)
    @NotNull
    private String technologyJson;
    
    @ApiModelProperty(name = "userType", value = "0普通外发 13机器人外发")
    private Integer userType;

    /**
     * 最近 20 个申请人头像 url, 用英文逗号分隔，机器人用,正常参数忽略
     */
    private String latestApplicantAvatar;

    /**
     * 访问次数，机器人用,正常参数忽略
     */
    private Long visitsNum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
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

	public String getTechnologyJson() {
		return technologyJson;
	}

	public void setTechnologyJson(String technologyJson) {
		this.technologyJson = technologyJson;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

    public String getLatestApplicantAvatar() {
        return latestApplicantAvatar;
    }

    public void setLatestApplicantAvatar(String latestApplicantAvatar) {
        this.latestApplicantAvatar = latestApplicantAvatar;
    }

    public Long getVisitsNum() {
        return visitsNum;
    }

    public void setVisitsNum(Long visitsNum) {
        this.visitsNum = visitsNum;
    }
}
