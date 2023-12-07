package com.outmao.ebs.hotel.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 *
 * 设备
 *
 */
@Data
public class HotelDeviceDTO {


    /**
     *
     * 自动编号
     *
     */
    private Long id;


    @ApiModelProperty(name = "hotelId", value = "酒店ID")
    private Long hotelId;

    /**
     * 房间号
     */
    @ApiModelProperty(name = "roomNo", value = "房间号")
    private String roomNo;


    /**
     * 设备号
     */
    @ApiModelProperty(name = "deviceNo", value = "设备号")
    private String deviceNo;

    /**
     * 设备名称
     */
    @ApiModelProperty(name = "name", value = "设备名称")
    private String name;

    /**
     * 设备型号
     */
    @ApiModelProperty(name = "model", value = "设备型号")
    private String model;

    /**
     * 设备系统
     */
    @ApiModelProperty(name = "os", value = "设备系统")
    private String os;


}
