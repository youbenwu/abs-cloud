package com.outmao.ebs.org.dao;

import com.outmao.ebs.org.entity.RoleMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface RoleMenuDao extends JpaRepository<RoleMenu,Long> {

    public void deleteAllByRoleId(Long roleId);

    public void deleteAllByMenuId(Long menuId);

    public void deleteAllByRoleIdAndMenuIdNotIn(Long roleId,List<Long> menuIdNotIn);

    public RoleMenu findByRoleIdAndMenuId(Long roleId, Long menuId);

    public List<RoleMenu> findAllByRoleId(Long roleId);

    public List<RoleMenu> findAllByRoleIdIn(Collection<Long> roleIdIn);

    public List<RoleMenu> findAllByRoleIdAndMenuIdIn(Long roleId, Collection<Long> menuIdIn);

}
