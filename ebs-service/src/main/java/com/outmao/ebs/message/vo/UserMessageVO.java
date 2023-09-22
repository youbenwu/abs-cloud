package com.outmao.ebs.message.vo;

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
@ApiModel(value = "UserMessageVO", description = "用户消息对象")
public class UserMessageVO implements SimpleUserSetter {

	@ApiModelProperty(name = "id", value = "消息ID")
	private Long id;

	// 0未读1已读
	@ApiModelProperty(name = "status", value = "0未读1已读")
	private int status;

	@ApiModelProperty(name = "fromId", value = "发送用户ID")
	private Long fromId;

	@ApiModelProperty(name = "from", value = "发送用户")
	private SimpleUserVO from;

	@ApiModelProperty(name = "toId", value = "接收用户ID")
	private Long toId;

	@ApiModelProperty(name = "type", value = "消息类型")
	private String type;

	private int sendType;
	private String target;

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

	@ApiModelProperty(name = "createTime", value = "创建时间")
	private Date createTime;

	@ApiModelProperty(name = "updateTime", value = "更新时间")
	private Date updateTime;


	@Override
	public Long getUserId() {
		return fromId;
	}

	@Override
	public void setUser(SimpleUserVO user) {
       from=user;
	}

}
