package com.outmao.ebs.thirdpartys.rongcloud.service.impl;

import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.common.util.StringUtil;
import com.outmao.ebs.thirdpartys.rongcloud.config.RongcloudProperties;
import com.outmao.ebs.thirdpartys.rongcloud.dao.RcChatroomDao;
import com.outmao.ebs.thirdpartys.rongcloud.dao.RcGroupDao;
import com.outmao.ebs.thirdpartys.rongcloud.dto.*;
import com.outmao.ebs.thirdpartys.rongcloud.entity.RcChatroom;
import com.outmao.ebs.thirdpartys.rongcloud.entity.RcGroup;
import com.outmao.ebs.thirdpartys.rongcloud.service.RongcloudService;
import com.outmao.ebs.thirdpartys.rongcloud.vo.Token;
import io.rong.RongCloud;
import io.rong.methods.user.User;
import io.rong.models.Result;
import io.rong.models.chatroom.ChatroomDataModel;
import io.rong.models.chatroom.ChatroomMember;
import io.rong.models.chatroom.ChatroomModel;
import io.rong.models.group.GroupMember;
import io.rong.models.response.*;
import io.rong.models.user.UserModel;
import io.rong.util.CommonUtil;
import io.rong.util.GsonUtil;
import io.rong.util.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;


@Slf4j
@Service
public class RongcloudServiceImpl implements RongcloudService {

    @Autowired
    private RongcloudProperties properties;

    @Autowired
    private RongCloud rongCloud;

    @Autowired
    private RcGroupDao rcGroupDao;

    @Autowired
    private RcChatroomDao rcChatroomDao;

    @Transactional
    @Override
    public Token registerUser(RcRegisterUserDTO request) {
        User user = rongCloud.user;
        try {
            TokenResult result=user.register(request);
            if(result.code==200){
                Token token=new Token();
                BeanUtils.copyProperties(result,token);

                //加入群组
                RcGroupDTO groupDTO=new RcGroupDTO();
                groupDTO.setId(request.getGroupId());
                groupDTO.setName(request.getGroupName());
                GroupMember member=new GroupMember();
                member.setId(request.getId());
                groupDTO.setMembers(new GroupMember[]{member});
                saveGroup(groupDTO);


                return token;
            }else{
                log.error("注册融云用户出错",result.errorMessage);
                throw new BusinessException("注册融云用户出错");
            }
        }catch (Exception e){
            log.error("注册融云用户出错",e);
            throw new BusinessException("注册融云用户出错");
        }

    }


    @Transactional
    @Override
    public RcGroup saveGroup(RcGroupDTO request) {

        RcGroup group=rcGroupDao.findById(request.getId()).orElse(null);

        if(group==null){
            group=new RcGroup();
            group.setCreateTime(new Date());
            try{
                Result result= rongCloud.group.create(request);
                if(result.getCode()!=200){
                    log.error("融云创建群组出错:{}",result.getErrorMessage());
                    throw new BusinessException("融云创建群组出错");
                }
            }catch (Exception e){
                log.error("融云创建群组出错",e);
                throw new BusinessException("融云创建群组出错");
            }
        }else if(request.getMembers()!=null&&request.getMembers().length>0){
            try{
                Result result= rongCloud.group.join(request);
                if(result.getCode()!=200){
                    log.error("融云加入群组出错:{}",result.getErrorMessage());
                    throw new BusinessException("融云加入群组出错");
                }
            }catch (Exception e){
                log.error("融云加入群组出错",e);
                throw new BusinessException("融云加入群组出错");
            }
        }

        BeanUtils.copyProperties(request,group);

        group.setUpdateTime(new Date());

        rcGroupDao.save(group);

        return group;
    }


    @Transactional
    @Override
    public RcChatroom saveChatroom(RcChatroomDTO request) {

        request.setDestroyType(1);
        request.setDestroyTime(10080);

        if(StringUtil.isEmpty(request.getName())){
            throw new BusinessException("聊天室名称不能为空");
        }

        RcChatroom chatroom=StringUtil.isEmpty(request.getRtcroomId())?null:rcChatroomDao.findByChatroomId(request.getChatroomId());

        if(chatroom!=null){
            throw new BusinessException("聊天室ID已存在");
        }

        chatroom=new RcChatroom();
        chatroom.setCreateTime(new Date());
        chatroom.setUpdateTime(new Date());
        BeanUtils.copyProperties(request,chatroom);

        rcChatroomDao.save(chatroom);
        if(chatroom.getChatroomId()==null){
            chatroom.setChatroomId(chatroom.getId().toString());
            rcChatroomDao.save(chatroom);
        }

        rongCloudChatroomCreate(chatroom);

        if(StringUtil.isNotEmpty(chatroom.getRtcroomId())){
            rongCloudChatroomCorrelationRtc(chatroom);
        }

        return chatroom;

    }


