package com.outmao.ebs.data.domain.impl;


import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.data.dao.SubwayDao;
import com.outmao.ebs.data.domain.SubwayDomain;
import com.outmao.ebs.data.domain.conver.SubwayVOConver;
import com.outmao.ebs.data.dto.SubwayDTO;
import com.outmao.ebs.data.entity.QSubway;
import com.outmao.ebs.data.entity.Subway;
import com.outmao.ebs.data.vo.SubwayVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Component
public class SubwayDomainImpl extends BaseDomain implements SubwayDomain {


    @Autowired
    private SubwayDao subwayDao;


    private SubwayVOConver subwayVOConver=new SubwayVOConver();


    @CacheEvict(value = "cache_subways", allEntries = true)
    @Transactional
    @Override
    public Subway saveSubway(SubwayDTO request) {
        Subway subway=request.getId()==null?null:subwayDao.getOne(request.getId());

        if(subway==null){
            subway=subwayDao.findByParentIdAndName(request.getParentId(),request.getName());
        }

        if(subway==null){
            subway=new Subway();
            subway.setCreateTime(new Date());
            subway.setLeaf(true);
            if(request.getParentId()!=null){
                Subway parent=subwayDao.getOne(request.getParentId());
                subway.setLevel(parent.getLevel()+1);
                subway.setParent(parent);
                if(parent.isLeaf())
                    parent.setLeaf(false);
            }

        }

        request.setId(subway.getId());
        BeanUtils.copyProperties(request,subway);

        subwayDao.save(subway);

        return subway;
    }


    @Cacheable(value = "cache_subways", key = "method.name")
    @Override
    public List<SubwayVO> getSubwayVOList() {

        QSubway e=QSubway.subway;

        List<SubwayVO> list=queryList(e,e.id.gt(0),subwayVOConver);

        list=toLevel(list);

        return list;
    }

    private List<SubwayVO> toLevel(List<SubwayVO> all){

        Map<Long,SubwayVO> map=all.stream().collect(Collectors.toMap(t->t.getId(), t->t));

        List<SubwayVO> list=new ArrayList<>();

        for(SubwayVO vo:all){
            if(vo.getParentId()!=null){
                SubwayVO parent=map.get(vo.getParentId());
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



}
