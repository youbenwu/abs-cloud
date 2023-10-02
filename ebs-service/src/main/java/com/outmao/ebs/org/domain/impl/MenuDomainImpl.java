package com.outmao.ebs.org.domain.impl;



import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.org.dao.MenuDao;
import com.outmao.ebs.org.domain.MenuDomain;
import com.outmao.ebs.org.domain.conver.MenuVOConver;
import com.outmao.ebs.org.dto.MenuDTO;
import com.outmao.ebs.org.entity.Menu;
import com.outmao.ebs.org.entity.QMenu;
import com.outmao.ebs.org.vo.MenuVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class MenuDomainImpl extends BaseDomain implements MenuDomain {


    @Autowired
    private MenuDao menuDao;


    private MenuVOConver menuVOConver=new MenuVOConver();


    @Transactional
    @Override
    public Menu saveMenu(MenuDTO request) {
        Menu menu=request.getId()==null?null:menuDao.findByIdForUpdate(request.getId());
        if(menu==null){
            menu=new Menu();
            menu.setCreateTime(new Date());
            menu.setLeaf(true);
            if(request.getParentId()!=null){
                Menu parent=menuDao.findByIdForUpdate(request.getParentId());
                menu.setParent(parent);
                menu.setLevel(parent.getLevel()+1);
                if(parent.isLeaf()){
                    parent.setLeaf(false);
                }
            }
        }
        BeanUtils.copyProperties(request,menu);
        menu.setUpdateTime(new Date());
        menuDao.save(menu);
        return menu;
    }

    @Transactional
    @Override
    public void deleteMenuById(Long id) {
        Menu menu=menuDao.getOne(id);
        if(!menu.isLeaf()){
            throw new BusinessException("请先删除下级菜单");
        }
        if(menu.getParent()!=null){
            Menu parent=menuDao.findByIdForUpdate(menu.getParent().getId());
            if(parent.getChildren().size()==1){
                menu.getParent().setLeaf(true);
            }
        }
        menuDao.delete(menu);
    }

    @Override
    public Menu getMenuByPath(String path) {
        return menuDao.findByPath(path);
    }

    @Override
    public long getCount() {
        return menuDao.count();
    }

    @Override
    public List<MenuVO> getMenuVOList() {
        List<MenuVO> list=getAll();
        list=toLevel(list);
        return list;
    }

    @Override
    public List<MenuVO> getMenuVOListByIdIn(Collection<Long> idIn) {
        List<MenuVO> list=getAllByIdIn(idIn);
        list=toLevel(list);
        return list;
    }

    private List<MenuVO> getAllByIdIn(Collection<Long> idIn){
        QMenu e= QMenu.menu;
        List<MenuVO> list=queryList(e,e.id.in(idIn),e.sort.asc(),menuVOConver);
        return list;
    }


    private List<MenuVO> getAll(){
        QMenu e= QMenu.menu;
        List<MenuVO> list=queryList(e,null,e.sort.asc(),menuVOConver);
        return list;
    }

    private List<MenuVO> toLevel(List<MenuVO> vos){

        Map<Long, MenuVO> voMap=vos.stream().collect(Collectors.toMap(t->t.getId(), t->t));

        List<MenuVO> list=new ArrayList<>();

        for(MenuVO vo:vos){
            MenuVO parent=vo.getParentId()==null?null:voMap.get(vo.getParentId());
            if(parent!=null){
                if(parent.getChildren()==null){
                    parent.setChildren(new ArrayList<>());
                }
                parent.getChildren().add(vo);
            }else{
                list.add(vo);
            }
        }

        return list;
    }


    @Override
    public List<Long> getMenuIdListByPathIn(Collection<String> pathIn) {
        return menuDao.findAllIdByPathIn(pathIn);
    }


}
