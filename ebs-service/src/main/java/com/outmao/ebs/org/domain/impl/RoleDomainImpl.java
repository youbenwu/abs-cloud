package com.outmao.ebs.org.domain.impl;


import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.org.dao.*;
import com.outmao.ebs.org.domain.RoleDomain;
import com.outmao.ebs.org.domain.conver.RoleMenuVOConver;
import com.outmao.ebs.org.domain.conver.RolePermissionVOConver;
import com.outmao.ebs.org.domain.conver.RoleVOConver;
import com.outmao.ebs.org.dto.*;
import com.outmao.ebs.org.entity.*;
import com.outmao.ebs.org.vo.RoleMenuVO;
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
    private MenuDao menuDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private RolePermissionDao rolePermissionDao;

    @Autowired
    private AccountRoleDao accountRoleDao;

    @Autowired
    private RoleMenuDao roleMenuDao;

    @Autowired
    private MemberRoleDao memberRoleDao;

    private RoleVOConver roleVOConver=new RoleVOConver();

    private RolePermissionVOConver rolePermissionVOConver=new RolePermissionVOConver();

    private RoleMenuVOConver roleMenuVOConver=new RoleMenuVOConver();


    @Transactional
    @Override
    public Role saveRole(RoleDTO request) {

        Role role=request.getId()==null?roleDao.findByOrgIdAndName(request.getOrgId(),request.getName())
                :roleDao.getOne(request.getId());

        if(role==null){
            role=new Role();
            role.setCreateTime(new Date());
            role.setOrg(orgDao.getOne(request.getOrgId()));
        }

        BeanUtils.copyProperties(request,role,"id");
        role.setUpdateTime(new Date());

        roleDao.save(role);

        if(request.getPermissions()!=null){
            setRolePermission(new SetRolePermissionDTO(role.getId(),request.getPermissions()));
        }

        if(request.getMenus()!=null){
            setRoleMenu(new SetRoleMenuDTO(role.getId(),request.getMenus()));
        }

        return role;
    }

    @Transactional
    @Override
    public void deleteRoleById(Long id) {

        Role role=roleDao.getOne(id);

        rolePermissionDao.deleteAllByRoleId(role.getId());

        roleMenuDao.deleteAllByRoleId(id);

        accountRoleDao.deleteAllByRoleId(role.getId());

        memberRoleDao.deleteAllByRoleId(role.getId());

        roleDao.delete(role);

    }


    @Override
    public RoleVO getRoleVOById(Long id) {
        QRole e= QRole.role;
        RoleVO vo=queryOne(e,e.id.eq(id),roleVOConver);
        if(vo!=null){
            vo.setPermissions(getRolePermissionVOList(new GetRolePermissionListDTO(id)));
            vo.setMenus(getRoleMenuVOList(new GetRoleMenuListDTO(id)));
        }
        return vo;
    }

    @Override
    public List<RoleVO> getRoleVOList(GetRoleListDTO request) {
        QRole e= QRole.role;
        Predicate p=e.org.id.eq(request.getOrgId());
        return queryList(e,p,e.sort.asc(),roleVOConver);
    }


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


    @Transactional
    @Override
    public List<RolePermission> setRolePermission(SetRolePermissionDTO request) {

        if(request.getPermissions()==null||request.getPermissions().isEmpty()){
            rolePermissionDao.deleteAllByRoleId(request.getRoleId());
            return new ArrayList<>();
        }

        Role role=roleDao.getOne(request.getRoleId());

        List<RolePermission> permissions=rolePermissionDao.findAllByRoleId(role.getId());

        Map<Long, RolePermission> permissionMap=permissions.stream().collect(Collectors.toMap(t->t.getPermission().getId(), t->t));

        List<RolePermission> list=new ArrayList<>();

        request.getPermissions().forEach(pid->{
            RolePermission rp=permissionMap.get(pid);
            if(rp==null){
                rp = new RolePermission();
                rp.setRole(role);
                rp.setPermission(permissionDao.getOne(pid));
                rp.setCreateTime(new Date());
            }
            list.add(rp);
        });

        rolePermissionDao.saveAll(list);

        rolePermissionDao.deleteAllByRoleIdAndPermissionIdNoIn(role.getId(),request.getPermissions());


        return list;
    }


    @Transactional
    @Override
    public void deleteRolePermissionById(Long id) {
        RolePermission rp=rolePermissionDao.getOne(id);
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
            roleMenu.setRole(roleDao.getOne(request.getRoleId()));
            roleMenu.setMenu(menuDao.getOne(request.getMenuId()));
            roleMenu.setCreateTime(new Date());
            roleMenuDao.save(roleMenu);
        }
        return roleMenu;
    }

    @Transactional
    @Override
    public List<RoleMenu> setRoleMenu(SetRoleMenuDTO request) {

        if(request.getMenus()==null||request.getMenus().isEmpty()){
            roleMenuDao.deleteAllByRoleId(request.getRoleId());
            return new ArrayList<>();
        }

        Role role=roleDao.getOne(request.getRoleId());
        List<RoleMenu> menus=roleMenuDao.findAllByRoleId(request.getRoleId());

        Map<Long,RoleMenu> menuMap=menus.stream().collect(Collectors.toMap(t->t.getMenu().getId(),t->t));

        List<RoleMenu> list=new ArrayList<>();

        request.getMenus().forEach(menuId->{
            RoleMenu rm=menuMap.get(menuId);
            if(rm==null){
                rm=new RoleMenu();
                rm.setRole(role);
                rm.setMenu(menuDao.getOne(menuId));
                rm.setCreateTime(new Date());
            }
            list.add(rm);
        });

        roleMenuDao.saveAll(list);
        roleMenuDao.deleteAllByRoleIdAndMenuIdNotIn(role.getId(),request.getMenus());

        return list;
    }

    @Transactional
    @Override
    public void deleteRoleMenuById(Long id) {
        RoleMenu roleMenu=roleMenuDao.getOne(id);
        roleMenuDao.delete(roleMenu);
    }


    @Override
    public List<RoleMenuVO> getRoleMenuVOList(GetRoleMenuListDTO request) {
        QRoleMenu e=QRoleMenu.roleMenu;
        return queryList(e,e.role.id.eq(request.getRoleId()),roleMenuVOConver);
    }

    @Override
    public List<RoleMenu> getRoleMenuListByRoleIdIn(Collection<Long> roleIdIn) {
        return roleMenuDao.findAllByRoleIdIn(roleIdIn);
    }

}
