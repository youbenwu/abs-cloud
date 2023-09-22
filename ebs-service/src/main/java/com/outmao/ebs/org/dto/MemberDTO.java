package com.outmao.ebs.org.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {

    @ApiModelProperty(name = "id", value = "ID，新增不传")
    private Long id;

    @ApiModelProperty(name = "orgId", value = "组织ID",required = true)
    private Long orgId;

    @ApiModelProperty(name = "userId", value = "所属用户ID，不传则用手机号注册新用户")
    private Long userId;

    /**
     *
     * 名称
     *
     */
    @ApiModelProperty(name = "name", value = "名称",required = true)
    private String name;

    /**
     *
     * 头像
     *
     */
    @ApiModelProperty(name = "avatar", value = "头像")
    private String avatar;

    /**
     *
     * 手机号
     *
     */
    @ApiModelProperty(name = "phone", value = "手机号",required = true)
    private String phone;

    /**
     *
     * 电子邮箱
     *
     */
    @ApiModelProperty(name = "email", value = "电子邮箱")
    private String email;
}
