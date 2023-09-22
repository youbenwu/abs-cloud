package com.outmao.ebs.user.dao;

import com.outmao.ebs.user.entity.IdCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IdCardDao extends JpaRepository<IdCard, Long> {
	
	public IdCard findByUserId(Long userId);

}
