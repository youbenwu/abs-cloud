package com.outmao.ebs.hotel.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@ApiModel(value = "RegisterHotelDTO", description = "注册酒店信息")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegisterHotelDTO extends HotelDTO {

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





}
