package com.outmao.ebs.org.dao;

import com.outmao.ebs.org.entity.AdminRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminRoleDao extends JpaRepository<AdminRole,Long> {

    public void deleteAllByAdminId(Long adminId);

    public void deleteAllByRoleId(Long roleId);

    public AdminRole findByAdminIdAndRoleId(Long adminId, Long roleId);

    public List<AdminRole> findAllByAdminId(Long adminId);

}
