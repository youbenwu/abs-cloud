package com.outmao.ebs.hotel.dto;

import com.outmao.ebs.common.vo.Address;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "PadActivateHotelDeviceDTO", description = "激活设备信息")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PadActivateHotelDeviceDTO {

    @ApiModelProperty(name = "userId", value = "操作激活用户ID")
    private Long userId;

    @ApiModelProperty(name = "hotelId", value = "酒店ID")
    private Long hotelId;

    @ApiModelProperty(name = "roomNo", value = "房间号")
    private String roomNo;

    @ApiModelProperty(name = "deviceNo", value = "设备号")
    private String deviceNo;


}
