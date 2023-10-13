package com.outmao.ebs.portal.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GetAdvertChannelListDTO {


    private Long orgId;

    @ApiModelProperty(name = "type", value = "投放是否收费 0--免费 1--收费")
    private Integer type;



}
