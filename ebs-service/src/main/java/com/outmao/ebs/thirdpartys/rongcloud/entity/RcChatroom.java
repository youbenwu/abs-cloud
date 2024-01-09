package com.outmao.ebs.thirdpartys.rongcloud.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@ApiModel(value = "RcChatroom", description = "聊天室")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@Data
@Entity
@Table(name = "RcChatroom")
public class RcChatroom   implements Serializable {

    /**
     *
     * 用户标识
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userId;

    //聊天室ID
    @Column(unique = true)
    private String chatroomId;

    //绑定音视频房间
    @Column(unique = true)
    private String rtcroomId;

    private String groupId;

    private String name;

    private String type;

    //指定聊天室的销毁方式。0：默认值，表示不活跃时销毁。默认情况下，所有聊天室的自动销毁方式均为不活跃时销毁，一旦不活跃长达到 60 分钟即被销毁，可通过 destroyTime 延长该时间。1：固定时间销毁，设置为该类型后，聊天室默认在创建 60 分钟后自动销毁，可通过 destroyTime 设置更长的存活时间。
    private int destroyType;

    //设置聊天室销毁等待时间。在 destroyType=0 时，表示聊天室应在不活跃达到该时长时自动销毁。在 destroyType=1 时，表示聊天室应在创建以后存活时间达到该时长后自动销毁。单位为分钟，最小值 60 分钟，最大 10080 分钟（7 天）。
    private int destroyTime;

    //是否禁言聊天室全体成员，默认 false。您也可以在聊天室创建成功后再设置，详见设置聊天室全体禁言。
    private boolean ban;


    @ApiModelProperty(name = "createTime", value = "创建时间")
    private Date createTime;
    @ApiModelProperty(name = "updateTime", value = "更新时间")
    private Date updateTime;


    


}
