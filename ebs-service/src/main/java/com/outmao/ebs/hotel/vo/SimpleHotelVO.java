package com.outmao.ebs.hotel.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@ApiModel(value = "SimpleHotelVO", description = "酒店信息")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SimpleHotelVO {

    @ApiModelProperty(name = "id", value = "酒店ID")
    private Long id;


    /**
     * 店铺ID
     */
    @ApiModelProperty(name = "shopId", value = "店铺ID")
    private Long shopId;


    /**
     *
     * 酒店名称
     *
     */
    @ApiModelProperty(name = "name", value = "酒店名称")
    private String name;



    /**
     *
     * 酒店LOGO
     *
     */
    @ApiModelProperty(name = "logo", value = "酒店LOGO")
    private String logo;

    /**
     *
     * 酒店图片
     *
     */
    @ApiModelProperty(name = "image", value = "酒店图片")
    private String image;


    /**
     *
     * 服务电话
     *
     */
    @ApiModelProperty(name = "servicePhone", value = "服务电话")
    private String servicePhone;



}
