package com.outmao.ebs.portal.dto;



import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "SetAdvertStatusDTO", description = "设置广告状态")
@Data
public class SetAdvertStatusDTO {

    @ApiModelProperty(name = "id", value = "ID")
    private Long id;

    @ApiModelProperty(name = "status", value = "状态 0--正常 2--未审核 3--审核中 4--审核失败 7--过期")
    private int status;

}
