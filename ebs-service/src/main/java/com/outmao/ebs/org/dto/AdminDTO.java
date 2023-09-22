package com.outmao.ebs.org.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@ApiModel(value = "AdminDTO", description = "保存管理员信息")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AdminDTO {

    @ApiModelProperty(name = "id", value = "ID")
    private Long id;

    @ApiModelProperty(name = "orgId", value = "组织ID")
    private Long orgId;

    @ApiModelProperty(name = "userId", value = "用户ID")
    private Long userId;

    @ApiModelProperty(name = "name", value = "名称")
    private String name;

    @ApiModelProperty(name = "phone", value = "手机")
    private String phone;

    @ApiModelProperty(name = "role", value = "角色")
    private String role;

}
