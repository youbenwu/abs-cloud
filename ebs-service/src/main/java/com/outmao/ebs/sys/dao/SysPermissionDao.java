package com.outmao.ebs.sys.dao;

import com.outmao.ebs.sys.entity.SysPermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SysPermissionDao extends JpaRepository<SysPermission,Long> {

    public SysPermission findBySysIdAndPermissionId(Long sysId, Long permissionId);

    public List<SysPermission> findAllBySysId(Long sysId);

    public void deleteAllBySysId(Long sysId);
}
