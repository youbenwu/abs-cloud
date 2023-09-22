package com.outmao.ebs.hotel.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 *
 * 客人
 *
 */
@Data
public class HotelCustomerDTO {


    /**
     *
     * 自动编号
     *
     */
    private Long id;


    @ApiModelProperty(name = "hotelId", value = "酒店ID" ,required = true)
    private Long hotelId;


    /**
     *
     * 用户ID
     *
     */
    @ApiModelProperty(name = "userId", value = "用户ID，不传则用手机号查找")
    private Long userId;


    /**
     *
     * 姓名
     *
     */
    @ApiModelProperty(name = "name", value = "姓名",required = true)
    private String name;

    /**
     *
     * 手机号
     *
     */
    @ApiModelProperty(name = "phone", value = "手机号",required = true)
    private String phone;



}
