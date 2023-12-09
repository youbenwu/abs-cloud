package com.outmao.ebs.hotel.dto;

import com.outmao.ebs.common.vo.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GetHotelListDTO extends BaseDTO {

    private String keyword;

    private Integer status;

    @ApiModelProperty(name = "latitude", value = "查附近酒店--纬度")
    private Double latitude;// 纬度

    @ApiModelProperty(name = "longitude", value = "查附近酒店--经度")
    private Double longitude;

}
