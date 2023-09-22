package com.outmao.ebs.bbs.dto.subject;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "GetCollectionSubjectListDTO", description = "查找用户收藏的主题参数")
@Data
public class GetCollectionSubjectListDTO {


    @ApiModelProperty(name = "userId", value = "收藏用户ID")
    private Long userId;

    @ApiModelProperty(name = "itemType", value = "主题绑定业务对象类型")
    private String itemType;

}