    @Override
    public RcChatroom chatroomBindRtcroom(RcChatroomBindRtcroomDTO request) {
        RcChatroom chatroom=rcChatroomDao.findByChatroomId(request.getChatroomId());

        if(chatroom==null){
            throw new BusinessException("聊天室不存在");
        }

        chatroom.setRtcroomId(request.getRtcroomId());

        rongCloudChatroomCorrelationRtc(chatroom);

        rcChatroomDao.save(chatroom);

        return chatroom;

    }

    private void rongCloudChatroomCreate(RcChatroom chatroom){
       try{
           ChatroomDataModel model=new ChatroomDataModel();
           BeanUtils.copyProperties(chatroom,model,"id");
           model.setId(chatroom.getChatroomId());
           Result result= rongCloud.chatroom.createV2(model);
           if(result.getCode()!=200){
               log.error("融云创建聊天室出错:{}",result.getErrorMessage());
               throw new BusinessException("融云创建聊天室出错");
           }
       }catch (Exception e){
           log.error("融云创建聊天室出错",e);
           throw new BusinessException("融云创建聊天室出错");
       }
   }


    private void rongCloudChatroomCorrelationRtc(RcChatroom chatroom){
        try{
            ResponseResult result=chatroom_correlation_rtc(chatroom.getChatroomId(),chatroom.getRtcroomId());
            if(result.getCode()!=200){
                log.error("融云绑定语聊室出错:{}",result.getErrorMessage());
                throw new BusinessException("融云绑定语聊室出错");
            }
        }catch (Exception e){
            log.error("融云绑定语聊室出错",e);
            throw new BusinessException("融云绑定语聊室出错");
        }
    }

    private ResponseResult chatroom_correlation_rtc(String chatroomId,String rtcroomId) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("chatroomId=").append(URLEncoder.encode(chatroomId, "UTF-8"));
        sb.append("&rtcroomId=").append(URLEncoder.encode(rtcroomId, "UTF-8"));


        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(this.rongCloud.getConfig(), properties.getAppKey(), properties.getAppSecret(), "/chatroom/correlation/rtc.json", "application/x-www-form-urlencoded");
        HttpUtil.setBodyParameter(body, conn, this.rongCloud.getConfig());
        ResponseResult result = (ResponseResult)GsonUtil.fromJson(HttpUtil.returnResult(conn, this.rongCloud.getConfig()), ResponseResult.class);
        result.setReqBody(body);
        return result;
    }

    @Override
    public List<RcChatroom> getChatroomListByGroupId(String groupId) {
        List<RcChatroom> list=rcChatroomDao.findAllByGroupIdOrderByUpdateTimeDesc(groupId);
        return list;
    }

    @Transactional
    @Override
    public void chatroomStatusNotify(List<RcChatroomStatusDTO> request) {
        if(request==null||request.isEmpty())
            return;
        request.forEach(t->{
            if(t.getType()==3){
                //销毁聊天室
                RcChatroom chatroom=rcChatroomDao.findByChatroomId(t.getChatRoomId());
                if(chatroom!=null){
                    rcChatroomDao.delete(chatroom);
                }
            }
        });
    }


    @Transactional
    @Override
    public void rtcroomNotify(RcRtcroomNotifyDTO request) {
        if(request.getEvent()==3){
            //销毁语聊室
            RcChatroom chatroom=rcChatroomDao.findByRtcroomId(request.getRoomId());
            if(chatroom!=null){
                rcChatroomDao.delete(chatroom);
            }
        }
    }


    @Override
    public List<ChatroomMember> rongCloudChatroomUserQuery(String chatroomId){

        try{

            ChatroomModel model=new ChatroomModel();
            model.setId(chatroomId);
            //要获取的聊天室成员信息数，最多返回 500 个成员信息
            model.setCount(500);
            //加入聊天室的先后顺序， 1 为加入时间正序， 2 为加入时间倒序
            model.setOrder(2);
            ChatroomUserQueryResult result= rongCloud.chatroom.get(model);

            if(result.getCode()!=200){
                log.error("融云获取聊天室成员出错:{}",result.getErrorMessage());
                throw new BusinessException("融云获取聊天室成员出错");
            }

            return result.getMembers();

        }catch (Exception e){
            log.error("融云获取聊天室成员出错",e);
            throw new BusinessException("融云获取聊天室成员出错");
        }




    }


    @Override
    public String rongCloudUserCheckOnline(String userId){
        try{
            UserModel model=new UserModel();
            model.setId(userId);
            CheckOnlineResult result= rongCloud.user.onlineStatus.check(model);
            if(result.getCode()!=200){
                log.error("融云获取用户在线状态出错:{}",result.getErrorMessage());
                throw new BusinessException("融云获取用户在线状态出错");
            }
            return result.getStatus();
        }catch (Exception e){
            log.error("融云获取用户在线状态出错",e);
            throw new BusinessException("融云获取用户在线状态出错");
        }
    }


    @Override
    public UserResult rongCloudUserInfo(String userId) {
        try{
            UserModel model=new UserModel();
            model.setId(userId);
            UserResult result=rongCloud.user.get(model);
            return result;
        }catch (Exception e){
            log.error("融云获取用户信息出错",e);
            throw new BusinessException("融云获取用户信息出错");
        }
    }


}
