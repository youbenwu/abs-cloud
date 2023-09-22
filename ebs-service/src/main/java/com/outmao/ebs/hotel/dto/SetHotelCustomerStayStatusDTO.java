package com.outmao.ebs.hotel.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class SetHotelCustomerStayStatusDTO {


    private Long id;

    @ApiModelProperty(name = "status", value = "0-未入住 1-已入住  2-已退房")
    private int status;

    private String statusRemark;


}
