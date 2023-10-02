package com.outmao.ebs.org.service.impl;



import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.common.util.JsonUtil;
import com.outmao.ebs.common.util.ResourceUtil;
import com.outmao.ebs.org.domain.AccountDomain;
import com.outmao.ebs.org.domain.MenuDomain;
import com.outmao.ebs.org.domain.RoleDomain;
import com.outmao.ebs.org.dto.MenuDTO;
import com.outmao.ebs.org.entity.Menu;
import com.outmao.ebs.org.entity.RoleMenu;
import com.outmao.ebs.org.service.MenuService;
import com.outmao.ebs.org.vo.MenuVO;
import com.outmao.ebs.sys.domain.SysDomain;
import com.outmao.ebs.sys.entity.SysMenu;
import com.outmao.ebs.user.domain.UserDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Order(0)
@Service
public class MenuServiceImpl extends BaseService implements MenuService , CommandLineRunner {


    @Autowired
    private MenuDomain menuDomain;


    @Autowired
    private SysDomain sysDomain;

    @Autowired
    private RoleDomain roleDomain;


    @Override
    public void run(String... args) throws Exception {

        if(menuDomain.getCount()>0)
            return;

       String json= ResourceUtil.getResourceString("menus.json");

       System.out.println(json);

       List<MenuVO> menus= (List<MenuVO>) JsonUtil.fromJson(json,List.class,MenuVO.class);

       saveMenus(menus,null,0);

    }

    private int saveMenus(List<MenuVO> menus,Long parentId,int sort){
        for (MenuVO vo:menus){
            MenuDTO menuDTO=new MenuDTO();
            menuDTO.setSort(sort++);
            menuDTO.setParentId(parentId);
            menuDTO.setName(vo.getName());
            menuDTO.setPath(vo.getPath());
            menuDTO.setDescription(vo.getDescription());
            Menu menu=saveMenu(menuDTO);
            if(vo.getChildren()!=null&&vo.getChildren().size()>0){
                sort=saveMenus(vo.getChildren(),menu.getId(),sort);
            }
        }
        return sort;
    }

    @Override
    public Menu saveMenu(MenuDTO request) {
        return menuDomain.saveMenu(request);
    }

    @Override
    public void deleteMenuById(Long id) {
        menuDomain.deleteMenuById(id);
    }

    @Override
    public List<MenuVO> getMenuVOList() {
        return menuDomain.getMenuVOList();
    }


    @Override
    public List<MenuVO> getMenuVOListBySysId(Long sysId) {
        List<SysMenu> sysMenus=sysDomain.getSysMenuListBySysId(sysId);
        return menuDomain.getMenuVOListByIdIn(sysMenus.stream().map(t->t.getMenuId()).collect(Collectors.toList()));
    }

    @Override
    public List<MenuVO> getMenuVOListBySysIdAndRoleIdIn(Long sysId, Collection<Long> roleIdIn) {
        List<SysMenu> sysMenus=sysDomain.getSysMenuListBySysId(sysId);
        List<RoleMenu> roleMenus=roleDomain.getRoleMenuListByRoleIdIn(roleIdIn);

        if(sysMenus.isEmpty()){
            return new ArrayList<>(0);
        }

        Collection<Long> ids = sysMenus.stream().map(t->t.getMenuId()).collect(Collectors.toList());


        if(!roleMenus.isEmpty()){
            Collection<Long> rIds = roleMenus.stream().map(t->t.getMenu().getId()).collect(Collectors.toList());
            ids=ids.stream().filter(t->rIds.contains(t)).collect(Collectors.toList());
        }

        return menuDomain.getMenuVOListByIdIn(ids);

    }


}
