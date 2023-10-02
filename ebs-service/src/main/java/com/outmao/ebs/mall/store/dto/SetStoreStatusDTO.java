package com.outmao.ebs.mall.store.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "SetStoreStatusDTO", description = "设置门店状态")
@Data
public class SetStoreStatusDTO {

    /**
     *
     * 门店ID
     *
     */
    @ApiModelProperty(name = "id", value = "门店ID")
    private Long id;

    @ApiModelProperty(name = "status", value = "状态 0--正常 1--禁用 2--未审核 3--审核中 4--审核成功 5--审核失败 6--删除",required = true)
    private int status;

    @ApiModelProperty(name = "statusRemark", value = "状态",required = true)
    private String statusRemark;


}
