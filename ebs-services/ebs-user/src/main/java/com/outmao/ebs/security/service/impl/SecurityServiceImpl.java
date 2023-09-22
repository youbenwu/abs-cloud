package com.outmao.ebs.security.service.impl;



import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.common.services.wxmp.WXMP;
import com.outmao.ebs.common.services.wxmp.WXMPSessionResult;
import com.outmao.ebs.common.util.RequestUtil;
import com.outmao.ebs.org.service.AdminService;
import com.outmao.ebs.org.service.MemberService;
import com.outmao.ebs.org.service.OrgService;
import com.outmao.ebs.org.vo.OrgVO;
import com.outmao.ebs.security.service.SecurityService;
import com.outmao.ebs.security.util.SecurityUtil;
import com.outmao.ebs.security.validate.ValidateCode;
import com.outmao.ebs.security.validate.ValidateCodeUtil;
import com.outmao.ebs.security.validate.sms.SmsCodeGenerator;
import com.outmao.ebs.security.validate.sms.SmsCodeSender;
import com.outmao.ebs.security.vo.SecurityUser;
import com.outmao.ebs.user.common.constant.ClientType;
import com.outmao.ebs.user.common.constant.Oauth;
import com.outmao.ebs.user.dto.RegisterDTO;
import com.outmao.ebs.user.dto.SetAuthenticatedDTO;
import com.outmao.ebs.user.entity.UserOauth;
import com.outmao.ebs.user.entity.UserOauthSession;
import com.outmao.ebs.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

@Service
public class SecurityServiceImpl implements SecurityService {


    @Autowired
    private UserService userService;

    @Autowired
    private OrgService orgService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private AdminService adminService;


    @Autowired
    private WXMP wxmp;

    @Autowired
    private SmsCodeSender smsCodeSender;

    @Autowired
    private SmsCodeGenerator smsCodeGenerator;


    @Override
    public boolean hasPermission(Long orgId, String url, String permission) {
        if(orgId==null){
            orgId=orgService.getOrg().getId();
        }
        if(SecurityUtil.hasPermission(orgId,url,permission)){
            return true;
        }
        OrgVO org=orgService.getOrgVOById(orgId);
        while (org.getParentId()!=null){
            if(SecurityUtil.hasPermission(org.getParentId(),url,permission)){
                return true;
            }
            org=orgService.getOrgVOById(org.getParentId());
        }
        return false;
    }


    @Override
    public SecurityUser setAuthenticated(SecurityUser securityUser, boolean authenticated) {
        if(authenticated){
            String clientType= RequestUtil.getHeader("clientType");
            String imei=RequestUtil.getHeader("imei");
            SetAuthenticatedDTO dto=new SetAuthenticatedDTO();
            dto.setClientType(clientType);
            dto.setUserId(securityUser.getId());
            dto.setOauthId(securityUser.getSession().getOauthId());
            dto.setImei(imei);
            dto.setSessionKey(securityUser.getSession().getSessionKey());
            dto.setExpireTime(securityUser.getSession().getExpireTime());
            UserOauthSession session=userService.setAuthenticated(dto);
            return getUser(securityUser,session);
        }else{
            if(securityUser.getSession().getSessionId()!=null) {
                userService.setAuthenticatedNot(securityUser.getSession().getSessionId());
            }
            return securityUser;
        }
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserOauth oauth=userService.getUserAuthByPrincipal(username);
        if(oauth==null){
            throw new UsernameNotFoundException("帐号不存在");
        }
        SecurityUser securityUser = getUser(oauth);
        return securityUser;
    }

    @Override
    public SecurityUser loadUserByToken(String token) {
        UserOauthSession session=userService.getUserOauthSessionByToken(token);
        if(session!=null) {
            SecurityUser securityUser = getUser(null,session);
            return securityUser;
        }
        return null;
    }

    @Override
    public SecurityUser loadUserOrRegisterByWeChatCode(String code) {
        try {
            WXMPSessionResult r= wxmp.getSession(code);
            if(r.getErrcode()!=0){
                throw new AuthenticationServiceException(r.getErrmsg());
            }
            String unionid=r.getUnionid();
            String openid=r.getOpenid();
            String sessionKey=r.getSession_key();
            SecurityUser securityUser=(SecurityUser)loadUserByUsername(openid);
            if(securityUser==null){
                securityUser=registerUser(Oauth.WECHAT.getName(),openid,code);
            }
            securityUser.getSession().setSessionKey(sessionKey);
            return securityUser;
        }catch (Exception e){
            throw new AuthenticationServiceException(e.getMessage());
        }
    }


    @Override
    public SecurityUser loadUserOrRegisterByPhone(String phone) {
        return registerUser(Oauth.PHONE.getName(),phone,null);
    }

    private SecurityUser registerUser(String oauth, String principal, String credentials) {
        userService.registerUser(new RegisterDTO(oauth, principal,credentials,0,RequestUtil.getHeaders()));
        SecurityUser securityUser = (SecurityUser)loadUserByUsername(principal);
        return securityUser;
    }

    private SecurityUser getUser(UserOauth oauth){
        SecurityUser securityUser = new SecurityUser();
        securityUser.setId(oauth.getUser().getId());
        securityUser.setUsername(oauth.getPrincipal());
        securityUser.setPassword(oauth.getCredentials());
        securityUser.getSession().setOauthId(oauth.getId());
        return securityUser;
    }

    private SecurityUser getUser(SecurityUser securityUser, UserOauthSession session){
        if(securityUser==null) {
            securityUser = getUser(session.getOauth());
        }
        securityUser.getSession().setSessionId(session.getId());
        securityUser.getSession().setToken(session.getToken());
        securityUser.getSession().setSessionKey(session.getSessionKey());
        securityUser.getSession().setExpireTime(session.getExpireTime());
        loadMembers(securityUser);
        if(session.getClientType().equals(ClientType.PC_ADMIN.getName())){
            if(securityUser.getOrgs()==null||securityUser.getOrgs().isEmpty()){
                throw new InternalAuthenticationServiceException("您无权登录,请联系管理员");
            }
        }
        return securityUser;
    }

    private void loadMembers(SecurityUser user){
        user.setMembers(memberService.getSecurityMemberListByUserId(user.getId()));
        user.getMembers().addAll(adminService.getSecurityMemberListByUserId(user.getId()));
        if(!user.getMembers().isEmpty()) {
            Long sysId=RequestUtil.getHeaderLong("sysId");
            user.setOrgs(orgService.getSecurityOrgList(user.getMembers().stream().map(t->t.getOrgId()).collect(Collectors.toList()),sysId));
        }
    }

    @Override
    public void sendSmsCode(String phone, String type, HttpServletRequest request) {
        // 生成短信验证码
        ValidateCode code = smsCodeGenerator.generate(new ServletWebRequest(request));
        // 把短信验证码保存到session中
        //request.getSession().setAttribute(SecurityConstants.SESSION_KEY_VERIFY_CODE, code);
        ValidateCodeUtil.saveCode(phone,code);
        // 通过短信供应商写出去
        smsCodeSender.send(phone, code.getCode());
    }

    @Override
    public Object getWeChatPhoneNumber(String encryptedData, String iv) {
        SecurityUser user= SecurityUtil.currentUser();
        if(user.getSession().getSessionKey()==null){
            throw new BusinessException("请用微信小程序登录");
        }
        Object object= wxmp.getPhoneNumber(encryptedData,user.getSession().getSessionKey(),iv);
        return object;
    }


}
