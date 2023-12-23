package com.outmao.ebs.thirdpartys.rongcloud.web.api;



import cn.jiguang.common.utils.StringUtils;
import com.outmao.ebs.common.configuration.sys.Config;
import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.security.util.SecurityUtil;
import com.outmao.ebs.thirdpartys.rongcloud.dto.RcRegisterUserDTO;
import com.outmao.ebs.thirdpartys.rongcloud.service.RongcloudService;
import com.outmao.ebs.thirdpartys.rongcloud.vo.Token;
import com.outmao.ebs.user.entity.User;
import com.outmao.ebs.user.service.UserService;
import io.rong.models.user.UserModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(value = "rongcloud", tags = "融云")
@RestController
@RequestMapping("/api/rongcloud")
public class RongcloudAction {

	@Autowired
    private RongcloudService rongcloudService;

	@Autowired
	private UserService userService;

	@Autowired
	protected Config config;



	@ApiOperation(value = "融云注册用户，获取APP登录TOKEN", notes = "融云注册用户，获取APP登录TOKEN")
	@PostMapping("/register")
	public Token register() {
		if(!SecurityUtil.isAuthenticated()){
			throw new BusinessException("请先登录用户");
		}
		User user=userService.getUserById(SecurityUtil.currentUserId());
		RcRegisterUserDTO userModel=new RcRegisterUserDTO();
		userModel.setId(user.getId().toString());
		userModel.setName(StringUtils.isEmpty(user.getNickname())?user.getUsername():user.getNickname());
		userModel.setPortrait(StringUtils.isEmpty(user.getAvatar())?(config.getBaseUrl()+"/user_head.jpg"):user.getNickname());

		return rongcloudService.registerUser(userModel);

	}





}
