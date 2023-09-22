package com.outmao.ebs.bbs.dto.post;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@ApiModel(value = "SetPostTopDTO", description = "设置帖子在主题中置顶")
@Data
public class SetPostTopDTO {

    @ApiModelProperty(name = "userId", value = "主题用户ID")
    private Long userId;

    @ApiModelProperty(name = "id", value = "贴子ID")
    private Long id;

    @ApiModelProperty(name = "top", value = "是否置顶")
    private boolean top;

}
