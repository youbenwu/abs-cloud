package com.outmao.ebs.bbs.dto.comment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "GetCommentListDTO", description = "获取评论列表参数")
@Data
public class GetCommentListDTO {

    @ApiModelProperty(name = "postId", value = "贴子ID")
    private Long postId;

    @ApiModelProperty(name = "toId", value = "被回复评论ID")
    private Long toId;

}
