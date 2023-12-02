package com.outmao.ebs.jnet.dto.assignment;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * @author yeyi
 * @date 2019/9/2 21:33
 **/
@ApiModel(value = "AssignmentApplyParamsDTO", description = "申请外发参数")
public class AssignmentApplyParamsDTO implements Serializable {

	private static final long serialVersionUID = 8120223658688227121L;

//	@ApiModelProperty(name = "id", value = "申请ID，创建时不传")
//    private Long id;

	@NotNull
    @ApiModelProperty(name = "assignmentId", value = "要申请的外发单ID")
	private Long assignmentId;

	@Size(min=1, max=11)
	@NotNull
    @ApiModelProperty(name = "price", value = "报价，不能超过四位小数")
	private String price;
	
	@Size(min=1, max=99)
	@NotNull
	@ApiModelProperty(name = "technologyIds", value = "多个工艺ID")
	private List<Long> technologyIds;

//    @ApiModelProperty(name = "updateTime", value = "")
//	private Date updateTime;
//    @ApiModelProperty(name = "createTime", value = "申请时间")
//	private Date createTime;
//    @ApiModelProperty(name = "isDeleted", value = "0未删除 1已删除")
//	private Byte isDeleted; 
//  @ApiModelProperty(name = "userId", value = "申请者ID(ebs_user.id)")
//	private Long userId;
//  @ApiModelProperty(name = "id", value = "申请ID，创建时不传")
//	private Byte selected;	// 0未选中 1选中

//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}

	public Long getAssignmentId() {
		return assignmentId;
	}

	public void setAssignmentId(Long assignmentId) {
		this.assignmentId = assignmentId;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public List<Long> getTechnologyIds() {
		return technologyIds;
	}

	public void setTechnologyIds(List<Long> technologyIds) {
		this.technologyIds = technologyIds;
	}

}
