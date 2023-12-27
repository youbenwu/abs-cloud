package com.outmao.ebs.thirdpartys.rongcloud.dto;

import lombok.Data;

import java.util.List;

@Data
public class RcChatroomStatusDTO {

    //"chatRoomId":"destory_11",
    //    "userIds":["gggg"],
    //    "status":0,
    //    "type":1,
    //    "time":1574476797772
    private String chatRoomId;
    private List<String> userIds;
    private int status;
    //聊天室事件类型取值如下：0 为创建聊天室、1 加入聊天室、2 退出聊天室、3 销毁聊天室。
    private int type;
    private long time;

}
