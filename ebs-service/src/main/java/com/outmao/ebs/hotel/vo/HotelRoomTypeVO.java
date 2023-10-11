package com.outmao.ebs.hotel.vo;

import lombok.Data;

import java.util.Date;

@Data
public class HotelRoomTypeVO {

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
     * 房型日租
     *
     */
    private double price;


    /**
     *
     * 可住人数
     *
     */
    private int num;

    /**
     *
     * 房型名称
     *
     */
    private String name;

    /**
     *
     * 房型简介
     *
     */
    private String intro;

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
