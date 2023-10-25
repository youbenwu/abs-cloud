package com.outmao.ebs.user.vo;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;


@ApiModel(value = "StatsUserActiveCountVO", description = "用户活跃数量统计")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
public class StatsUserActiveCountVO {


    @ApiModelProperty(name = "time", value = "时间")
    private Date time;

    @ApiModelProperty(name = "index", value = "下标")
    private String index;

    @ApiModelProperty(name = "count", value = "活跃用户数量")
    private long count;


}
