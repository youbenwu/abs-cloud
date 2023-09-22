package com.outmao.ebs.sys.service.impl;


import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.util.JsonUtil;
import com.outmao.ebs.common.util.ResourceUtil;
import com.outmao.ebs.org.domain.MenuDomain;
import com.outmao.ebs.org.domain.PermissionDomain;
import com.outmao.ebs.org.vo.MenuVO;
import com.outmao.ebs.org.vo.PermissionVO;
import com.outmao.ebs.sys.domain.SysDomain;
import com.outmao.ebs.sys.dto.SetSysMenuDTO;
import com.outmao.ebs.sys.dto.SetSysPermissionDTO;
import com.outmao.ebs.sys.dto.SysDTO;
import com.outmao.ebs.sys.entity.Sys;
import com.outmao.ebs.sys.entity.SysMenu;
import com.outmao.ebs.sys.entity.SysPermission;
import com.outmao.ebs.sys.service.SysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SysServiceImpl extends BaseDomain implements SysService , CommandLineRunner {



    @Autowired
    private SysDomain sysDomain;

    @Autowired
    private MenuDomain menuDomain;

    @Autowired
    private PermissionDomain permissionDomain;

    @Override
    public void run(String... args) throws Exception {
        if(sysDomain.getCount()>0)
            return;
        String activeJson= ResourceUtil.getResourceString("syss.json");
        Map<String,String> active= JsonUtil.fromJson(activeJson,Map.class);
        String file="syss_"+active.get("active")+".json";
        String json= ResourceUtil.getResourceString(file);
        List<SysDTO> list=(List<SysDTO>)JsonUtil.fromJson(json,List.class,SysDTO.class);

        for(SysDTO dto:list){
            Sys sys=saveSys(dto);
            loadMenus(sys);
            loadPers(sys);
        }

    }

    private void loadPers(Sys sys){
        String pers_file="pers_sys_"+sys.getSysNo()+".json";
        String pers_json= ResourceUtil.getResourceString(pers_file);
        List<PermissionVO> pers= (List<PermissionVO>)JsonUtil.fromJson(pers_json,List.class,PermissionVO.class);
        List<Long> ids=new ArrayList<>();
        getIds(pers,ids);
        if(!ids.isEmpty()){
            SetSysPermissionDTO setSysPermissionDTO=new SetSysPermissionDTO();
            setSysPermissionDTO.setSysId(sys.getId());
            setSysPermissionDTO.setPermissions(ids);
            setSysPermission(setSysPermissionDTO);
        }
    }

    private void getIds(List<PermissionVO> pers, Collection<Long> ids){
        for (PermissionVO vo:pers){
            Long id=permissionDomain.getIdByUrlAndName(vo.getUrl(),vo.getName());
            if(id!=null){
                ids.add(id);
            }
            if(vo.getChildren()!=null&&vo.getChildren().size()>0){
                getIds(vo.getChildren(),ids);
            }
        }
    }

    private void loadMenus(Sys sys){
        String menus_file="menus_sys_"+sys.getSysNo()+".json";
        String menus_json= ResourceUtil.getResourceString(menus_file);
        List<MenuVO> menus= (List<MenuVO>)JsonUtil.fromJson(menus_json,List.class,MenuVO.class);
        List<String> menu_paths=new ArrayList<>();
        getPaths(menus,menu_paths);
        List<Long> menuIds=menuDomain.getMenuIdListByPathIn(menu_paths);
        if(!menuIds.isEmpty()) {
            SetSysMenuDTO setSysMenuDTO = new SetSysMenuDTO();
            setSysMenuDTO.setSysId(sys.getId());
            setSysMenuDTO.setMenus(menuIds);
            setSysMenu(setSysMenuDTO);
        }
    }

    private void getPaths(List<MenuVO> menus,List<String> menu_paths){
        for (MenuVO vo:menus){
            menu_paths.add(vo.getPath());
            if(vo.getChildren()!=null&&vo.getChildren().size()>0){
                getPaths(vo.getChildren(),menu_paths);
            }
        }
    }

    @Override
    public Sys saveSys(SysDTO request) {
        return sysDomain.saveSys(request);
    }


    @Override
    public Sys getSysById(Long id) {
        return sysDomain.getSysById(id);
    }

    @Override
    public Sys getSysByType(int type) {
        return sysDomain.getSysByType(type);
    }


    @Override
    public Page<Sys> getSysPage(Pageable pageable) {
        return sysDomain.getSysPage(pageable);
    }


    @Override
    public List<SysMenu> setSysMenu(SetSysMenuDTO request) {
        return sysDomain.setSysMenu(request);
    }

    @Override
    public List<SysMenu> getSysMenuListBySysId(Long sysId) {
        return sysDomain.getSysMenuListBySysId(sysId);
    }

    @Override
    public List<SysPermission> setSysPermission(SetSysPermissionDTO request) {
        return sysDomain.setSysPermission(request);
    }

    @Override
    public List<SysPermission> getSysPermissionListBySysId(Long sysId) {
        return sysDomain.getSysPermissionListBySysId(sysId);
    }


}
