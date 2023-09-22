package com.outmao.ebs.sys.dao;

import com.outmao.ebs.sys.entity.SysMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SysMenuDao extends JpaRepository<SysMenu,Long> {


    public SysMenu findBySysIdAndMenuId(Long sysId, Long menuId);

    public List<SysMenu> findAllBySysId(Long sysId);

}
