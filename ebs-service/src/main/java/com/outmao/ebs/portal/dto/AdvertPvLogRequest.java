package com.outmao.ebs.portal.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdvertPvLogRequest {

    @ApiModelProperty(name = "advertId", value = "广告ID")
    private Long advertId;

    @ApiModelProperty(name = "userId", value = "点击用户")
    private Long userId;

    @ApiModelProperty(name = "type", value = "类型 0--爆光 1--点击广告链接 2--点击广告视频 3--扫广告二维码")
    private int type;


}
