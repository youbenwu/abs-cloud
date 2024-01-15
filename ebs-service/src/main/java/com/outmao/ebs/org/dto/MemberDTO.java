package com.outmao.ebs.org.dto;


import cn.jiguang.common.utils.StringUtils;
import com.outmao.ebs.user.common.constant.Oauth;
import com.outmao.ebs.user.common.data.RegisterUser;
import com.outmao.ebs.user.dto.RegisterDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO implements RegisterUser {

    @ApiModelProperty(name = "id", value = "ID，新增不传")
    private Long id;

    @ApiModelProperty(name = "orgId", value = "组织ID",required = true)
    private Long orgId;

    @ApiModelProperty(name = "userId", value = "所属用户ID，不传则用手机号注册新用户")
    private Long userId;

    @ApiModelProperty(name = "types", value = "成员类型")
    private List<MemberTypeDTO> types;

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


    @ApiModelProperty(name = "password", value = "密码")
    private String password;


    @Override
    public RegisterDTO getRegisterRequest() {
        if(StringUtils.isEmpty(phone))
            return null;
        RegisterDTO registerDTO=new RegisterDTO();
        registerDTO.setPrincipal(phone);
        registerDTO.setCredentials(password);
        registerDTO.setOauth(Oauth.PHONE.getName());
        registerDTO.setArgs(new HashMap<>());
        registerDTO.getArgs().put("nickname",name);
        return registerDTO;
    }


}
