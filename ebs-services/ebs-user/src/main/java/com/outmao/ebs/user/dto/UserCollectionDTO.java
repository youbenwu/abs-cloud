package com.outmao.ebs.user.dto;

import lombok.Data;

@Data
public class UserCollectionDTO {

    private Long id;


    private Long userId;


    private Long toId;

    //0--取消收藏 1--收藏
    private int status;

    //标记
    private String mark;

    //备注
    private String remark;

}
