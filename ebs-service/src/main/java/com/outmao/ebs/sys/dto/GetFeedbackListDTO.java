package com.outmao.ebs.sys.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GetFeedbackListDTO {

    //0--用户反馈 1--用户投诉
    @ApiModelProperty(name = "type", value = "10--迁眼聊天-异常反馈 11--迁眼聊天-用户投诉")
    private Integer type;

    private Integer status;


}
