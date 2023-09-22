package com.outmao.ebs.hotel.dto;

import com.outmao.ebs.common.vo.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@ApiModel(value = "SetHotelStatusDTO", description = "设置酒店状态")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SetHotelStatusDTO extends BaseDTO {

    private Long id;

    @ApiModelProperty(name = "status", value = "酒店状态 0--正常 1--禁用 2--未审核 5--审核失败 7--欠费")
    private int status;

    private String statusRemark;

}
