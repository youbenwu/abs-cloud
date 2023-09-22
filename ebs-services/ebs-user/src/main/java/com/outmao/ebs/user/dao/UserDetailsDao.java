package com.outmao.ebs.user.dao;


import com.outmao.ebs.user.entity.UserDetails;
import com.outmao.ebs.user.vo.UserEmailVO;
import com.outmao.ebs.user.vo.UserPhoneVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface UserDetailsDao extends JpaRepository<UserDetails, Long> {
	
	public UserDetails findByUserId(Long userId);

	@Query("select u.user.id,u.email from UserDetails u where u.user.id in ?1 and u.phone is not null")
	public List<Object[]> findAllEmailByUserIdIn(Collection<Long> ids);

	@Query("select u.user.id,u.phone from UserDetails u where u.user.id in ?1 and u.phone is not null")
	public List<Object[]> findAllPhoneByUserIdIn(Collection<Long> ids);


	@Query("select u.user.id as userId,u.email as email from UserDetails u where u.user.id in ?1 and u.phone is not null")
	public List<UserEmailVO> findAllUserEmailByUserIdIn(Collection<Long> ids);

	@Query("select u.user.id as userId,u.phone as Phone from UserDetails u where u.user.id in ?1 and u.phone is not null")
	public List<UserPhoneVO> findAllUserPhoneByUserIdIn(Collection<Long> ids);

}
