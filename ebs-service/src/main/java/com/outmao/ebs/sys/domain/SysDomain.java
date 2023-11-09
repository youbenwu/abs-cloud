package com.outmao.ebs.sys.domain;

import com.outmao.ebs.sys.dto.SetSysMenuDTO;
import com.outmao.ebs.sys.dto.SetSysPermissionDTO;
import com.outmao.ebs.sys.dto.SysDTO;
import com.outmao.ebs.sys.entity.Sys;
import com.outmao.ebs.sys.entity.SysMenu;
import com.outmao.ebs.sys.entity.SysPermission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface SysDomain {

    public Sys saveSys(SysDTO request);

    public void deleteSysById(Long id);

    public Sys getSysById(Long id);

    public Sys getSysByType(int type);

    public long getCount();

    public Page<Sys> getSysPage(Pageable pageable);


    public List<SysMenu> setSysMenu(SetSysMenuDTO request);

    public List<SysMenu> getSysMenuListBySysId(Long sysId);


    public List<SysPermission> setSysPermission(SetSysPermissionDTO request);

    public List<SysPermission> getSysPermissionListBySysId(Long sysId);

}
