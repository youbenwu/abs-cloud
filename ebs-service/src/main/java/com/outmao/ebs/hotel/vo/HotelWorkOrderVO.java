package com.outmao.ebs.hotel.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 *
 * 客服服务
 *
 */
@Data
public class HotelWorkOrderVO {


    /**
     *
     * 自动编号
     *
     */
    private Long id;

    /**
     * 组织ID
     */
    private Long orgId;


    private Long hotelId;

    /**
     *
     * 状态
     * 0-未处理 1-已处理
     *
     */
    @ApiModelProperty(name = "status", value = "0-未处理 1-已处理")
    private int status;

    /**
     *
     * 状态备注
     *
     */
    private String statusRemark;

    /**
     *
     * 房间号
     *
     */
    @ApiModelProperty(name = "roomNo", value = "房间号")
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
    @ApiModelProperty(name = "content", value = "用户提交的服务内容")
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


    /**
     *
     * 创建时间
     *
     */
    private Date createTime;

    /**
     *
     * 更新时间
     *
     */
    private Date updateTime;



}
