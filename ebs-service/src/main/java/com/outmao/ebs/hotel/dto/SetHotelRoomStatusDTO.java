package com.outmao.ebs.hotel.dto;

import com.outmao.ebs.common.vo.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@ApiModel(value = "SetHotelRoomStatusDTO", description = "设置房间状态")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SetHotelRoomStatusDTO extends BaseDTO {

    private Long id;

    @ApiModelProperty(name = "status", value = "房间状态 0-空闲 1-有客")
    private int status;

    private String statusRemark;

}
