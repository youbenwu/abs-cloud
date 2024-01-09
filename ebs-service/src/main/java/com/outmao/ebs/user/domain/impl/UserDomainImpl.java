package com.outmao.ebs.user.domain.impl;

import com.outmao.ebs.bbs.common.annotation.BindingSubjectId;
import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.common.kcnamer.KCNamer;
import com.outmao.ebs.common.util.StringUtil;
import com.outmao.ebs.common.util.Validation;
import com.outmao.ebs.user.common.constant.CertificationStatus;
import com.outmao.ebs.user.common.constant.Oauth;
import com.outmao.ebs.user.dao.UserDao;
import com.outmao.ebs.user.dao.UserDetailsDao;
import com.outmao.ebs.user.domain.UserDomain;
import com.outmao.ebs.user.domain.UserOauthDomain;
import com.outmao.ebs.user.domain.conver.*;
import com.outmao.ebs.user.dto.GetUserListDTO;
import com.outmao.ebs.user.dto.RegisterDTO;
import com.outmao.ebs.user.dto.UserDTO;
import com.outmao.ebs.user.dto.UserDetailsDTO;
import com.outmao.ebs.user.entity.*;
import com.outmao.ebs.user.vo.*;
import com.outmao.ebs.wallet.common.annotation.BindingWallet;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Component
public class UserDomainImpl extends BaseDomain implements UserDomain {

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserDetailsDao userDetailsDao;

	@Autowired
	private UserOauthDomain userOauthDomain;


	private SimpleUserVOConver simpleUserVOConver=new SimpleUserVOConver();

	private ContactUserVOConver contactUserVOConver=new ContactUserVOConver();

	private UserVOConver userVOConver=new UserVOConver();

	private UserDetailsVOConver userDetailsVOConver=new UserDetailsVOConver();

	private HuaUserVOConver huaUserVOConver=new HuaUserVOConver();

	@Bean
	private KCNamer kcNamer(){
		System.out.println("=====kcNamer====");
		return new KCNamer();
	}



	@Transactional
	@BindingSubjectId
	@BindingWallet
	@Override
	public User registerUser(RegisterDTO request) {

		Assert.hasText(request.getPrincipal(), "帐号不能为空");
		Assert.isNull(userOauthDomain.getUserAuthByPrincipal(request.getPrincipal()), "帐号已注册");

		// 创建用户
		User user = new User();
		user.setType(request.getType());
		user.setUsername(request.getPrincipal());
		user.setPassword(request.getCredentials());
		//设置昵称
		user.setNickname((String)request.getArgs().get("nickname"));
		//设置注册设备IMEI
		user.setImei((String)request.getArgs().get("imei"));
		//设置注册城市编码
		user.setAreaCode((String)request.getArgs().get("areaCode"));
		//设置注册城市
		user.setArea((String)request.getArgs().get("area"));
		//注册时间
		user.setRegisterTime(new Date());

		// 创建用户详情
		UserDetails details = new UserDetails();
		if (request.getOauth().equals(Oauth.PHONE.getName())) {
			details.setPhone(request.getPrincipal());
		} else if (request.getOauth().equals(Oauth.EMAIL.getName())) {
			details.setEmail(request.getPrincipal());
		}else{
			if(Validation.isMobile(request.getPrincipal())){
				details.setPhone(request.getPrincipal());
			}else if(Validation.isEmail(request.getPrincipal())){
				details.setEmail(request.getPrincipal());
			}
		}
		details.setUser(user);
		user.setDetails(details);

		if(StringUtils.isEmpty(user.getNickname())){
			user.setNickname(kcNamer().getRandomName());
		}

		user.setKeyword(getUserKeyword(user,user.getDetails()));

		// 保存用户信息
		userDao.save(user);

		// 注册授权信息
		userOauthDomain.registerUserOauth(user.getId(),request.getOauth(),request.getPrincipal(),request.getCredentials());


		return user;
	}



	@Override
	public User saveUser(UserDTO request) {
		User user=userDao.getOne(request.getId());

		if(request.getPassword()!=null){
			userOauthDomain.updateCredentials(user.getUsername(),request.getPassword());
		}

		BeanUtils.copyProperties(request,user);

		user.setKeyword(getUserKeyword(user,user.getDetails()));

		userDao.save(user);
		return user;
	}

	@Transactional
	@Override
	public User modifyUser(UserDTO request) {
		User user=userDao.getOne(request.getId());

		if(request.getPassword()!=null){
			userOauthDomain.updateCredentials(user.getUsername(),request.getPassword());
		}
		if(request.getNickname()!=null){
			user.setNickname(request.getNickname());
		}

		if(request.getAvatar()!=null){
			user.setAvatar(request.getAvatar());
		}

		user.setKeyword(getUserKeyword(user,user.getDetails()));

		userDao.save(user);
		return user;
	}

