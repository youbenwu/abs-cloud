package com.outmao.ebs.org.dto;


import com.outmao.ebs.user.common.constant.Oauth;
import com.outmao.ebs.user.common.data.RegisterUser;
import com.outmao.ebs.user.dto.RegisterDTO;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.util.HashMap;


@Data
public class RegisterOrgDTO extends OrgDTO implements RegisterUser {

    /**
     *
     * 用户ID
     *
     */
    private Long userId;

    /**
     *
     * 父ID
     *
     */
    private Long parentId;

    /**
     *
     * 组织类型
     *
     */
    private int type;

    /**
     *
     * 组织类型对应ID
     */
    private Long targetId;

    /**
     *
     * 密码
     *
     */
    private String password;


    @Override
    public RegisterDTO getRegisterRequest() {
        if(getContact()==null)
            return null;
        String username=getContact().getName();
        String phone=getContact().getPhone();
        RegisterDTO registerDTO=new RegisterDTO();
        registerDTO.setPrincipal(StringUtils.isEmpty(phone)?username:phone);
        registerDTO.setCredentials(password);
        registerDTO.setOauth(StringUtils.isEmpty(phone)?Oauth.USERNAME.getName():Oauth.PHONE.getName());
        registerDTO.setArgs(new HashMap<>());
        registerDTO.getArgs().put("nickname",username);
        return registerDTO;
    }

}
