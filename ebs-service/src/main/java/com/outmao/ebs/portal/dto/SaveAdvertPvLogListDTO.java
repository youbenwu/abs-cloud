package com.outmao.ebs.portal.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.List;

/**
 *
 * 保存广告PV记录
 *
 */

@Data
public class SaveAdvertPvLogListDTO  {

    @ApiModelProperty(name = "adverts", value = "广告ID列表")
    private List<Long> adverts;

    @ApiModelProperty(name = "userId", value = "点击用户")
    private Long userId;

    @ApiModelProperty(name = "spaceId", value = "场所ID")
    private Long spaceId;


}
