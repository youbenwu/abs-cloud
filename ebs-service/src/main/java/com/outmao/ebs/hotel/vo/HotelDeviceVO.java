package com.outmao.ebs.hotel.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;

/**
 *
 * 设备
 *
 */
@Data
public class HotelDeviceVO {


    /**
     *
     * 自动编号
     *
     */
    private Long id;

    /**
     * 0--未激活
     * 1--已激活
     */
    private int status;

    /**
     * 组织ID
     */
    private Long orgId;



    private Long hotelId;


    /**
     * 房间号
     */
    @ApiModelProperty(name = "roomNo", value = "房间号")
    private String roomNo;




    /**
     * 迁眼PAD应用--会同时创建一个虚拟的用户，代表房客用户
     */
    @ApiModelProperty(name = "userId", value = "迁眼PAD应用--会同时创建一个虚拟的用户，代表房客用户")
    private Long userId;


    /**
     * 设备号
     */
    @ApiModelProperty(name = "deviceNo", value = "设备号")
    private String deviceNo;

    /**
     * 设备投放省
     */
    private String province;

    /**
     * 设备投放城市
     */
    private String city;

    /**
     *
     * 设备所有者用户ID
     *
     */
    private Long ownerId;

    /**
     *
     * 购买设备的金额
     *
     */
    private Double amount;

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


    /**
     * 应用类型
     */
    @ApiModelProperty(name = "appType", value = "应用类型")
    private String appType;


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
