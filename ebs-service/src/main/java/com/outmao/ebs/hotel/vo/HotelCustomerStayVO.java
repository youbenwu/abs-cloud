package com.outmao.ebs.hotel.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;

/**
 *
 * 客人入住记录
 *
 */
@Data
public class HotelCustomerStayVO {

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
     * 用户ID
     *
     */
    private Long userId;

    /**
     *
     * 客户ID
     *
     */
    private Long customerId;



    /**
     *
     * 状态
     * 0-未入住 1-已入住  2-已退房
     *
     */
    @ApiModelProperty(name = "status", value = "0-未入住 1-已入住  2-已退房")
    private int status;

    /**
     *
     * 状态
     *
     *
     */
    private String statusRemark;

    /**
     *
     * 入住房间
     *
     */
    private String roomNo;

    /**
     *
     * 房间价格
     *
     */
    private double price;

    /**
     *
     * 入住天数
     *
     */
    @ApiModelProperty(name = "stayDays", value = "入住天数")
    private int stayDays;

    /**
     *
     * 押金
     *
     */
    @ApiModelProperty(name = "rents", value = "押金")
    private double rents;

    /**
     *
     * 消费金额
     *
     */
    @ApiModelProperty(name = "amount", value = "消费金额")
    private double amount;

    /**
     *
     * 开始时间
     *
     */
    @ApiModelProperty(name = "startTime", value = "开始时间")
    private Date startTime;

    /**
     *
     * 结束时间
     *
     */
    @ApiModelProperty(name = "endTime", value = "结束时间")
    private Date endTime;


    /**
     *
     * 姓名
     *
     */
    private String name;

    /**
     *
     * 手机号
     *
     */
    private String phone;

    /**
     *
     * 身份证号码
     *
     */
    private String idNo;


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
