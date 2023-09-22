package com.outmao.ebs.bbs.dto.post;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "SetPostStatusDTO", description = "设置贴子状态参数")
@Data
public class SetPostStatusDTO {


    @ApiModelProperty(name = "id", value = "贴子ID")
    private Long id;

    @ApiModelProperty(name = "status", value = "状态 0--正常 1--禁用 2--未审核 3--审核中 4--审核成功 5--审核失败 6--删除")
    private int status;

}
