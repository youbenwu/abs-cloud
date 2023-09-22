package com.outmao.ebs.org.domain.impl;


import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.org.dao.*;
import com.outmao.ebs.org.domain.RoleDomain;
import com.outmao.ebs.org.domain.conver.RolePermissionVOConver;
import com.outmao.ebs.org.domain.conver.RoleVOConver;
import com.outmao.ebs.org.dto.*;
import com.outmao.ebs.org.entity.*;
import com.outmao.ebs.org.vo.RolePermissionVO;
import com.outmao.ebs.org.vo.RoleVO;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class RoleDomainImpl extends BaseDomain implements RoleDomain {

    @Autowired
    private OrgDao orgDao;
    @Autowired
    private PermissionDao permissionDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private RolePermissionDao rolePermissionDao;

    @Autowired
    private AdminRoleDao adminRoleDao;

    @Autowired
    private RoleMenuDao roleMenuDao;

    @Autowired
    private MemberRoleDao memberRoleDao;

    private RoleVOConver roleVOConver=new RoleVOConver();

    private RolePermissionVOConver rolePermissionVOConver=new RolePermissionVOConver();


    @Transactional
    @Override
    public Role saveRole(RoleDTO request) {
        Role role=request.getId()==null?roleDao.findByOrgIdAndValue(request.getOrgId(),request.getValue())
                :roleDao.getOne(request.getId());

        if(role==null){
            role=new Role();
            role.setCreateTime(new Date());
            role.setOrg(orgDao.getOne(request.getOrgId()));
        }

        BeanUtils.copyProperties(request,role,"id");
        role.setUpdateTime(new Date());

        roleDao.save(role);

        return role;
    }

    //@CacheEvict(value = "cache_admin", allEntries = true)
    @Transactional
    @Override
    public void deleteRole(DeleteRoleDTO request) {

        Role role=roleDao.getOne(request.getId());

        rolePermissionDao.deleteAllByRoleId(role.getId());

        adminRoleDao.deleteAllByRoleId(role.getId());

        memberRoleDao.deleteAllByRoleId(role.getId());

        roleDao.delete(role);

    }


    @Override
    public List<RoleVO> getRoleVOList(GetRoleListDTO request) {
        QRole e= QRole.role;
        Predicate p=e.org.id.eq(request.getOrgId());
        return queryList(e,p,e.sort.asc(),roleVOConver);
    }


    //@CacheEvict(value = "cache_admin", allEntries = true)
    @Transactional
    @Override
    public RolePermission saveRolePermission(RolePermissionDTO request) {
        RolePermission rp=rolePermissionDao.findByRoleIdAndPermissionId(request.getRoleId(),request.getPermissionId());

        if(rp==null){
            rp=new RolePermission();
            rp.setRole(roleDao.getOne(request.getRoleId()));
            rp.setPermission(permissionDao.getOne(request.getPermissionId()));
            rp.setCreateTime(new Date());
            rolePermissionDao.save(rp);
        }
        return rp;
    }

    //@CacheEvict(value = "cache_admin", allEntries = true)
    @Transactional
    @Override
    public List<RolePermission> setRolePermission(SetRolePermissionDTO request) {

        Role role=roleDao.getOne(request.getRoleId());

        List<RolePermission> list=rolePermissionDao.findAllByRoleId(request.getRoleId());

        Map<Long, Permission> permissions=list.stream().collect(Collectors.toMap(t->t.getPermission().getId(), t->t.getPermission()));

        List<RolePermission> dels=new  ArrayList();

        for (RolePermission p:list) {
            if(!request.getPermissions().contains(p.getPermission().getId())){
                dels.add(p);
            }
        }

        List<RolePermission> saves=new ArrayList(request.getPermissions().size());

        for(Long pid:request.getPermissions()){
            if(!permissions.containsKey(pid)) {
                RolePermission rp = new RolePermission();
                rp.setRole(roleDao.getOne(request.getRoleId()));
                rp.setPermission(permissionDao.getOne(pid));
                rp.setCreateTime(new Date());
                saves.add(rp);
            }
        }

        rolePermissionDao.saveAll(saves);
        rolePermissionDao.deleteAll(dels);

        return saves;
    }

    //@CacheEvict(value = "cache_admin", allEntries = true)
    @Transactional
    @Override
    public void deleteRolePermission(DeleteRolePermissionDTO request) {
        RolePermission rp=rolePermissionDao.getOne(request.getId());
        rolePermissionDao.delete(rp);
    }

    @Override
    public List<RolePermissionVO> getRolePermissionVOList(GetRolePermissionListDTO request) {

        QRolePermission e=QRolePermission.rolePermission;

        Predicate p=e.role.id.eq(request.getRoleId());

        return queryList(e,p,rolePermissionVOConver);

    }

    @Transactional
    @Override
    public RoleMenu saveRoleMenu(RoleMenuDTO request) {
        RoleMenu roleMenu=roleMenuDao.findByRoleIdAndMenuId(request.getRoleId(),request.getMenuId());
        if(roleMenu==null){
            roleMenu=new RoleMenu();
            roleMenu.setRoleId(request.getRoleId());
            roleMenu.setMenuId(request.getMenuId());
            roleMenu.setCreateTime(new Date());
            roleMenuDao.save(roleMenu);
        }
        return roleMenu;
    }

    @Transactional
    @Override
    public List<RoleMenu> setRoleMenu(SetRoleMenuDTO request) {
        List<RoleMenu> list=roleMenuDao.findAllByRoleId(request.getRoleId());

        list.forEach(t->{
            if(!request.getMenus().contains(t.getMenuId())){
                roleMenuDao.delete(t);
            }
        });

        Map<Long,RoleMenu> listMap=list.stream().collect(Collectors.toMap(t->t.getMenuId(),t->t));

        List<RoleMenu> saves=new ArrayList<>();
        request.getMenus().forEach(t->{
            if(!listMap.containsKey(t)){
                RoleMenu roleMenu=new RoleMenu();
                roleMenu.setRoleId(request.getRoleId());
                roleMenu.setMenuId(t);
                roleMenu.setCreateTime(new Date());
                saves.add(roleMenu);
            }
        });

        roleMenuDao.saveAll(saves);

        return saves;
    }

    @Transactional
    @Override
    public void deleteRoleMenuById(Long id) {
        RoleMenu roleMenu=roleMenuDao.getOne(id);
        roleMenuDao.delete(roleMenu);
    }


    @Override
    public List<RoleMenu> getRoleMenuListByRoleId(Long roleId) {
        return roleMenuDao.findAllByRoleId(roleId);
    }


    @Override
    public List<RoleMenu> getRoleMenuListByRoleIdIn(Collection<Long> roleIdIn) {
        return roleMenuDao.findAllByRoleIdIn(roleIdIn);
    }
}
