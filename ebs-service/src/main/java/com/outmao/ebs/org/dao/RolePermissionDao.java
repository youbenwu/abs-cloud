package com.outmao.ebs.org.dao;


import com.outmao.ebs.org.entity.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RolePermissionDao extends JpaRepository<RolePermission, Long> {


	public void deleteAllByRoleId(Long roleId);

	public void deleteAllByRoleIdAndPermissionIdNoIn(Long roleId,List<Long> permissionIdNoIn);

	public RolePermission findByRoleIdAndPermissionId(Long roleId, Long permissionId);

	public List<RolePermission> findAllByRoleId(Long roleId);

	public void deleteAllByPermissionId(Long permissionId);

}

