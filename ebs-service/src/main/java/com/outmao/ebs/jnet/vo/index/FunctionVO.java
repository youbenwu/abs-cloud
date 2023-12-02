package com.outmao.ebs.jnet.vo.index;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.outmao.ebs.common.dsl.DslVO;
import com.outmao.ebs.jnet.entity.index.QFunction;
import com.querydsl.core.Tuple;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 功能模块
 * @author yeyi
 * @date 2019/9/23 14:49
 **/
@ApiModel(value = "FunctionVO", description = "功能模块")
@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class FunctionVO implements DslVO<QFunction> {

    @ApiModelProperty(name = "id", value = "自动编号")
    private Long id;

    @ApiModelProperty(name = "updateTime", value = "updateTime")
    private Date updateTime;

    @ApiModelProperty(name = "createTime", value = "createTime")
    private Date createTime;

    @ApiModelProperty(name = "isDeleted", value = "0未删除 1已删除")
    private Boolean isDeleted;

    @ApiModelProperty(name = "name", value = "模块名")
    private String name;

    @ApiModelProperty(name = "useCount", value = "使用次数")
    private Long useCount;
    
    @ApiModelProperty(name = "imgUrl", value = "图片 url")
    private String imgUrl;
    
    @ApiModelProperty(name = "pageUrl", value = "页面路径")
    private String pageUrl;

    @ApiModelProperty(name = "bannerUrl", value = "banner 图片，多个用英文逗号分隔")
    private String bannerUrl;

    @Override
    public DslVO<QFunction> fromTuple(Tuple t, QFunction e) {
        this.id = t.get(e.id);
        this.name = t.get(e.name);
        this.createTime = t.get(e.createTime);
        this.useCount = t.get(e.useCount);
        this.isDeleted = t.get(e.isDeleted);
        this.updateTime = t.get(e.updateTime);
        this.imgUrl = t.get(e.imgUrl);
        this.pageUrl = t.get(e.pageUrl);
        this.bannerUrl = t.get(e.bannerUrl);
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUseCount() {
        return useCount;
    }

    public void setUseCount(Long useCount) {
        this.useCount = useCount;
    }

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }
}
