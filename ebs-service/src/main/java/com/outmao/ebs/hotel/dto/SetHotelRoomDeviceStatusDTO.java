package com.outmao.ebs.hotel.dto;

import com.outmao.ebs.common.vo.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@ApiModel(value = "SetHotelRoomDeviceStatusDTO", description = "设置房间设备投放状态")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SetHotelRoomDeviceStatusDTO extends BaseDTO {

    private Long hotelId;

    private String roomNo;

    @ApiModelProperty(name = "deviceStatus", value = "房间设备投放状态 0--无设备 1--设备投放中 2--设备已投放")
    private int deviceStatus;


}
