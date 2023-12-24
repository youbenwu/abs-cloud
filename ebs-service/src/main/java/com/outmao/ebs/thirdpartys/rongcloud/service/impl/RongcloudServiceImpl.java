package com.outmao.ebs.thirdpartys.rongcloud.service.impl;

import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.common.util.StringUtil;
import com.outmao.ebs.thirdpartys.rongcloud.dao.RcChatroomDao;
import com.outmao.ebs.thirdpartys.rongcloud.dao.RcGroupDao;
import com.outmao.ebs.thirdpartys.rongcloud.dto.RcChatroomDTO;
import com.outmao.ebs.thirdpartys.rongcloud.dto.RcGroupDTO;
import com.outmao.ebs.thirdpartys.rongcloud.dto.RcRegisterUserDTO;
import com.outmao.ebs.thirdpartys.rongcloud.entity.RcChatroom;
import com.outmao.ebs.thirdpartys.rongcloud.entity.RcGroup;
import com.outmao.ebs.thirdpartys.rongcloud.service.RongcloudService;
import com.outmao.ebs.thirdpartys.rongcloud.vo.Token;
import io.rong.RongCloud;
import io.rong.methods.user.User;
import io.rong.models.Result;
import io.rong.models.chatroom.ChatroomDataModel;
import io.rong.models.group.GroupMember;
import io.rong.models.response.ResponseResult;
import io.rong.models.response.TokenResult;
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


@Slf4j
@Service
public class RongcloudServiceImpl implements RongcloudService {

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
    public RcChatroom saveRcChatroom(RcChatroomDTO request) {


        if(StringUtil.isEmpty(request.getId())){
            throw new BusinessException("聊天室ID不能为空");
        }

        if(StringUtil.isEmpty(request.getName())){
            throw new BusinessException("聊天室名称不能为空");
        }

        RcChatroom chatroom=rcChatroomDao.findById(request.getId()).orElse(null);

        if(chatroom!=null){
            throw new BusinessException("聊天室已存在");
        }

        try{
            request.setDestroyType(1);
            request.setDestroyTime(10080);
            Result result= rongCloud.chatroom.createV2(request);
            if(result.getCode()!=200){
                log.error("融云创建聊天室出错:{}",result.getErrorMessage());
                throw new BusinessException("融云创建聊天室出错");
            }
        }catch (Exception e){
            log.error("融云创建聊天室出错",e);
            throw new BusinessException("融云创建聊天室出错");
        }

//        if(StringUtil.isNotEmpty(request.getRtcroomId())){
//            try{
//
//                rongCloud.chatroom.
//                Result result= rongCloud.chatroom.createV2(request);
//                if(result.getCode()!=200){
//                    log.error("融云创建聊天室出错:{}",result.getErrorMessage());
//                    throw new BusinessException("融云创建聊天室出错");
//                }
//            }catch (Exception e){
//                log.error("融云创建聊天室出错",e);
//                throw new BusinessException("融云创建聊天室出错");
//            }
//        }

        chatroom=new RcChatroom();
        chatroom.setCreateTime(new Date());
        chatroom.setUpdateTime(new Date());
        BeanUtils.copyProperties(request,chatroom);

        rcChatroomDao.save(chatroom);


        return chatroom;
    }


//    public ResponseResult bind(RcChatroomDTO request) throws Exception {
//        StringBuilder sb = new StringBuilder();
//        sb.append("chatroomId=").append(URLEncoder.encode(request.getId(), "UTF-8"));
//        sb.append("&rtcroomId=").append(URLEncoder.encode(request.getRtcroomId(), "UTF-8"));
//
//
//        String body = sb.toString();
//        if (body.indexOf("&") == 0) {
//            body = body.substring(1, body.length());
//        }
//
//        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(this.rongCloud.getConfig(), rongCloud.chatroom.appKey, this.appSecret, "/chatroom/create_new.json", "application/x-www-form-urlencoded");
//        HttpUtil.setBodyParameter(body, conn, this.rongCloud.getConfig());
//        ResponseResult result = (ResponseResult)GsonUtil.fromJson(CommonUtil.getResponseByCode("chatroom", "createv2", HttpUtil.returnResult(conn, this.rongCloud.getConfig())), ResponseResult.class);
//        result.setReqBody(body);
//        return result;
//    }


}
