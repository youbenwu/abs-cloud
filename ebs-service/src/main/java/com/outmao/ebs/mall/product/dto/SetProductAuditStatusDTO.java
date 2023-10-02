package com.outmao.ebs.mall.product.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "SetProductAuditStatusDTO", description = "设置商品审核状态")
@Data
public class SetProductAuditStatusDTO {


    @ApiModelProperty(name = "id", value = "商品ID",required = true)
    private Long id;
    /**
     *
     * 审核状态(0--正常 1--禁用 2--未审核 3--审核中 4--审核成功 5--审核失败 6--删除)
     *
     */
    @ApiModelProperty(name = "auditStatus", value = "审核状态(0--正常 1--禁用 2--未审核 3--审核中 4--审核成功 5--审核失败 6--删除)")
    private Integer auditStatus;



}
