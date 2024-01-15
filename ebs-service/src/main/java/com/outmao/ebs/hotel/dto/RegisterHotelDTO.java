package com.outmao.ebs.hotel.dto;

import cn.jiguang.common.utils.StringUtils;
import com.outmao.ebs.user.common.constant.Oauth;
import com.outmao.ebs.user.common.data.RegisterUser;
import com.outmao.ebs.user.dto.RegisterDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;


@ApiModel(value = "RegisterHotelDTO", description = "注册酒店信息")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegisterHotelDTO extends HotelDTO implements RegisterUser {

    /**
     *
     * 酒店所有者用户ID
     *
     */
    @ApiModelProperty(name = "userId", value = "酒店所有者用户ID，新增如果不传则用联系人手机号所属用户作为酒店所有者")
    private Long userId;

    /**
     *
     * 身份证号码
     *
     */
    @ApiModelProperty(name = "idCardNo", value = "身份证号码")
    private String idCardNo;

    /**
     *
     * 身份证正面
     *
     */
    @ApiModelProperty(name = "idCardFront", value = "身份证正面")
    private String idCardFront;

    /**
     *
     * 身份证反面
     *
     */
    @ApiModelProperty(name = "idCardBack", value = "身份证反面")
    private String idCardBack;



    @ApiModelProperty(name = "password", value = "密码")
    private String password;

    @Override
    public RegisterDTO getRegisterRequest() {
        if(StringUtils.isEmpty(getContact().getPhone()))
            return null;
        RegisterDTO registerDTO=new RegisterDTO();
        registerDTO.setPrincipal(getContact().getPhone());
        registerDTO.setCredentials(password);
        registerDTO.setOauth(Oauth.PHONE.getName());
        registerDTO.setArgs(new HashMap<>());
        registerDTO.getArgs().put("nickname",getContact().getName());
        return registerDTO;
    }

}
