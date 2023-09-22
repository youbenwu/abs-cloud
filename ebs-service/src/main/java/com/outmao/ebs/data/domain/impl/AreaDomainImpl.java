package com.outmao.ebs.data.domain.impl;


import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.common.util.StringUtil;
import com.outmao.ebs.data.dao.AreaDao;
import com.outmao.ebs.data.domain.AreaDomain;
import com.outmao.ebs.data.domain.conver.AreaVOConver;
import com.outmao.ebs.data.dto.AreaDTO;
import com.outmao.ebs.data.dto.GetAreaListDTO;
import com.outmao.ebs.data.entity.Area;
import com.outmao.ebs.data.entity.QArea;
import com.outmao.ebs.data.vo.AreaVO;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
public class AreaDomainImpl extends BaseDomain implements AreaDomain {

    @Autowired
    private AreaDao areaDao;


    private AreaVOConver areaVOConver=new AreaVOConver();


    @CacheEvict(value = "cache_areas", allEntries = true)
    @Transactional
    @Override
    public Area saveArea(AreaDTO request) {
        Area area=request.getId()==null?null:areaDao.getOne(request.getId());
        if(area==null&&request.getCode()!=null){
            area=areaDao.findByCode(request.getCode());
        }
        if(area==null){
            area=areaDao.findByParentIdAndName(request.getParentId(),request.getName());
        }
        if(area==null){
            area=new Area();
            area.setCreateTime(new Date());
            area.setLeaf(true);
            if(request.getParentId()!=null){
                Area parent=areaDao.getOne(request.getParentId());
                area.setParent(parent);
                area.setLevel(parent.getLevel()+1);
                if(parent.isLeaf()){
                    parent.setLeaf(false);
                }
            }
        }
        request.setId(area.getId());
        BeanUtils.copyProperties(request,area);
        if(area.getLetter()==null){
            area.setLetter(StringUtil.toPinyin(area.getName()));
        }
        areaDao.save(area);
        return area;
    }

    @CacheEvict(value = "cache_areas", allEntries = true)
    @Transactional
    @Override
    public void deleteAreaById(Long id) {
        Area area=areaDao.getOne(id);
        if(!area.isLeaf()){
            throw new BusinessException("请先删除下级");
        }
        areaDao.delete(area);
    }

    @Override
    public List<Area> getAreaList() {
        return areaDao.findAll();
    }


    @Cacheable(value = "cache_areas", key = "method.name")
    @Override
    public List<AreaVO> getAreaVOList() {
        QArea e=QArea.area;

        Predicate p=e.type.lt(3);

        List<AreaVO> all=queryList(e,p,areaVOConver);

        return toLevel(all);
    }

    @Cacheable(value = "cache_areas", key = "method.name")
    @Override
    public List<AreaVO> getProvinceVOList() {
        QArea e=QArea.area;

        Predicate p=e.type.lt(4);

        List<AreaVO> all=queryList(e,p,areaVOConver);

        List<AreaVO> list=toLevel(all);

        for(AreaVO vo:list){
            if(vo.getName().equals("中国"))
                return vo.getChildren();
        }

        return null;
    }

    @Override
    public List<AreaVO> getCityVOList() {

        QArea e=QArea.area;

        Predicate p=e.type.eq(2).and(e.foreign.isFalse()).and(e.name.ne("市辖区"));

        List<AreaVO> list=queryList(e,p,areaVOConver);

        return list;
    }

    @Override
    public List<AreaVO> getAreaVOChildren(Long parentId) {
        QArea e=QArea.area;

        Predicate p=e.parent.id.eq(parentId);

        List<AreaVO> all=queryList(e,p,areaVOConver);

        return all;
    }

    private List<AreaVO> toLevel(List<AreaVO> all){

        Map<Long,AreaVO> map=all.stream().collect(Collectors.toMap(t->t.getId(),t->t));

        List<AreaVO> list=new ArrayList<>();

        for(AreaVO vo:all){
            if(vo.getParentId()!=null){
                AreaVO parent=map.get(vo.getParentId());
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
    public Page<AreaVO> getAreaVOPage(GetAreaListDTO request, Pageable pageable) {
        QArea e=QArea.area;

        Predicate p=null;

        if(request.getLevel()!=null){
           p=e.level.eq(request.getLevel());
        }

        if(request.getParentId()!=null){
            p=e.parent.id.eq(request.getParentId()).and(p);
        }

        if(StringUtil.isNotEmpty(request.getKeyword())){
            p=e.name.like("%"+request.getKeyword()+"%").and(p);
        }

        Page<AreaVO> all=queryPage(e,p,areaVOConver,pageable);

        return all;
    }






}



















