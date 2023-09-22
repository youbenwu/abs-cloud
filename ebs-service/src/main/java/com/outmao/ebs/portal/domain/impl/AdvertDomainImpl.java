package com.outmao.ebs.portal.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.util.StringUtil;
import com.outmao.ebs.portal.dao.AdvertDao;
import com.outmao.ebs.portal.domain.AdvertDomain;
import com.outmao.ebs.portal.dto.AdvertDTO;
import com.outmao.ebs.portal.dto.GetAdvertListDTO;
import com.outmao.ebs.portal.dto.SetAdvertStatusDTO;
import com.outmao.ebs.portal.entity.Advert;
import com.outmao.ebs.portal.entity.QAdvert;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;


@Component
public class AdvertDomainImpl extends BaseDomain implements AdvertDomain {


    @Autowired
    private AdvertDao advertDao;



    @Transactional
    @Override
    public Advert saveAdvert(AdvertDTO request) {
        Advert advert=request.getId()==null?null:advertDao.getOne(request.getId());


        if(advert==null){
            advert=new Advert();
            advert.setCreateTime(new Date());
        }

        BeanUtils.copyProperties(request,advert);
        advert.setUpdateTime(new Date());

        advertDao.save(advert);

        return advert;
    }

    @Transactional
    @Override
    public Advert setAdvertStatus(SetAdvertStatusDTO request) {
        Advert advert=advertDao.getOne(request.getId());
        BeanUtils.copyProperties(request,advert);
        advert.setUpdateTime(new Date());
        advertDao.save(advert);
        return advert;
    }

    @Transactional
    @Override
    public void deleteAdvertById(Long id) {
        advertDao.deleteById(id);
    }

    @Override
    public Page<Advert> getAdvertPage(GetAdvertListDTO request, Pageable pageable) {
        QAdvert e=QAdvert.advert;

        Predicate p=null;

        if(StringUtil.isNotEmpty(request.getKeyword())){
            p=e.title.like("%"+request.getKeyword()+"%");
        }

        if(request.getStatus()!=null){
            p=e.status.eq(request.getStatus()).and(p);
        }else{
            Date now =new Date();
            //前端只返回上架的，并且在显示时间之内的
            p=e.status.eq(1).and(e.startTime.before(now).and(e.endTime.after(now))).and(p);
        }

        Page<Advert> page=p==null?advertDao.findAll(pageable):advertDao.findAll(p,pageable);

        return page;
    }


}
