package com.outmao.ebs.bbs.dto.subject;


import com.outmao.ebs.common.vo.BindingItem;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "SubjectDTO", description = "保存主题参数")
@Data
public class SubjectDTO {

	@ApiModelProperty(name = "id", value = "主题ID")
	private Long id;

	@ApiModelProperty(name = "categoryId", value = "分类ID")
	private Long categoryId;

	@ApiModelProperty(name = "userId", value = "用户ID")
	private Long userId;

	@ApiModelProperty(name = "title", value = "标题")
	private String title;

	@ApiModelProperty(name = "content", value = "内容")
	private String content;

	@ApiModelProperty(name = "item", value = "绑定的业务对象")
	private BindingItem item;


}
