package com.outmao.ebs.hotel.dto;



import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;

/**
 *
 * 客人登记入住
 *
 */
@Data
public class HotelCustomerStayDTO {


    @ApiModelProperty(name = "hotelId", value = "酒店ID" ,required = true)
    private Long hotelId;


    /**
     *
     * 客户ID
     *
     */
    @ApiModelProperty(name = "customerId", value = "客户ID，不传则用手机号查找，找不到则注册新客户" )
    private Long customerId;

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
    @ApiModelProperty(name = "phone", value = "phone",required = true)
    private String phone;


    /**
     *
     * 入住房间
     *
     */
    @ApiModelProperty(name = "roomNo", value = "入住房间号")
    private String roomNo;

    /**
     *
     * 入住时间
     *
     */
    @ApiModelProperty(name = "startTime", value = "入住时间")
    private Date startTime;

    /**
     *
     * 入住天数
     *
     */
    @ApiModelProperty(name = "stayDays", value = "入住天数")
    private int stayDays;



}
