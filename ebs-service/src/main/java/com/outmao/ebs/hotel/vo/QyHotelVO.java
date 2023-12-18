package com.outmao.ebs.hotel.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@ApiModel(value = "QyHotelVO", description = "迁眼酒店信息")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class QyHotelVO extends HotelVO {


    @ApiModelProperty(name = "noDeviceRoomCount", value = "剩余房间数量")
    private int noDeviceRoomCount;

    @ApiModelProperty(name = "deviceRoomCount", value = "投放设备房间数量")
    private int deviceRoomCount;



}
