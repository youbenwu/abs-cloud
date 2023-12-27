package com.outmao.ebs.thirdpartys.rongcloud.dto;

import lombok.Data;

import java.util.List;

@Data
public class RcRtcroomNotifyDTO {

    //"appKey":"appKey",
    //  "sessionId":"sessionId",
    //  "roomId":"roomId",
    //  "userId":"123",
    //  "event":1,
    //  "exitType":2,
    //  "timestamp":1586244141831,\\
    private String roomId;
    private String userId;
    private int event;
    private int exitType;

}
