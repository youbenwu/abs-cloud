package com.outmao.ebs.thirdpartys.rongcloud.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class RcChatroomDTO {


    @ApiModelProperty(name = "name", value = "聊天室名称")
    private String name;

    @ApiModelProperty(name = "type", value = "聊天室类型")
    private String type;

    @ApiModelProperty(name = "userId", value = "用户ID")
    private String userId;

    @ApiModelProperty(name = "chatroomId", value = "聊天室ID，不存自动生成")
    private String chatroomId;

    @ApiModelProperty(name = "rtcroomId", value = "绑定语聊室ID")
    private String rtcroomId;

    @ApiModelProperty(name = "groupId", value = "传酒店ID")
    private String groupId;



    //指定聊天室的销毁方式。0：默认值，表示不活跃时销毁。默认情况下，所有聊天室的自动销毁方式均为不活跃时销毁，一旦不活跃长达到 60 分钟即被销毁，可通过 destroyTime 延长该时间。1：固定时间销毁，设置为该类型后，聊天室默认在创建 60 分钟后自动销毁，可通过 destroyTime 设置更长的存活时间。
    private int destroyType;

    //设置聊天室销毁等待时间。在 destroyType=0 时，表示聊天室应在不活跃达到该时长时自动销毁。在 destroyType=1 时，表示聊天室应在创建以后存活时间达到该时长后自动销毁。单位为分钟，最小值 60 分钟，最大 10080 分钟（7 天）。
    private int destroyTime;

    //是否禁言聊天室全体成员，默认 false。您也可以在聊天室创建成功后再设置，详见设置聊天室全体禁言。
    private boolean ban;

}
