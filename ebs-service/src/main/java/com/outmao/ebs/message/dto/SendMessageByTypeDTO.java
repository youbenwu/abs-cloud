package com.outmao.ebs.message.dto;


import com.outmao.ebs.common.vo.BindingItem;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class SendMessageByTypeDTO {

    @ApiModelProperty(name="type",value="消息类别",required = true)
    private String type;
    @ApiModelProperty(name="fromId",value="发送用户ID",required = true)
    private Long fromId;
    @ApiModelProperty(name="tos",value="接收用户ID列表")
    private List<Long> tos;
    @ApiModelProperty(name="item",value="绑定业务对像")
    private BindingItem item;
    @ApiModelProperty(name="data",value="消息参数数据")
    private Object data;


}
