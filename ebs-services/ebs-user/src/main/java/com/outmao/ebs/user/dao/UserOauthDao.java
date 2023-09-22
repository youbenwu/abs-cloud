package com.outmao.ebs.user.dao;


import com.outmao.ebs.user.entity.User;
import com.outmao.ebs.user.entity.UserOauth;
import com.outmao.ebs.user.vo.UserOpenIdVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface UserOauthDao extends JpaRepository<UserOauth, Long> {
	
	public UserOauth findByPrincipal(String principal);

	public UserOauth findByUserAndOauth(User user, String oauth);

	@Query("select u.user.id,u.principal from UserOauth u where u.user.id in ?1 and u.oauth='WECHAT'")
	public List<Object[]> findAllOpenIdByUserIdIn(Collection<Long> ids);

	@Query("select u.user.id as userId,u.principal as openId from UserOauth u where u.user.id in ?1 and u.oauth='WECHAT'")
	public List<UserOpenIdVO> findAllUserOpenIdByUserIdIn(Collection<Long> ids);

}
