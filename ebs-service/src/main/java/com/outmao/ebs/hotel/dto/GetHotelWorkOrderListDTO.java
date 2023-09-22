package com.outmao.ebs.hotel.dto;

import com.outmao.ebs.common.vo.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@ApiModel(value = "GetHotelWorkOrderListDTO", description = "获取酒店服务列表")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GetHotelWorkOrderListDTO extends BaseDTO {

    private Long hotelId;

    @ApiModelProperty(name = "status", value = "0-未处理 1-已处理")
    private Integer status;


    private String keyword;



}
