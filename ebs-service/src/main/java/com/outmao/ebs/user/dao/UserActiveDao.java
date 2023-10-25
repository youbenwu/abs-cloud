package com.outmao.ebs.user.dao;

import com.outmao.ebs.user.entity.UserActive;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserActiveDao extends JpaRepository<UserActive,Long> {
}
