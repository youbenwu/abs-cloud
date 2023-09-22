package com.outmao.ebs.hotel.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 *
 * 客服服务
 *
 */
@Data
public class HotelWorkOrderDTO {


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
     * 房间号
     *
     */
    @ApiModelProperty(name = "roomNo", value = "房间号",required = true)
    private String roomNo;


    /**
     *
     * 提交用户的ID
     *
     */
    @ApiModelProperty(name = "userId", value = "提交用户的ID")
    private Long userId;

    /**
     *
     * 提交用户的名称
     *
     */
    @ApiModelProperty(name = "userName", value = "提交用户的名称")
    private String userName;


    /**
     *
     * 提交用户的手机号
     *
     */
    @ApiModelProperty(name = "userPhone", value = "提交用户的手机号")
    private String userPhone;

    /**
     *
     * 服务名称
     *
     */
    @ApiModelProperty(name = "name", value = "服务名称")
    private String name;

    /**
     *
     * 用户提交的服务内容
     *
     */
    @ApiModelProperty(name = "content", value = "用户提交的服务内容",required = true)
    private String content;

    /**
     *
     * 用户提交的服务开始时间
     *
     */
    @ApiModelProperty(name = "startTime", value = "用户提交的服务开始时间")
    private Date startTime;


    /**
     *
     * 用户提交的服务结束时间
     *
     */
    @ApiModelProperty(name = "endTime", value = "用户提交的服务结束时间")
    private Date endTime;






}
