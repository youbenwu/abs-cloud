package com.outmao.ebs.user.dao;


import com.outmao.ebs.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Collection;
import java.util.List;

public interface UserDao extends JpaRepository<User, Long> {

	public User findByUsername(String username);

	public List<User> findAllByIdIn(Collection<Long> ids);

	public Page<User> findAllByType(int type, Pageable pageable);


	@Query("select u.id from User u")
	public List<Long> findIdAll(Pageable pageable);

	@Query("select u.keyword from User u where u.id=?1")
	public String findKeywordById(Long id);


}
