package com.outmao.ebs.portal.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GetChannelListDTO {


    private Long orgId;

    @ApiModelProperty(name = "type", value = "0--总后台 1--酒店")
    private Integer type;

}
