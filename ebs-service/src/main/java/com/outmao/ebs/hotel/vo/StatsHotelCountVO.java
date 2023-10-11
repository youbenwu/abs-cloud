package com.outmao.ebs.hotel.vo;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;


@ApiModel(value = "StatsHotelCountVO", description = "酒店数量统计")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
public class StatsHotelCountVO {


    @ApiModelProperty(name = "time", value = "时间")
    private Date time;

    @ApiModelProperty(name = "index", value = "下标")
    private String index;

    @ApiModelProperty(name = "count", value = "酒店数量")
    private long count;


}
