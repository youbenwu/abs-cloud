package com.outmao.ebs.org.domain.impl;


import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.org.dao.AdminDao;
import com.outmao.ebs.org.dao.AdminRoleDao;
import com.outmao.ebs.org.dao.OrgDao;
import com.outmao.ebs.org.dao.RoleDao;
import com.outmao.ebs.org.domain.AdminDomain;
import com.outmao.ebs.org.domain.RoleDomain;
import com.outmao.ebs.org.domain.conver.*;
import com.outmao.ebs.org.dto.*;
import com.outmao.ebs.org.entity.*;
import com.outmao.ebs.org.vo.AdminRoleVO;
import com.outmao.ebs.org.vo.AdminVO;
import com.outmao.ebs.org.vo.RolePermissionVO;
import com.outmao.ebs.org.vo.RoleVO;
import com.outmao.ebs.security.vo.SecurityMember;
import com.outmao.ebs.security.vo.SecurityRole;
import com.outmao.ebs.security.vo.SecurityRolePermission;
import com.outmao.ebs.user.dao.UserDao;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;


@Component
public class AdminDomainImpl extends BaseDomain implements AdminDomain {

    @Autowired
    private AdminDao adminDao;

    @Autowired
    private AdminRoleDao adminRoleDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private OrgDao orgDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDomain roleDomain;

    private AdminVOConver adminVOConver=new AdminVOConver();

    private AdminRoleVOConver adminRoleVOConver=new AdminRoleVOConver();

    private RolePermissionVOConver rolePermissionVOConver=new RolePermissionVOConver();

    @Autowired
    private SecurityAdminConver securityAdminConver;
    @Autowired
    private SecurityAdminRoleConver securityAdminRoleConver;
    @Autowired
    private SecurityRolePermissionConver securityRolePermissionConver;

    //@CacheEvict(value = "cache_admin", key = "#request.id",condition ="#request.id!=null")
    @Transactional
    @Override
    public Admin saveAdmin(AdminDTO request) {
        Admin admin=request.getId()==null?
                adminDao.findByOrgIdAndUserId(request.getOrgId(),request.getUserId())
                :adminDao.getOne(request.getId());
        if(admin==null){
            admin=new Admin();
            admin.setOrg(orgDao.getOne(request.getOrgId()));
            admin.setUser(userDao.getOne(request.getUserId()));
            admin.setCreateTime(new Date());
        }

        security.hasPermission(admin.getOrg().getId(),null);

        BeanUtils.copyProperties(request,admin,"id");

        admin.setUpdateTime(new Date());

        adminDao.save(admin);

        if(request.getRole()!=null){
            RoleDTO r=new RoleDTO();
            r.setOrgId(admin.getOrg().getId());
            r.setName(request.getRole());
            r.setValue(request.getRole());
            Role role=roleDomain.saveRole(r);
            saveAdminRole(new AdminRoleDTO(admin.getId(),role.getId()));
        }

        return admin;
    }

    //@CacheEvict(value = "cache_admin", key = "#request.id")
    @Transactional
    @Override
    public void deleteAdmin(DeleteAdminDTO request) {

        Admin admin=adminDao.getOne(request.getId());

        security.hasPermission(admin.getOrg().getId(),null);

        adminRoleDao.deleteAllByAdminId(admin.getId());

        adminDao.delete(admin);

    }


    //@Cacheable(value = "cache_admin", key = "#id",unless = "#result == null")
    @Override
    public AdminVO getAdminVOById(Long id) {
        QAdmin e=QAdmin.admin;
        AdminVO vo=queryOne(e,e.id.eq(id),adminVOConver);

        QAdminRole q=QAdminRole.adminRole;
        List<AdminRoleVO> list=queryList(q,q.admin.id.eq(id),adminRoleVOConver);

        List<RoleVO> roles=list.stream().map(t->t.getRole()).collect(Collectors.toList());

        loadRolePermission(roles);

        vo.setRoles(list);

        return vo;
    }

    private void loadRolePermission(List<RoleVO> roles){
        QRolePermission e=QRolePermission.rolePermission;
        List<RolePermissionVO> rolePermissions=queryList(e,e.role.id.in(roles.stream().map(t->t.getId()).collect(Collectors.toList())),rolePermissionVOConver);

        Map<Long,List<RolePermissionVO>> map=new HashMap<>();

        rolePermissions.forEach(t->{

            List<RolePermissionVO> ps=map.get(t.getRoleId());

            if(ps==null){
                ps=new ArrayList<>();
                map.put(t.getRoleId(),ps);
            }

            ps.add(t);

        });

        roles.forEach(t->{
            t.setPermissions(map.get(t.getId()));
        });

    }

