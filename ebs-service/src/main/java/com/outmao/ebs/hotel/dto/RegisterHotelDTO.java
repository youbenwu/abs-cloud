package com.outmao.ebs.hotel.dto;

import com.outmao.ebs.common.vo.BaseDTO;
import com.outmao.ebs.common.vo.Contact;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@ApiModel(value = "RegisterHotelDTO", description = "注册酒店信息")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegisterHotelDTO extends BaseDTO {


    /**
     *
     * 酒店所有者用户ID
     *
     */
    @ApiModelProperty(name = "userId", value = "酒店所有者用户ID，新增如果不传则用联系人手机号所属用户作为酒店所有者")
    private Long userId;

    /**
     *
     * 酒店名称
     *
     */
    @ApiModelProperty(name = "name", value = "酒店名称")
    private String name;

    /**
     *
     * 酒店简介
     *
     */
    @ApiModelProperty(name = "intro", value = "酒店简介")
    private String intro;

    /**
     *
     * 酒店LOGO
     *
     */
    @ApiModelProperty(name = "logo", value = "酒店LOGO")
    private String logo;

    /**
     *
     * 酒店图片
     *
     */
    @ApiModelProperty(name = "image", value = "酒店图片")
    private String image;

    /**
     *
     * 营业执照
     *
     */
    @ApiModelProperty(name = "license", value = "营业执照")
    private String license;

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


    /**
     *
     * 联系信息
     *
     */
    @ApiModelProperty(name = "contact", value = "联系信息")
    private Contact contact;


    @ApiModelProperty(name = "password", value = "密码")
    private String password;


}
