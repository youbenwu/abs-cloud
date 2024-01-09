package com.outmao.ebs.thirdpartys.rongcloud.service;

import com.outmao.ebs.thirdpartys.rongcloud.dto.*;
import com.outmao.ebs.thirdpartys.rongcloud.entity.RcChatroom;
import com.outmao.ebs.thirdpartys.rongcloud.entity.RcGroup;
import com.outmao.ebs.thirdpartys.rongcloud.vo.Token;
import io.rong.models.chatroom.ChatroomMember;
import io.rong.models.response.UserResult;

import java.util.List;

public interface RongcloudService {

    public Token registerUser(RcRegisterUserDTO request);


    public RcGroup saveGroup(RcGroupDTO request);


    public RcChatroom saveChatroom(RcChatroomDTO request);

    public RcChatroom updateChatroom(RcChatroomUpdateDTO request);


    public void deleteChatroomById(Long id);


    public RcChatroom chatroomBindRtcroom(RcChatroomBindRtcroomDTO request);


    public List<RcChatroom> getChatroomListByGroupId(String groupId);

    public void chatroomStatusNotify(List<RcChatroomStatusDTO> request);


    public void rtcroomNotify(RcRtcroomNotifyDTO request);


    public List<ChatroomMember> rongCloudChatroomUserQuery(String chatroomId);

    public String rongCloudUserCheckOnline(String userId);

    public UserResult rongCloudUserInfo(String userId);


    public void push(PushDTO<PushBodyDTO> request);


}
