package com.outmao.ebs.hotel.dto;

import com.outmao.ebs.common.vo.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@ApiModel(value = "SetHotelWorkOrderStatusDTO", description = "酒店服务状态")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SetHotelWorkOrderStatusDTO extends BaseDTO {

    private Long id;

    @ApiModelProperty(name = "status", value = "0-未处理 1-已处理")
    private int status;

    private String statusRemark;

}
