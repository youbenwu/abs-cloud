package com.outmao.ebs.jnet.vo.assignment;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.outmao.ebs.common.dsl.DslVO;
import com.outmao.ebs.jnet.entity.asssignment.QZMyAssignmentApply;
import com.outmao.ebs.jnet.util.BigDecimalUtil;
import com.querydsl.core.Tuple;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * @author yeyi
 * @date 2019年9月3日
 */
@ApiModel(value = "ZMyAssignmentApplyVO", description = "外发申请")
@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class ZMyAssignmentApplyVO implements DslVO<QZMyAssignmentApply> {

    @ApiModelProperty(name = "id", value = "自动编号")
    private Long id;

    @ApiModelProperty(name = "updateTime", value = "")
    private Date updateTime;

    @ApiModelProperty(name = "createTime", value = "申请时间")
    private Date createTime;

    @ApiModelProperty(name = "assignmentId", value = "外发单ID(z_my_assignment.id)")
    private Long assignmentId;

    @ApiModelProperty(name = "userId", value = "申请者ID(ebs_user.id)")
    private Long userId;
    
    @ApiModelProperty(name = "userName", value = "申请者名字")
    private String userName;
    
    @ApiModelProperty(name = "realname", value = "申请者名字(与userName一样，登红要求加的)")
    private String realname;

    @ApiModelProperty(name = "avatar", value = "申请者头像")
    private String avatar;

    @ApiModelProperty(name = "selected", value = "false未选中 true选中")
    private Boolean selected;

    @ApiModelProperty(name = "price", value = "报价，最多四位小数")
    private String price;
    
	@ApiModelProperty(name = "longitude", value = "经度")
	private String longitude;
	
	@ApiModelProperty(name = "latitude", value = "纬度")
	private String latitude;
	
	@ApiModelProperty(name = "technologies", value = "工艺列表")
	private List<ZMyAssignmentApplyTechnonogyVO> technologies;

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

    public Long getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(Long assignmentId) {
        this.assignmentId = assignmentId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
		this.realname = userName;
	}

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<ZMyAssignmentApplyTechnonogyVO> getTechnologies() {
		return technologies;
	}

	public void setTechnologies(List<ZMyAssignmentApplyTechnonogyVO> technologies) {
		this.technologies = technologies;
	}
	
	public String getRealName() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	@Override
    public ZMyAssignmentApplyVO fromTuple(Tuple t, QZMyAssignmentApply e) {
        this.id = t.get(e.id);
        this.assignmentId = t.get(e.assignmentId);
        this.createTime = t.get(e.createTime);
        this.price = BigDecimalUtil.rounding4_5Cut0(t.get(e.price)).toString();
        this.selected = t.get(e.selected);
        this.updateTime = t.get(e.updateTime);
        this.userId = t.get(e.userId);
        return this;
    }
}
