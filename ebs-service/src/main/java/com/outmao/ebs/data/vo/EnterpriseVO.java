package com.outmao.ebs.data.vo;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.outmao.ebs.data.entity.enterprise.EnterpriseBasicInformation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "EnterpriseVO", description = "企业信息")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
public class EnterpriseVO extends EnterpriseBasicInformation {

    @ApiModelProperty(name = "id", value = "ID")
    private Long id;

    /**
     *
     * 用户
     *
     */
    @ApiModelProperty(name = "userId", value = "用户")
    private Long userId;

    /**
     *
     * 状态
     *
     */
    @ApiModelProperty(name = "status", value = "状态")
    private int status;

    /**
     *
     * 状态
     *
     */
    @ApiModelProperty(name = "statusRemark", value = "状态")
    private String statusRemark;




}