	@Override
	public Page<User> getUserPageByType(int type, Pageable pageable) {
		return userDao.findAllByType(type,pageable);
	}

	@Transactional
	@Override
	public User modifyUserPhone(Long id, String phone) {

		Assert.hasText(phone, "手机号不能为空");

		UserOauth oauth=userOauthDomain.getUserAuthByPrincipal(phone);

		if(oauth!=null&&!oauth.getUser().getId().equals(id)){
			throw new BusinessException("手机号已被注册");
		}

		if(oauth==null) {
			//注册手机号
			userOauthDomain.registerUserOauth(id, Oauth.PHONE.getName(), phone, null);
		}

		//修改用户信息
		UserDetails details=userDetailsDao.findByUserId(id);
		details.setPhone(phone);

		details.getUser().setKeyword(getUserKeyword(details.getUser(),details));

		userDetailsDao.save(details);

        return details.getUser();
	}

	@Transactional
	@Override
	public User modifyUserPassword(Long id,String oldPassword, String password) {
		User user=userDao.getOne(id);
		if(StringUtil.isEmpty(oldPassword)) {
			userOauthDomain.updateCredentials(user.getUsername(), password);
		}else{
			userOauthDomain.updateCredentials(user.getUsername(),oldPassword, password);

		}
		return user;
	}

	@Transactional
	@Override
	public User setUserVerified(Long id, boolean verified, String realName) {
		User user=userDao.findByIdLock(id);
		user.setVerified(verified);
		if(verified){
			user.getDetails().setRealName(realName);
		}else{
			user.getDetails().setRealName(null);
		}
		userDao.save(user);
		return user;
	}

	@Transactional
	@Override
	public User setUserEntVerified(Long id,boolean entVerified,Long enterpriseId,String enterpriseName) {
		User user=userDao.findByIdLock(id);
		if(user.isEntVerified()&&!user.getDetails().getEnterpriseId().equals(enterpriseId)){
			return user;
		}
		user.setEntVerified(entVerified);
		if(entVerified){
			user.getDetails().setEnterpriseId(enterpriseId);
			user.getDetails().setEnterpriseName(enterpriseName);
		}else{
			user.getDetails().setEnterpriseId(null);
			user.getDetails().setEnterpriseName(null);
		}
		userDao.save(user);
		return user;
	}

	@Override
	public User getUserById(Long id) {
		return userDao.findById(id).orElse(null);
	}

	@Override
	public User getUserByUsername(String username) {
		User user= userDao.findByUsername(username);
		if(user==null){
			UserOauth oauth=userOauthDomain.getUserAuthByPrincipal(username);
			if(oauth!=null){
				user=oauth.getUser();
			}
		}
		return user;
	}

	@Override
	public List<SimpleUserVO> getSimpleUserVOListByIdIn(Collection<Long> idIn) {
		QUser e=QUser.user;
		List<SimpleUserVO> list=queryList(e,e.id.in(idIn),simpleUserVOConver);
		return list;
	}

	@Override
	public List<ContactUserVO> getContactUserVOListByIdIn(Collection<Long> idIn) {
		QUser e=QUser.user;
		List<ContactUserVO> list=queryList(e,e.id.in(idIn),contactUserVOConver);
		return list;
	}

	@Override
	public UserVO getUserVOById(Long id) {
		QUser e=QUser.user;
		return queryOne(e,e.id.eq(id),userVOConver);
	}

	@Override
	public Page<UserVO> getUserVOPage(GetUserListDTO request, Pageable pageable) {
		QUser e=QUser.user;
		Predicate p=null;
		if(request.getKeyword()!=null){
			p=e.keyword.like("%"+request.getKeyword()+"%");
		}
		if(request.getType()!=null){
			p=e.type.eq(request.getType()).and(p);
		}
		Page<UserVO> page=queryPage(e,p,userVOConver,pageable);
		return page;
	}

	@Override
	public Page<UserDetailsVO> getUserDetailsVOPage(GetUserListDTO request, Pageable pageable) {
		QUser e=QUser.user;
		Predicate p=null;
		if(request.getKeyword()!=null){
			p=e.keyword.like("%"+request.getKeyword()+"%");
		}
		if(request.getType()!=null){
			p=e.type.eq(request.getType()).and(p);
		}
		Page<UserDetailsVO> page=queryPage(e,p,userDetailsVOConver,pageable);
		return page;
	}

