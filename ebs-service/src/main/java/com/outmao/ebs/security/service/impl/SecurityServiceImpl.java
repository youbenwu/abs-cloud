package com.outmao.ebs.security.service.impl;



import cn.jiguang.common.utils.StringUtils;
import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.common.services.wxmp.WXMP;
import com.outmao.ebs.common.services.wxmp.WXMPSessionResult;
import com.outmao.ebs.common.services.wxmp.WXPhoneResult;
import com.outmao.ebs.common.util.RequestUtil;
import com.outmao.ebs.org.entity.*;
import com.outmao.ebs.org.service.AccountService;
import com.outmao.ebs.org.service.MemberService;
import com.outmao.ebs.org.service.OrgService;
import com.outmao.ebs.org.vo.CacheOrgVO;
import com.outmao.ebs.org.vo.OrgVO;
import com.outmao.ebs.security.configuration.SecurityConstants;
import com.outmao.ebs.security.service.SecurityService;
import com.outmao.ebs.security.service.conver.*;
import com.outmao.ebs.security.util.SecurityUtil;
import com.outmao.ebs.security.validate.ValidateCode;
import com.outmao.ebs.security.validate.ValidateCodeUtil;
import com.outmao.ebs.security.validate.sms.SmsCodeGenerator;
import com.outmao.ebs.security.validate.sms.SmsCodeSender;
import com.outmao.ebs.security.vo.*;
import com.outmao.ebs.sys.entity.QSys;
import com.outmao.ebs.user.common.constant.ClientType;
import com.outmao.ebs.user.common.constant.Oauth;
import com.outmao.ebs.user.dto.RegisterDTO;
import com.outmao.ebs.user.dto.SetAuthenticatedDTO;
import com.outmao.ebs.user.entity.UserOauth;
import com.outmao.ebs.user.entity.UserOauthSession;
import com.outmao.ebs.user.service.UserService;
import com.querydsl.core.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SecurityServiceImpl extends BaseDomain implements SecurityService {


    @Autowired
    private OrgService orgService;


    @Autowired
    private UserService userService;


    @Autowired
    private MemberService memberService;

    @Autowired
    private AccountService accountService;


    @Autowired
    private WXMP wxmp;

    @Autowired
    private SmsCodeSender smsCodeSender;

    @Autowired
    private SmsCodeGenerator smsCodeGenerator;

    private SecurityAccountConver securityAccountConver=new SecurityAccountConver();
    private SecurityAccountRoleConver securityAccountRoleConver=new SecurityAccountRoleConver();
    private SecurityMemberConver securityMemberConver=new SecurityMemberConver();
    private SecurityMemberRoleConver securityMemberRoleConver=new SecurityMemberRoleConver();
    private SecurityOrgConver securityOrgConver=new SecurityOrgConver();
    private SecurityRolePermissionConver securityRolePermissionConver=new SecurityRolePermissionConver();


    @Override
    public boolean hasPermission(Long orgId, String url, String permission) {
        if(orgId==null){
            orgId=orgService.getCacheOrgVO().getId();
        }
        if(SecurityUtil.hasPermission(orgId,url,permission)){
            return true;
        }
        CacheOrgVO org=orgService.getCacheOrgVOById(orgId);
        if(org.getParents()!=null&&org.getParents().size()>0){
            for(Long t : org.getParents()){
                if(SecurityUtil.hasPermission(t,url,permission)){
                    return true;
                }
            }
        }
        while (org.getParent()!=null){
            org=org.getParent();
            if(SecurityUtil.hasPermission(org.getId(),url,permission)){
                return true;
            }
            if(org.getParents()!=null&&org.getParents().size()>0){
                for(Long t : org.getParents()){
                    if(SecurityUtil.hasPermission(t,url,permission)){
                        return true;
                    }
                }
            }
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
            //String unionid=r.getUnionid();
            String openid=r.getOpenid();
            String sessionKey=r.getSession_key();

            UserOauth oauth=userService.getUserAuthByPrincipal(openid);

            SecurityUser securityUser=oauth!=null?getUser(oauth):null;
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
    public SecurityUser loadUserOrRegisterByWx(String session_code,String phone_code) {
        try {
            WXMPSessionResult r= wxmp.getSession(session_code);
            if(r.getErrcode()!=0){
                throw new AuthenticationServiceException(r.getErrmsg());
            }
            WXPhoneResult phoneResult=wxmp.getWxPhone(phone_code);

            if(phoneResult.getErrcode()!=0){
                throw new AuthenticationServiceException(r.getErrmsg());
            }

            String session_key=r.getSession_key();
            String unionid=r.getUnionid();
            String openid=r.getOpenid();
            String phone=phoneResult.getPhone_info().getPhoneNumber();
            String nickname=null;

            String uid= StringUtils.isEmpty(unionid)?openid:unionid;
            UserOauth wxoauth=userService.getUserAuthByPrincipal(uid);
            UserOauth oauth=userService.getUserAuthByPrincipal(phone);
            if(wxoauth==null){
                if(oauth==null){
                    Map<String,Object> params=new HashMap<>();
                    if(nickname!=null) {
                        params.put(SecurityConstants.PARAMETER_KEY_NICKNAME, nickname);
                    }
                    userService.registerUser(new RegisterDTO(Oauth.PHONE.getName(), phone,session_key,0,params));
                    oauth=userService.getUserAuthByPrincipal(phone);
                }
                wxoauth=userService.registerUserOauth(oauth.getUser().getId(),Oauth.WECHAT.getName(),uid,session_key);
            }else{
                if(oauth==null){
                    userService.registerUserOauth(wxoauth.getUser().getId(),Oauth.PHONE.getName(),phone,session_key);
                }
            }


            SecurityUser securityUser=getUser(wxoauth);
            securityUser.getSession().setSessionKey(session_key);

            return securityUser;
        }catch (Exception e){
            throw new AuthenticationServiceException(e.getMessage());
        }
    }

    @Override
    public SecurityUser loadUserOrRegisterByPhone(String phone) {
        UserOauth oauth=userService.getUserAuthByPrincipal(phone);
        if(oauth!=null){
            return getUser(oauth);
        }
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
        user.setMembers(getSecurityMemberListByUserId(user.getId()));
        user.getMembers().addAll(getSecurityAcccountListByUserId(user.getId()));
        if(!user.getMembers().isEmpty()) {
            Long sysId=RequestUtil.getHeaderLong("sysId");
            user.setOrgs(getSecurityOrgList(user.getMembers().stream().map(t->t.getOrgId()).collect(Collectors.toList()),sysId));
        }
    }

    private List<SecurityOrg> getSecurityOrgList(Collection<Long> orgIdIn, Long sysId) {
        QOrg e=QOrg.org;
        List<SecurityOrg> list=queryList(e,e.id.in(orgIdIn),securityOrgConver);

        if(list.isEmpty())
            return list;


        QSys s=QSys.sys;

        if(sysId!=null){
            Tuple t=QF.select(s.id,s.name,s.type).from(s).where(s.id.eq(sysId)).fetchOne();
            list.forEach(org->{
                if(t.get(s.type).equals(org.getOrgType())){
                    org.setSysId(t.get(s.id));
                    org.setSysName(t.get(s.name));
                }
            });
        }else {
            List<Tuple> ss = QF.select(s.id, s.name, s.type).from(s).where(s.type.in(list.stream().map(t -> t.getOrgType()).collect(Collectors.toList()))).fetch();
            Map<Integer, Tuple> sMap = ss.stream().collect(Collectors.toMap(t -> t.get(s.type), t -> t));
            list.forEach(org -> {
                Tuple t = sMap.get(org.getOrgType());
                if (t != null) {
                    org.setSysId(t.get(s.id));
                    org.setSysName(t.get(s.name));
                }
            });
        }

        return list.stream().filter(t->t.getSysId()!=null).collect(Collectors.toList());
    }

    private List<SecurityMember> getSecurityMemberListByUserId(Long userId) {
        QMember e=QMember.member;
        List<SecurityMember> members=queryList(e,e.user.id.eq(userId),securityMemberConver);
        if(members.isEmpty())
            return members;

        members.forEach(t->{
            t.setRoles(new ArrayList<>());
        });

        Map<Long,SecurityMember> memberMap=members.stream().collect(Collectors.toMap(t->t.getId(), t->t));


        QMemberRole ar=QMemberRole.memberRole;
        List<SecurityRole> roles=queryList(ar,ar.member.id.in(members.stream().map(t->t.getId()).collect(Collectors.toList())),securityMemberRoleConver);
        if(roles.isEmpty())
            return members;

        roles.forEach(t->{
            t.setPermissions(new ArrayList<>());
            memberMap.get(t.getMemberId()).getRoles().add(t);
        });

        Map<Long, SecurityRole> roleMap=roles.stream().collect(Collectors.toMap(t->t.getId(), t->t));

        QRolePermission rp=QRolePermission.rolePermission;

        List<SecurityRolePermission> permissions=queryList(rp,rp.role.id.in(roles.stream().map(t->t.getId()).collect(Collectors.toList())),securityRolePermissionConver);

        if(permissions.isEmpty())
            return members;


        permissions.forEach(t->{
            roleMap.get(t.getRoleId()).getPermissions().add(t);
        });

        return members;
    }

    private List<SecurityMember> getSecurityAcccountListByUserId(Long userId) {
        QAccount e=QAccount.account;
        List<SecurityMember> members=queryList(e,e.user.id.eq(userId),securityAccountConver);
        if(members.isEmpty())
            return members;

        members.forEach(t->{
            t.setRoles(new ArrayList<>());
        });

        Map<Long,SecurityMember> memberMap=members.stream().collect(Collectors.toMap(t->t.getId(),t->t));


        QAccountRole ar=QAccountRole.accountRole;
        List<SecurityRole> roles=queryList(ar,ar.account.id.in(members.stream().map(t->t.getId()).collect(Collectors.toList())),securityAccountRoleConver);
        if(roles.isEmpty())
            return members;

        roles.forEach(t->{
            t.setPermissions(new ArrayList<>());
            memberMap.get(t.getMemberId()).getRoles().add(t);
        });

        Map<Long,SecurityRole> roleMap=roles.stream().collect(Collectors.toMap(t->t.getId(),t->t));

        QRolePermission rp=QRolePermission.rolePermission;

        List<SecurityRolePermission> permissions=queryList(rp,rp.role.id.in(roles.stream().map(t->t.getId()).collect(Collectors.toList())),securityRolePermissionConver);

        if(permissions.isEmpty())
            return members;


        permissions.forEach(t->{
            roleMap.get(t.getRoleId()).getPermissions().add(t);
        });

        return members;
    }

    @Override
    public void sendSmsCode(String phone, String type, HttpServletRequest request) {
        // 生成短信验证码
        ValidateCode code = smsCodeGenerator.generate(new ServletWebRequest(request));
        //测试用
        code.setCode("123456");
        // 把短信验证码保存到session中
        //request.getSession().setAttribute(SecurityConstants.SESSION_KEY_VERIFY_CODE, code);
        ValidateCodeUtil.saveCode(phone,code);
        // 通过短信供应商写出去
        smsCodeSender.send(phone, code.getCode());
    }


    @Override
    public WXMPSessionResult getWeChatSession(String code) {
        try {
            return wxmp.getSession(code);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
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


    @Override
    public Object getWeChatPhoneNumber(String code) {
        try {
            return wxmp.getWxPhone(code);
        }catch (Exception e){
            return new BusinessException(e.getMessage());
        }
    }
}
