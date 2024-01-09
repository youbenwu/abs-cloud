package com.outmao.ebs.hotel.dto;

import com.outmao.ebs.common.vo.BaseDTO;
import com.outmao.ebs.common.vo.Contact;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


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
     * 酒店视频
     *
     */
    @ApiModelProperty(name = "movie", value = "酒店视频")
    private String video;

    /**
     *
     *
     *
     */
    private String roomImages;

    /**
     *
     *
     *
     */
    private String images;

    /**
     *
     * 酒店服务说明
     *
     */
    @ApiModelProperty(name = "business", value = "酒店服务说明")
    private String business;

    /**
     *
     * 星级 1～5
     *
     */
    @ApiModelProperty(name = "star", value = "星级 1～5")
    private int star;

    /**
     *
     *  酒店标记
     *
     */
    @ApiModelProperty(name = "mark", value = "酒店标记")
    private String mark;

    /**
     *
     *  酒店成立时间
     *
     */
    @ApiModelProperty(name = "estTime", value = "酒店成立时间")
    private Date estTime;

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
