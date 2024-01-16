package com.outmao.ebs.thirdpartys.rongcloud.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PushBodyDTO<T> {


    @ApiModelProperty(name = "type", value = "消息类型 chatroom-create--创建群聊 chatroom-update--修改群聊 chatroom-delete--删除群聊")
    private String type;

    private String title;

    private String content;

    private T data;

}
