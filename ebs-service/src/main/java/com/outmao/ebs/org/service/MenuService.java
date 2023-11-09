package com.outmao.ebs.org.service;

import com.outmao.ebs.org.dto.MenuDTO;
import com.outmao.ebs.org.entity.Menu;
import com.outmao.ebs.org.vo.MenuVO;

import java.util.Collection;
import java.util.List;

public interface MenuService {

    public Menu saveMenu(MenuDTO request);

    public void deleteMenuById(Long id);

    public List<MenuVO> getMenuVOList();

    public List<MenuVO> getMenuVOListBySysId(Long sysId);

    public List<MenuVO> getMenuVOListBySysIdAndRoleIdIn(Long sysId, Collection<Long> roleIdIn);

    public void sort(List<Long> ids);

}
