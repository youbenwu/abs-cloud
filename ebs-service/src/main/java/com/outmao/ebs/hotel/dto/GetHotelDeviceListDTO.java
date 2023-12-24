package com.outmao.ebs.hotel.dto;

import com.outmao.ebs.common.vo.BaseDTO;
import com.outmao.ebs.common.vo.PageDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class GetHotelDeviceListDTO extends PageDTO {


    @ApiModelProperty(name = "status", value = "/**\n" +
            "     * 0--未激活\n" +
            "     * 1--已激活\n" +
            "     * 2--待托管\n" +
            "     * 3--已托管\n" +
            "     *\n" +
            "     */")
    private List<Integer> status;

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
