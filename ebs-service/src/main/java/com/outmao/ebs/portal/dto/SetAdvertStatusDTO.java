package com.outmao.ebs.portal.dto;



import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "SetAdvertStatusDTO", description = "设置广告状态")
@Data
public class SetAdvertStatusDTO {

    @ApiModelProperty(name = "id", value = "ID")
    private Long id;

    @ApiModelProperty(name = "status", value = "0--未上架 1--已上架")
    private int status;

}
