package com.outmao.ebs.hotel.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;

@Data
public class HotelRoomVO {

    /**
     *
     * 自动编号
     *
     */
    private Long id;

    /**
     *
     * 房间状态
     * 0-空闲 1-有客
     *
     */
    @ApiModelProperty(name = "status", value = "0-空闲 1-有客")
    private int status;

    /**
     *
     * 房间状态备注
     *
     */
    @ApiModelProperty(name = "statusRemark", value = "房间状态备注")
    private String statusRemark;


    @ApiModelProperty(name = "hotelId", value = "酒店ID")
    private Long hotelId;

    /**
     *
     * 房型ID
     *
     */
    @ApiModelProperty(name = "typeId", value = "房型ID")
    private Long typeId;
    private HotelRoomTypeVO type;


    /**
     *
     * 房间号
     *
     */
    @ApiModelProperty(name = "roomNo", value = "房间号")
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
