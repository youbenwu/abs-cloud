package com.outmao.ebs.hotel.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@ApiModel(value = "HotelServiceStaffVO", description = "酒店客服信息")
@Data
public class HotelServiceStaffVO {

    @ApiModelProperty(name = "hotelId", value = "酒店ID")
    private Long hotelId;

    @ApiModelProperty(name = "hotelName", value = "酒店名称")
    private String hotelName;

    @ApiModelProperty(name = "userId", value = "酒店客服用户ID")
    private Long userId;

    @ApiModelProperty(name = "phone", value = "酒店服务电话")
    private String phone;


}
