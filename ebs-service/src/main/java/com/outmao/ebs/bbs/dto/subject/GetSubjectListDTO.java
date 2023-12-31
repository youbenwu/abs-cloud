package com.outmao.ebs.bbs.dto.subject;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "GetSubjectListDTO", description = "获取主题列表参数")
@Data
public class GetSubjectListDTO {

    @ApiModelProperty(name = "itemType", value = "绑定对象类型")
    private String itemType;

    @ApiModelProperty(name = "type", value = "0--论坛 1--朋友圈 2--商品评价")
    private Integer type;

}
