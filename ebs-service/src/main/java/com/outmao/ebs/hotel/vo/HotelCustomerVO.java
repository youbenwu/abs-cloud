package com.outmao.ebs.hotel.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;

/**
 *
 * 客人
 *
 */
@Data
public class HotelCustomerVO {


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
     * 客户状态
     * 0-未入住 1-已入住
     *
     */
    @ApiModelProperty(name = "stayStatus", value = "0-未入住 1-已入住")
    private int stayStatus;


    /**
     *
     * 用户ID
     *
     */
    private Long userId;


    /**
     *
     * 入住次数
     *
     */
    @ApiModelProperty(name = "stays", value = "入住次数")
    private int stays;


    /**
     *
     * 入住天数
     *
     */
    @ApiModelProperty(name = "stayDays", value = "入住天数")
    private int stayDays;


    /**
     *
     * 姓名
     *
     */
    @ApiModelProperty(name = "name", value = "姓名")
    private String name;

    /**
     *
     * 手机号
     *
     */
    @ApiModelProperty(name = "phone", value = "手机号")
    private String phone;


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
