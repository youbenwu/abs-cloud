package com.outmao.ebs.org.dto;

import com.outmao.ebs.user.common.constant.Oauth;
import com.outmao.ebs.user.common.data.RegisterUser;
import com.outmao.ebs.user.dto.RegisterDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;


@ApiModel(value = "AccountDTO", description = "保存管理员信息")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AccountDTO implements RegisterUser {

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

    private String password;

    private List<Long> roles;

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
