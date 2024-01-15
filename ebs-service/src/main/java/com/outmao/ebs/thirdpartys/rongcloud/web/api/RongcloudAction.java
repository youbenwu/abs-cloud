package com.outmao.ebs.thirdpartys.rongcloud.web.api;



import cn.jiguang.common.utils.StringUtils;
import com.outmao.ebs.common.configuration.sys.Config;
import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.common.vo.Result;
import com.outmao.ebs.hotel.entity.HotelDevice;
import com.outmao.ebs.hotel.service.HotelDeviceService;
import com.outmao.ebs.security.util.SecurityUtil;
import com.outmao.ebs.thirdpartys.rongcloud.dto.*;
import com.outmao.ebs.thirdpartys.rongcloud.entity.RcChatroom;
import com.outmao.ebs.thirdpartys.rongcloud.entity.RcChatroomUser;
import com.outmao.ebs.thirdpartys.rongcloud.service.RongcloudService;
import com.outmao.ebs.thirdpartys.rongcloud.vo.Token;
import com.outmao.ebs.user.common.constant.UserType;
import com.outmao.ebs.user.entity.User;
import com.outmao.ebs.user.service.UserService;
import com.outmao.ebs.user.vo.QyUserVO;
import com.outmao.ebs.user.vo.UserDetailsVO;
import io.rong.models.chatroom.ChatroomMember;
import io.rong.models.response.UserResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Slf4j
@Api(value = "rongcloud", tags = "融云")
@RestController
@RequestMapping("/api/rongcloud")
public class RongcloudAction {

	@Autowired
    private RongcloudService rongcloudService;

	@Autowired
	private UserService userService;

    @Autowired
	private HotelDeviceService hotelDeviceService;

	@Autowired
	protected Config config;

	@PreAuthorize("permitAll")
	@ApiOperation(value = "融云聊天室状态回调", notes = "融云聊天室状态回调")
	@PostMapping("/chatroom/notify")
	public void chatroom_notify(@RequestBody List<RcChatroomStatusDTO> data){
		log.info("/*  融云聊天室状态回调  */");
		rongcloudService.chatroomStatusNotify(data);
	}

	@PreAuthorize("permitAll")
	@ApiOperation(value = "融云语聊室状态回调", notes = "融云语聊室状态回调")
	@PostMapping("/rtcroom/notify")
	public void rtcroom_notify(@RequestBody List<RcChatroomStatusDTO> data){
		rongcloudService.chatroomStatusNotify(data);
	}


	@ApiOperation(value = "融云注册用户，获取APP登录TOKEN", notes = "融云注册用户，获取APP登录TOKEN")
	@PostMapping("/register")
	public Token register(Long hotelId) {
		if(!SecurityUtil.isAuthenticated()){
			throw new BusinessException("请先登录用户");
		}
		User user=userService.getUserById(SecurityUtil.currentUserId());

		RcRegisterUserDTO userModel=new RcRegisterUserDTO();
		userModel.setId(user.getId().toString());
		userModel.setName(StringUtils.isEmpty(user.getNickname())?user.getUsername():user.getNickname());

		String avatar=user.getAvatar();
		if(StringUtils.isEmpty(avatar)){
			if(user.getDetails().getSex()==1){
				avatar=config.getBaseUrl()+"/h_1.jpg";
			}else if(user.getDetails().getSex()==2){
				avatar=config.getBaseUrl()+"/h_2.jpg";
			}else{
				avatar=config.getBaseUrl()+"/user_head.jpg";
			}
		}
		userModel.setPortrait(avatar);


        if(user.getType()== UserType.QyHotelDevice.getType()){
            HotelDevice device=hotelDeviceService.getHotelDeviceByUserId(user.getId());
            if(device!=null){
                userModel.setGroupId(device.getHotelId().toString());
                userModel.setGroupName(device.getHotelId().toString());
            }
        }else{
        	if(hotelId!=null) {
				userModel.setGroupId(hotelId.toString());
				userModel.setGroupName(hotelId.toString());
			}
		}

		return rongcloudService.registerUser(userModel);

	}


