package com.outmao.ebs.hotel.vo;


import com.outmao.ebs.hotel.common.data.SimpleHotelSetter;
import com.outmao.ebs.hotel.entity.HotelDeviceBuy;
import com.outmao.ebs.hotel.entity.HotelDeviceLease;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;

/**
 *
 * 设备
 *
 */
@Data
public class HotelDeviceVO implements SimpleHotelSetter {


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
     *
     * 0--关机状态
     * 1--开机状态
     * 2--维修状态
     *
     */
    @ApiModelProperty(name = "activeStatus", value = "/**\n" +
            "     *\n" +
            "     * 0--关机状态\n" +
            "     * 1--开机状态\n" +
            "     * 2--维修状态\n" +
            "     * 3--预警状态\n" +
            "     *\n" +
            "     */")
    private int activeStatus;

    /**
     * 开机时长
     */
    @ApiModelProperty(name = "activeOnDuration", value = "开机时长")
    private int activeOnDuration;

    /**
     * 最新开机时间
     */
    @ApiModelProperty(name = "activeOnTime", value = "最新开机时间")
    private Date activeOnTime;

    /**
     * 最新关机时间
     */
    @ApiModelProperty(name = "activeOffTime", value = "最新关机时间")
    private Date activeOffTime;

    /**
     * 组织ID
     */
    private Long orgId;

    private Long hotelId;

    private SimpleHotelVO hotel;


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
     *
     * 操作激活用户的ID
     *
     */
    @ApiModelProperty(name = "actUserId", value = "操作激活用户的ID")
    private Long actUserId;

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
     * 设备购买信息
     *
     */
    private HotelDeviceBuy buy;

    /**
     *
     * 租赁信息
     *
     */
    private HotelDeviceLease lease;

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
     *
     * 激活时间
     *
     */
    private Date activeTime;


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


    private String token;


}
