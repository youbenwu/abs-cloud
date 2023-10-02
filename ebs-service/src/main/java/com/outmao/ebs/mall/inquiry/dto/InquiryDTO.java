package com.outmao.ebs.mall.inquiry.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * 用户询盘
 *
 */
@ApiModel(value = "InquiryDTO", description = "用户询盘参数")
@Data
public class InquiryDTO {


    @ApiModelProperty(name = "id", value = "ID")
    private Long id;

    /**
     *
     * 商家ID
     *
     */
//    @ApiModelProperty(name = "merchantId", value = "商家ID",required = true)
//    private Long merchantId;


    @ApiModelProperty(name = "userId", value = "User ID")
    private Long userId;

    /**
     *
     * 商品ID
     *
     */
    @ApiModelProperty(name = "productId", value = "商品ID")
    private Long productId;

    /**
     *
     * 商品名称
     *
     */
    @ApiModelProperty(name = "productTitle", value = "商品名称")
    private String productTitle;

    /**
     *
     * 商品图片
     *
     */
    @ApiModelProperty(name = "productImage", value = "商品图片")
    private String productImage;

    /**
     *
     * 姓名
     *
     */
    @ApiModelProperty(name = "name", value = "姓名")
    private String name;

    /**
     *
     * 手机号码
     *
     */
    @ApiModelProperty(name = "phone", value = "手机号码")
    private String phone;

    /**
     *
     * 意向购买数量
     *
     */
    @ApiModelProperty(name = "quantity", value = "意向购买数量")
    private Integer quantity;

    /**
     *
     * 用户备注
     *
     */
    @ApiModelProperty(name = "remark", value = "用户备注")
    private String remark;




}