    @Override
    public Page<AdminVO> getAdminVOPage(GetAdminListDTO request, Pageable pageable) {
        QAdmin e=QAdmin.admin;

        Predicate p=null;

        if(request.getKeyword()!=null){
            p=e.name.like("%"+request.getKeyword()+"%")
                    .or(e.user.username.like("%"+request.getKeyword()+"%"))
                    .or(e.user.nickname.like("%"+request.getKeyword()+"%"));

        }

        p=e.org.id.eq(request.getOrgId()).and(p);

        Page<AdminVO> page=queryPage(e,p,adminVOConver,pageable);

        loadAdminRoleList(page.getContent());

        return page;
    }

    private void loadAdminRoleList(List<AdminVO> admins){

        if(admins==null||admins.isEmpty())
            return;

        List<Long> adminIdIn=admins.stream().map(t->t.getId()).collect(Collectors.toList());

        QAdminRole e=QAdminRole.adminRole;

        List<AdminRoleVO> list=queryList(e,e.admin.id.in(adminIdIn),adminRoleVOConver);

        Map<Long, AdminVO> adminsMap=admins.stream().collect(Collectors.toMap(t->t.getId(), t->t));

        list.forEach(t->{
            AdminVO admin=adminsMap.get(t.getAdminId());
            List<AdminRoleVO> roles=admin.getRoles();
            if(roles==null){
                roles=new ArrayList<>();
                admin.setRoles(roles);
            }
            roles.add(t);
        });

    }

    //@CacheEvict(value = "cache_admin", key = "#request.adminId")
    @Transactional
    @Override
    public AdminRole saveAdminRole(AdminRoleDTO request) {

        AdminRole adminRole=adminRoleDao.findByAdminIdAndRoleId(request.getAdminId(),request.getRoleId());

        if(adminRole==null){
            adminRole=new AdminRole();
            adminRole.setCreateTime(new Date());
            adminRole.setAdmin(adminDao.getOne(request.getAdminId()));
            adminRole.setRole(roleDao.getOne(request.getRoleId()));
            adminRoleDao.save(adminRole);
        }

        return adminRole;
    }

    //@CacheEvict(value = "cache_admin", key = "#request.adminId")
    @Transactional
    @Override
    public List<AdminRole> setAdminRole(SetAdminRoleDTO request) {

        Admin admin=adminDao.getOne(request.getAdminId());

        List<AdminRole> roles=adminRoleDao.findAllByAdminId(request.getAdminId());

        List<AdminRole> dels=new ArrayList<>();

        roles.forEach(t->{
            if(!request.getRoles().contains(t.getRole().getId())){
                dels.add(t);
            }
        });

        Map<Long, AdminRole> rolesMap=roles.stream().collect(Collectors.toMap(t->t.getRole().getId(), t->t));

        List<AdminRole> saves=new ArrayList<>();

        request.getRoles().forEach(t->{
            if(!rolesMap.containsKey(t)){
                AdminRole adminRole=new AdminRole();
                adminRole.setCreateTime(new Date());
                adminRole.setAdmin(adminDao.getOne(request.getAdminId()));
                adminRole.setRole(roleDao.getOne(t));
                saves.add(adminRole);
            }
        });

        if(saves.size()>0)
        adminRoleDao.saveAll(saves);

        if(dels.size()>0)
        adminRoleDao.deleteAll(dels);

        return saves;
    }

    //@CacheEvict(value = "cache_admin", key = "#request.adminId")
    @Transactional
    @Override
    public void deleteAdminRole(DeleteAdminRoleDTO request) {

        AdminRole adminRole=adminRoleDao.getOne(request.getId());

        adminRoleDao.delete(adminRole);

    }


    @Override
    public List<SecurityMember> getSecurityMemberListByUserId(Long userId) {
        QAdmin e=QAdmin.admin;
        List<SecurityMember> members=queryList(e,e.user.id.eq(userId),securityAdminConver);
        if(members.isEmpty())
            return members;

        members.forEach(t->{
            t.setRoles(new ArrayList<>());
        });

        Map<Long,SecurityMember> memberMap=members.stream().collect(Collectors.toMap(t->t.getId(),t->t));


        QAdminRole ar=QAdminRole.adminRole;
        List<SecurityRole> roles=queryList(ar,ar.admin.id.in(members.stream().map(t->t.getId()).collect(Collectors.toList())),securityAdminRoleConver);
        if(roles.isEmpty())
            return members;

        roles.forEach(t->{
            t.setPermissions(new ArrayList<>());
            memberMap.get(t.getMemberId()).getRoles().add(t);
        });

        Map<Long,SecurityRole> roleMap=roles.stream().collect(Collectors.toMap(t->t.getId(),t->t));

        QRolePermission rp=QRolePermission.rolePermission;

        List<SecurityRolePermission> permissions=queryList(rp,rp.role.id.in(roles.stream().map(t->t.getId()).collect(Collectors.toList())),securityRolePermissionConver);

        if(permissions.isEmpty())
            return members;


        permissions.forEach(t->{
             roleMap.get(t.getRoleId()).getPermissions().add(t);
        });

        return members;
    }


}
