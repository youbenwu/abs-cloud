package com.outmao.ebs.hotel.dto;

import lombok.Data;


@Data
public class HotelRoomTypeDTO {

    /**
     *
     * 自动编号
     *
     */
    private Long id;


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



}
