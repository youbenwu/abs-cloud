package com.outmao.ebs.thirdpartys.rongcloud.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PushBodyDTO<T> {


    @ApiModelProperty(name = "type", value = "10--订单支付成功 11--订单状态变更消息 20--迁眼创建群消息 21--迁眼删除群消息 22--迁眼修改群信息消息 23--迁眼群公告消息")
    private int type;

    private String title;

    private String content;

    private T data;

}
