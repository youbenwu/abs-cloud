package com.outmao.ebs.hotel.dto;

import com.outmao.ebs.common.vo.BaseDTO;
import com.outmao.ebs.common.vo.Contact;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@ApiModel(value = "HotelDTO", description = "保存酒店信息")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class HotelDTO extends BaseDTO {

    @ApiModelProperty(name = "id", value = "酒店ID")
    private Long id;


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
     * 联系信息
     *
     */
    @ApiModelProperty(name = "contact", value = "联系信息")
    private Contact contact;


}
