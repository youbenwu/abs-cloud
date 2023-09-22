package com.outmao.ebs.org.dao;


import com.outmao.ebs.org.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.LockModeType;

public interface PermissionDao extends JpaRepository<Permission, Long> {

	@Lock(value = LockModeType.PESSIMISTIC_READ)
	@Query("select p from Permission p where p.id=?1")
	public Permission findByIdForUpdate(Long id);

	@Lock(value = LockModeType.PESSIMISTIC_READ)
	@Query("select p from Permission p where p.url=?1 and p.name=?2")
	public Permission findByUrlAndNameForUpdate(String url, String value);

	@Query("select p.id from Permission p where p.url=?1 and p.name=?2")
	public Long findIdByUrlAndName(String url, String name);


}
