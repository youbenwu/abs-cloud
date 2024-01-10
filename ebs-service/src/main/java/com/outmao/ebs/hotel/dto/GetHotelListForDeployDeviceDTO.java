package com.outmao.ebs.hotel.dto;

import com.outmao.ebs.common.vo.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

//设备托管获取酒店列表
@Data
public class GetHotelListForDeployDeviceDTO extends BaseDTO {

    private String keyword;

    @ApiModelProperty(name = "latitude", value = "查附近酒店--纬度")
    private Double latitude;// 纬度

    @ApiModelProperty(name = "longitude", value = "查附近酒店--经度")
    private Double longitude;

    private String city;

}
