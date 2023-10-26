package com.outmao.ebs.hotel.vo;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@ApiModel(value = "StatsHotelDeviceProvinceVO", description = "设备按省统计数量和金额")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
public class StatsHotelDeviceProvinceVO {


    @ApiModelProperty(name = "province", value = "省份")
    private String province;

    @ApiModelProperty(name = "count", value = "设备数量")
    private long count;

    @ApiModelProperty(name = "amount", value = "设备金额")
    private double amount;

}
