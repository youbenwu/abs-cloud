package com.outmao.ebs.message.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.outmao.ebs.common.vo.BindingItem;
import com.outmao.ebs.user.common.data.SimpleUserSetter;
import com.outmao.ebs.user.vo.SimpleUserVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(value = Include.NON_NULL)
@ApiModel(value = "MessageVO", description = "消息对象")
public class MessageVO implements SimpleUserSetter {

	@ApiModelProperty(name = "id", value = "消息ID")
	private Long id;

	@ApiModelProperty(name = "status", value = "状态")
	private int status;

	@ApiModelProperty(name = "statusRemark", value = "状态 0--未发送，1--发送中，2--已发送，3--发送失败，4--己删除")
	private String statusRemark;

	@ApiModelProperty(name = "fromId", value = "发送用户ID")
	private Long fromId;

	@ApiModelProperty(name = "from", value = "发送用户")
	private SimpleUserVO from;

	@ApiModelProperty(name = "tos", value = "接收用户ID列表JSON，空则为全部用户")
	private String tos;

	@ApiModelProperty(name = "sendType", value = "发送类型 0--站内信 1--电子邮件 2--短信 3--推送")
	private int sendType;

	@ApiModelProperty(name = "type", value = "消息类型")
	private String type;

	// 关联的对象
	@ApiModelProperty(name = "item", value = "关联的对象")
	private BindingItem item;

	@ApiModelProperty(name = "url", value = "URL")
	private String url;

	@ApiModelProperty(name = "title", value = "消息标题")
	private String title;

	@ApiModelProperty(name = "content", value = "消息内容")
	private String content;

	@ApiModelProperty(name = "image", value = "消息图片")
	private String image;

	/**
	 *
	 * 按钮名称
	 *
	 */
	@ApiModelProperty(name = "action", value = "按钮名称")
	private String action;

	@ApiModelProperty(name = "createTime", value = "创建时间")
	private Date createTime;

	@ApiModelProperty(name = "updateTime", value = "更新时间")
	private Date updateTime;

	@JsonIgnore
	@Override
	public Long getUserId() {
		return fromId;
	}

	@Override
	public void setUser(SimpleUserVO user) {
        from=user;
	}

}
