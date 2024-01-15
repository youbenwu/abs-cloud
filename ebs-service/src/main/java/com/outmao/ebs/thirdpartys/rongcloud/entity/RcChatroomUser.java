package com.outmao.ebs.thirdpartys.rongcloud.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@ApiModel(value = "RcChatroomUser", description = "聊天室用户")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@Data
@Entity
@Table(name = "RcChatroomUser", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "chatroomId", "userId" }) })
public class RcChatroomUser implements Serializable {

    /**
     *
     * 用户标识
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false,updatable = false)
    private Long chatroomId;

    @Column(nullable = false,updatable = false)
    private Long userId;


}
