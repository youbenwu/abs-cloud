package com.outmao.ebs.mall.inquiry.vo;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;


@ApiModel(value = "StatsInquiryVO", description = "询盘按日统计数量")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
public class StatsInquiryVO {

    @ApiModelProperty(name = "userId", value = "用户ID")
    private Long userId;

    @ApiModelProperty(name = "time", value = "日期")
    private Date time;

    @ApiModelProperty(name = "count", value = "数量")
    private Long count;

}
