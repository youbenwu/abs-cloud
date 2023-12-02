package com.outmao.ebs.jnet.dto.assignment;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author yeyi
 * @date 2019/09/01 11:57
 **/
@ApiModel(value = "AssignmentSaveParamsDTO", description = "分页搜索我的外发列表参数")
public class AssignmentPageSearchDTO implements Serializable {

    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3707049596399027460L;
	
	@ApiModelProperty(name = "userId", value = "要查谁的外发列表,当前用户则不用传")
	private Long userId;
	
	@ApiModelProperty(name = "status", value = "0生效中 1已到期 2已满员（上限50人） 3已结束 4已取消 5待完成(包含1，2), 不传就是全部")
	private Byte status;

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
    

}
