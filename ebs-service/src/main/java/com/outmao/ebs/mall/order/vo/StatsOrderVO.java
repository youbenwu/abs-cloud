package com.outmao.ebs.mall.order.vo;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;


@ApiModel(value = "StatsOrderVO", description = "订单统计数量和金额")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
public class StatsOrderVO {

    @ApiModelProperty(name = "userId", value = "用户ID")
    private Long userId;

    @ApiModelProperty(name = "time", value = "时间")
    private Date time;

    @ApiModelProperty(name = "count", value = "订单数量")
    private Long count;

    @ApiModelProperty(name = "amount", value = "订单金额")
    private Double amount;

}
