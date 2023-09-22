package com.outmao.ebs.org.dao;

import com.outmao.ebs.org.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdminDao extends JpaRepository<Admin,Long> {

    @Query("select e.id from  Admin e where e.user.id=?1")
    public List<Long> findAllIdByUserId(Long userId);

    public Admin findByOrgIdAndUserId(Long orgId, Long userId);

}
