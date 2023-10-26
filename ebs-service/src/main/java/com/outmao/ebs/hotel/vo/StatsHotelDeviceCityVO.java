package com.outmao.ebs.hotel.vo;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;



@ApiModel(value = "StatsHotelDeviceCityVO", description = "设备按城市统计数量和金额")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
public class StatsHotelDeviceCityVO {


    @ApiModelProperty(name = "city", value = "城市")
    private String city;

    @ApiModelProperty(name = "count", value = "设备数量")
    private long count;

    @ApiModelProperty(name = "amount", value = "设备金额")
    private double amount;

}
