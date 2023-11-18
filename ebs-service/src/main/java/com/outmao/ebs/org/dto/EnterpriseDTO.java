package com.outmao.ebs.org.dto;


import com.outmao.ebs.org.entity.enterprise.EnterpriseBasicInformation;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class EnterpriseDTO extends EnterpriseBasicInformation {

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
