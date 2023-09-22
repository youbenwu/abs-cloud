package com.outmao.ebs.sys.domain.impl;


import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.sys.dao.SysDao;
import com.outmao.ebs.sys.dao.SysMenuDao;
import com.outmao.ebs.sys.dao.SysPermissionDao;
import com.outmao.ebs.sys.domain.SysDomain;
import com.outmao.ebs.sys.dto.SetSysMenuDTO;
import com.outmao.ebs.sys.dto.SetSysPermissionDTO;
import com.outmao.ebs.sys.dto.SysDTO;
import com.outmao.ebs.sys.entity.Sys;
import com.outmao.ebs.sys.entity.SysMenu;
import com.outmao.ebs.sys.entity.SysPermission;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class SysDomainImpl extends BaseDomain implements SysDomain {

    @Autowired
    private SysDao sysDao;

    @Autowired
    private SysMenuDao sysMenuDao;

    @Autowired
    private SysPermissionDao sysPermissionDao;



    @Transactional
    @Override
    public Sys saveSys(SysDTO request) {

        Sys sys=request.getId()==null?null:sysDao.getOne(request.getId());

        if(sys==null){
            sys=new Sys();
            sys.setCreateTime(new Date());
        }

        BeanUtils.copyProperties(request,sys);

        sysDao.save(sys);

        return sys;
    }

    @Override
    public Sys getSysById(Long id) {
        return sysDao.findById(id).orElse(null);
    }

    @Override
    public Sys getSysByType(int type) {
        return sysDao.findByType(type);
    }

    @Override
    public long getCount() {
        return sysDao.count();
    }

    @Override
    public Page<Sys> getSysPage(Pageable pageable) {
        return sysDao.findAll(pageable);
    }

    @Transactional
    @Override
    public List<SysMenu> setSysMenu(SetSysMenuDTO request) {
        List<SysMenu> list=sysMenuDao.findAllBySysId(request.getSysId());

        list.forEach(t->{
            if(!request.getMenus().contains(t.getMenuId())){
                sysMenuDao.delete(t);
            }
        });

        Map<Long,SysMenu> listMap=list.stream().collect(Collectors.toMap(t->t.getMenuId(),t->t));

        List<SysMenu> saves=new ArrayList<>();

        request.getMenus().forEach(t->{
            if(!listMap.containsKey(t)){
                SysMenu sysMenu=new SysMenu();
                sysMenu.setCreateTime(new Date());
                sysMenu.setSysId(request.getSysId());
                sysMenu.setMenuId(t);
                saves.add(sysMenu);
            }
        });

        sysMenuDao.saveAll(saves);

        return saves;
    }

    @Override
    public List<SysMenu> getSysMenuListBySysId(Long sysId) {
        return sysMenuDao.findAllBySysId(sysId);
    }

    @Override
    public List<SysPermission> setSysPermission(SetSysPermissionDTO request) {
        List<SysPermission> list=sysPermissionDao.findAllBySysId(request.getSysId());

        list.forEach(t->{
            if(!request.getPermissions().contains(t.getPermissionId())){
                sysPermissionDao.delete(t);
            }
        });

        Map<Long,SysPermission> listMap=list.stream().collect(Collectors.toMap(t->t.getPermissionId(),t->t));

        List<SysPermission> saves=new ArrayList<>();

        request.getPermissions().forEach(t->{
            if(!listMap.containsKey(t)){
                SysPermission sysPermission=new SysPermission();
                sysPermission.setCreateTime(new Date());
                sysPermission.setSysId(request.getSysId());
                sysPermission.setPermissionId(t);
                saves.add(sysPermission);
            }
        });

        sysPermissionDao.saveAll(saves);

        return saves;
    }

    @Override
    public List<SysPermission> getSysPermissionListBySysId(Long sysId) {
        return sysPermissionDao.findAllBySysId(sysId);
    }


}
