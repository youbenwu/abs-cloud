package com.outmao.ebs.jnet.vo.index;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.outmao.ebs.common.dsl.DslVO;
import com.outmao.ebs.jnet.entity.index.QIndexBanner;
import com.querydsl.core.Tuple;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 首页 banner
 * @author yeyi
 * @date 2019/10/19 14:49
 **/
@ApiModel(value = "BannerVO", description = "首页 banner")
@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class BannerVO implements DslVO<QIndexBanner>, Serializable {

    @ApiModelProperty(name = "id", value = "自动编号")
    private Long id;

    @ApiModelProperty(name = "updateTime", value = "updateTime")
    private Date updateTime;

    @ApiModelProperty(name = "createTime", value = "createTime")
    private Date createTime;

    @ApiModelProperty(name = "isDeleted", value = "0未删除 1已删除")
    private Boolean isDeleted;
    
    @ApiModelProperty(name = "imgUrl", value = "图片 url")
    private String imgUrl;

    @ApiModelProperty(name = "remark", value = "备注")
    private String remark;

    @Override
    public DslVO<QIndexBanner> fromTuple(Tuple t, QIndexBanner e) {
        this.id = t.get(e.id);
        this.createTime = t.get(e.createTime);
        this.isDeleted = t.get(e.isDeleted);
        this.updateTime = t.get(e.updateTime);
        this.imgUrl = t.get(e.imgUrl);
        this.setRemark(t.get(e.remark));
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
