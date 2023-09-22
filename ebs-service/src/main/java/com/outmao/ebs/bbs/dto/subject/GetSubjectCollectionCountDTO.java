package com.outmao.ebs.bbs.dto.subject;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel(value = "GetSubjectCollectionCountDTO", description = "获取用户收藏主题数量")
@Data
public class GetSubjectCollectionCountDTO {

    @ApiModelProperty(name = "userId", value = "用户ID",required = true)
    private Long userId;

    @ApiModelProperty(name = "itemTypes", value = "绑定的业务类型",required = true)
    private List<String> itemTypes;

}
