package com.outmao.ebs.thirdpartys.rongcloud.dto;

import io.rong.models.chatroom.ChatroomDataModel;
import lombok.Data;


@Data
public class RcChatroomDTO extends ChatroomDataModel {

    private String name;

    private String userId;

    private String rtcroomId;

    private String groupId;


}
