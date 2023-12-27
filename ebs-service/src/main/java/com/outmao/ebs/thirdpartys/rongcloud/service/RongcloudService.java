package com.outmao.ebs.thirdpartys.rongcloud.service;

import com.outmao.ebs.thirdpartys.rongcloud.dto.*;
import com.outmao.ebs.thirdpartys.rongcloud.entity.RcChatroom;
import com.outmao.ebs.thirdpartys.rongcloud.entity.RcGroup;
import com.outmao.ebs.thirdpartys.rongcloud.vo.Token;
import io.rong.models.chatroom.ChatroomMember;

import java.util.List;

public interface RongcloudService {

    public Token registerUser(RcRegisterUserDTO request);


    public RcGroup saveGroup(RcGroupDTO request);


    public RcChatroom saveChatroom(RcChatroomDTO request);


    public RcChatroom chatroomBindRtcroom(RcChatroomBindRtcroomDTO request);


    public List<RcChatroom> getChatroomListByGroupId(String groupId);

    public void chatroomStatusNotify(List<RcChatroomStatusDTO> request);


    public void rtcroomNotify(RcRtcroomNotifyDTO request);


    public List<ChatroomMember> rongCloudChatroomUserQuery(String chatroomId);


}
