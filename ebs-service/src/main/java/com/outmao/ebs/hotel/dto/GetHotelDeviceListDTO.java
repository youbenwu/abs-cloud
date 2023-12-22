package com.outmao.ebs.hotel.dto;

import com.outmao.ebs.common.vo.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GetHotelDeviceListDTO extends BaseDTO {


    @ApiModelProperty(name = "status", value = "0-未激活 1-已激活")
    private Integer status;

    @ApiModelProperty(name = "activeStatus", value = "/**\n" +
            "     *\n" +
            "     * 0--关机状态\n" +
            "     * 1--开机状态\n" +
            "     * 2--维修状态\n" +
            "     * 3--预警状态\n" +
            "     *\n" +
            "     */")
    private Integer activeStatus;

    @ApiModelProperty(name = "renterId", value = "租赁用户ID")
    private Long renterId;

    @ApiModelProperty(name = "ownerId", value = "机主用户ID")
    private Long ownerId;

    private Long hotelId;

    private String keyword;


}
