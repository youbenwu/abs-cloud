package com.outmao.ebs.org.domain;

import com.outmao.ebs.org.dto.MenuDTO;
import com.outmao.ebs.org.entity.Menu;
import com.outmao.ebs.org.vo.MenuVO;

import java.util.Collection;
import java.util.List;

public interface MenuDomain {

    public Menu saveMenu(MenuDTO request);

    public void deleteMenuById(Long id);

    public Menu getMenuByPath(String path);

    public long getCount();

    public List<MenuVO> getMenuVOList();

    public List<MenuVO> getMenuVOListByIdIn(Collection<Long> idIn);

    public List<Long> getMenuIdListByPathIn(Collection<String> pathIn);

}
