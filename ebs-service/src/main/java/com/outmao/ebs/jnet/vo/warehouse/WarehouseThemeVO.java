package com.outmao.ebs.jnet.vo.warehouse;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.outmao.ebs.common.dsl.DslVO;
import com.outmao.ebs.jnet.entity.warehouse.QWarehouseTheme;
import com.querydsl.core.Tuple;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 仓库主题
 * @author yeyi
 * @date 2019/9/23 14:49
 **/
@ApiModel(value = "WarehouseThemeVO", description = "仓库主题")
@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class WarehouseThemeVO implements DslVO<QWarehouseTheme> {

    @ApiModelProperty(name = "id", value = "自动编号")
    private Long id;

    @ApiModelProperty(name = "updateTime", value = "updateTime")
    private Date updateTime;

    @ApiModelProperty(name = "createTime", value = "createTime")
    private Date createTime;

    @ApiModelProperty(name = "isDeleted", value = "0未删除 1已删除")
    private Boolean isDeleted;

    @ApiModelProperty(name = "imgUrl", value = "主题图片 url")
    private String imgUrl;

    @ApiModelProperty(name = "bgcolor", value = "背景色")
    private String bgcolor;

    @Override
    public DslVO<QWarehouseTheme> fromTuple(Tuple t, QWarehouseTheme e) {
        this.id = t.get(e.id);
        this.bgcolor = t.get(e.bgcolor);
        this.createTime = t.get(e.createTime);
        this.imgUrl = t.get(e.imgUrl);
        this.isDeleted = t.get(e.isDeleted);
        this.updateTime = t.get(e.updateTime);
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

    public String getBgcolor() {
        return bgcolor;
    }

    public void setBgcolor(String bgcolor) {
        this.bgcolor = bgcolor;
    }
}
