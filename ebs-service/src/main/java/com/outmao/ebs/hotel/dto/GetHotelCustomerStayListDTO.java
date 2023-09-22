package com.outmao.ebs.hotel.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *
 * 客人
 *
 */
@Data
public class GetHotelCustomerStayListDTO {



    @ApiModelProperty(name = "hotelId", value = "酒店ID" )
    private Long hotelId;

    private String keyword;

    @ApiModelProperty(name = "status", value = "0-未入住 1-已入住  2-已退房")
    private Integer status;


}