	@ApiOperation(value = "融云创建聊天室", notes = "融云创建聊天室")
	@PostMapping("/chatroom/save")
	public RcChatroom saveChatroom(RcChatroomDTO request){
		return rongcloudService.saveChatroom(request);
	}

	@ApiOperation(value = "修改聊天室名称", notes = "修改聊天室名称")
	@PostMapping("/chatroom/update")
	public RcChatroom updateChatroom(RcChatroomUpdateDTO request){
		return rongcloudService.updateChatroom(request);
	}

	@ApiOperation(value = "删除聊天室", notes = "删除聊天室")
	@PostMapping("/chatroom/delete")
	public void deleteChatroomById(Long id){
		rongcloudService.deleteChatroomById(id);
	}



	@ApiOperation(value = "融云聊天室绑定语聊室", notes = "融云聊天室绑定语聊室")
	@PostMapping("/chatroom/bindRtcroom")
	public RcChatroom chatroomBindRtcroom(RcChatroomBindRtcroomDTO request){
		return rongcloudService.chatroomBindRtcroom(request);
	}

	@ApiOperation(value = "获取融云聊天室列表", notes = "获取融云聊天室列表")
	@PostMapping("/chatroom/list")
	public List<RcChatroom> getChatroomListByGroupId(String groupId){
		Long userId=null;
		if(SecurityUtil.isAuthenticated()){
			userId=SecurityUtil.currentUserId();
		}
		List<RcChatroom> list= rongcloudService.getChatroomListByGroupId(groupId);
		if(list.isEmpty())
			return list;
		Map<Long,RcChatroom> map=list.stream().collect(Collectors.toMap(t->t.getId(),t->t));
		List<RcChatroomUser> us=rongcloudService.getRcChatroomUserListByChatroomIdIn(map.keySet());

		if(us.isEmpty())
			return list;

		List<QyUserVO> users=userService.getQyUserVOListByIdIn(us.stream().map(t->t.getUserId()).collect(Collectors.toList()));

		Map<Long,QyUserVO> userMap=users.stream().collect(Collectors.toMap(t->t.getId(),t->t));

		for (RcChatroomUser u:us){
			RcChatroom room=map.get(u.getChatroomId());
			QyUserVO user=userMap.get(u.getUserId());
			if(room.getUsers()==null){
				room.setUsers(new ArrayList<>());
			}
			room.getUsers().add(user);
			if(u.getUserId().equals(userId)){
				room.setMember(true);
			}
		}

		return list;
	}


	@ApiOperation(value = "获取融云聊天室用户列表", notes = "获取融云聊天室用户列表")
	@PostMapping("/chatroom/query")
	public List<UserDetailsVO> rongCloudChatroomUserQuery(String chatroomId){
		List<ChatroomMember> list= rongcloudService.rongCloudChatroomUserQuery(chatroomId);
		try{
			List<Long> ids=list.stream().map(t->Long.parseLong(t.getId())).collect(Collectors.toList());
			List<UserDetailsVO> users=userService.getUserDetailsVOListByIdIn(ids);
			return users;
		}catch (Exception e){
			log.error("获取聊天室用户列表出错",e);
		}
		return new ArrayList<>();
	}

	@ApiOperation(value = "融云获取用户在线状态", notes = "融云获取用户在线状态")
	@PostMapping("/user/checkOnline")
	public Result rongCloudUserCheckOnline(String userId){
        String r=rongcloudService.rongCloudUserCheckOnline(userId);
		return Result.successResult(r);
	}


	@ApiOperation(value = "融云获取用户信息", notes = "融云获取用户信息")
	@PostMapping("/user/info")
	public UserResult rongCloudUserInfo(String userId){
		return rongcloudService.rongCloudUserInfo(userId);
	}





}