    @Override
    public List<UserDetailsVO> getUserDetailsVOListByIdIn(Collection<Long> idIn) {
        QUser e=QUser.user;
        List<UserDetailsVO> list=queryList(e,e.id.in(idIn),userDetailsVOConver);
        return list;
    }

    //	@SetUserStatsOrder
//	@SetUserStatsInquiry
	@Override
	public Page<HuaUserVO> getHuaUserVOPage(GetUserListDTO request, Pageable pageable) {
		QUser e=QUser.user;
		Predicate p=null;
		if(StringUtil.isNotEmpty(request.getKeyword())){
			p=e.keyword.like("%"+request.getKeyword()+"%");
		}
		Page<HuaUserVO> page=queryPage(e,p,huaUserVOConver,pageable);
		return page;
	}

	@Transactional
	@Override
	public UserDetails modifyUserDetails(UserDetailsDTO request) {

		if(!StringUtils.isEmpty(request.getPhone())){
			this.modifyUserPhone(request.getId(), request.getPhone());
		}

		User user=userDao.getOne(request.getId());
		UserDetails details=user.getDetails();

		if(StringUtil.isNotEmpty(request.getAvatar())){
			user.setAvatar(request.getAvatar());
		}
		if(StringUtil.isNotEmpty(request.getNickname())){
			user.setNickname(request.getNickname());
		}

		if(StringUtil.isNotEmpty(request.getWechat())){
			details.setWeChat(request.getWechat());
		}
		if(StringUtil.isNotEmpty(request.getEmail())){
			details.setEmail(request.getEmail());
		}
		if(StringUtil.isNotEmpty(request.getAddress())){
			details.setAddress(request.getAddress());
		}
		if(request.getBirthday()!=null){
			details.setBirthday(request.getBirthday());
		}
		if(StringUtil.isNotEmpty(request.getHometown())){
			details.setHometown(request.getHometown());
		}
		if(StringUtil.isNotEmpty(request.getCareer())){
			details.setCareer(request.getCareer());
		}

		if(StringUtil.isNotEmpty(request.getCompany())){
			details.setCompany(request.getCompany());
		}
		if(StringUtil.isNotEmpty(request.getRealName())){
			if(user.isVerified()){
				if(!details.getRealName().equals(request.getRealName())){
					throw new BusinessException("已实名，不能修改姓名");
				}
			}
			details.setRealName(request.getRealName());
		}
		if(StringUtil.isNotEmpty(request.getJob())){
			details.setJob(request.getJob());
		}
		if(StringUtil.isNotEmpty(request.getSchool())){
			details.setSchool(request.getSchool());
		}
		if(request.getSex()!=null){
            details.setSex(request.getSex());
		}

		if(request.getAge()!=null){
			details.setAge(request.getAge());
		}
        if(StringUtil.isNotEmpty(request.getHobby())){
            details.setHobby(request.getHobby());
        }
		user.setKeyword(getUserKeyword(user,details));

		userDao.save(user);
		userDetailsDao.save(details);

		return details;
	}

	@Transactional
	@Override
	public UserDetails saveUserDetails(UserDetailsDTO request) {

		User user=userDao.findById(request.getId()).orElse(null);
		UserDetails details = userDetailsDao.findByUserId(user.getId());

		if(user.isVerified()){
           if(!details.getRealName().equals(request.getRealName())){
           	  throw new BusinessException("已实名，不能修改姓名");
		   }
		}

		BeanUtils.copyProperties(request, user);
		BeanUtils.copyProperties(request, details);

		user.setKeyword(getUserKeyword(user,details));

		userDao.save(user);
		userDetailsDao.save(details);

		return details;
	}


	private String getUserKeyword(User user,UserDetails userDetails){
		StringBuffer s=new StringBuffer();
		s.append(user.getUsername());
		if(user.getNickname()!=null){
			s.append(" "+user.getNickname());
		}
		if(userDetails.getPhone()!=null){
			s.append(" "+userDetails.getPhone());
		}
		if(userDetails.getEmail()!=null){
			s.append(" "+userDetails.getEmail());
		}
		if(userDetails.getRealName()!=null){
			s.append(" "+userDetails.getRealName());
		}
		if(userDetails.getCompany()!=null){
			s.append(" "+userDetails.getCompany());
		}
		return s.toString();
	}


	@Override
	public UserDetails getUserDetailsByUserId(Long userId) {
		return userDetailsDao.findByUserId(userId);
	}


	@Override
	public UserDetailsVO getUserDetailsVOByUserId(Long userId) {
		QUser e=QUser.user;
		return queryOne(e,e.id.eq(userId),userDetailsVOConver);
	}

	@Override
	public long getUserCount() {
		return userDao.count();
	}


}
