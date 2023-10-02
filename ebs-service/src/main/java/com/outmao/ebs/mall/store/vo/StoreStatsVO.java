package com.outmao.ebs.mall.store.vo;

import com.outmao.ebs.bbs.entity.SubjectStats;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class StoreStatsVO extends SubjectStats {

    private Long storeId;

    @ApiModelProperty(name = "memberCount", value = "店员数量")
    private Long memberCount;

    @ApiModelProperty(name = "orderCount", value = "订单数量")
    private Long orderCount;

    @ApiModelProperty(name = "orderAmount", value = "订单金额")
    private Double orderAmount;

    @ApiModelProperty(name = "lookCount", value = "带看数量")
    private Long lookCount;

}
