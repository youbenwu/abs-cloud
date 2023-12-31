package com.outmao.ebs.hotel.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class HotelRoomDTO {

    /**
     *
     * 自动编号
     *
     */
    private Long id;

    @ApiModelProperty(name = "hotelId", value = "酒店ID" ,required = true)
    private Long hotelId;

    @ApiModelProperty(name = "typeId", value = "房型ID")
    private Long typeId;


    /**
     *
     * 房间号
     *
     */
    @ApiModelProperty(name = "roomNo", value = "房间号",required = true)
    private String roomNo;

    /**
     *
     * 房间名称
     *
     */
    @ApiModelProperty(name = "name", value = "name")
    private String name;

    /**
     *
     * 房间配置
     *
     */
    @ApiModelProperty(name = "intro", value = "房间配置")
    private String intro;

    /**
     *
     * 房间图片
     *
     */
    @ApiModelProperty(name = "image", value = "房间图片")
    private String image;



}
