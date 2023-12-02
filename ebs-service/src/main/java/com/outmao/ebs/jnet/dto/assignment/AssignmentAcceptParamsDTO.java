package com.outmao.ebs.jnet.dto.assignment;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @author yeyi
 * @date 2019/9/11 21:33
 **/
@ApiModel(value = "AssignmentAcceptParamsDTO", description = "接受外发参数")
public class AssignmentAcceptParamsDTO implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 116781417846536444L;

	@NotNull
    @ApiModelProperty(name = "assignmentId", value = "外发单ID")
	private Long assignmentId;

	@ApiModelProperty(name = "applyIds", value = "选中的申请ID")
	private List<Long> applyIds;

	public Long getAssignmentId() {
		return assignmentId;
	}

	public void setAssignmentId(Long assignmentId) {
		this.assignmentId = assignmentId;
	}

	public List<Long> getApplyIds() {
		return applyIds;
	}

	public void setApplyIds(List<Long> applyIds) {
		this.applyIds = applyIds;
	}
	
}
