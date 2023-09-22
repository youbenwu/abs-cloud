package com.outmao.ebs.bbs.dto.post;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "GetCollectionPostListDTO", description = "查找用户收藏的贴子参数")
@Data
public class GetCollectionPostListDTO {

    @ApiModelProperty(name = "userId", value = "收藏用户ID")
    private Long userId;


}
