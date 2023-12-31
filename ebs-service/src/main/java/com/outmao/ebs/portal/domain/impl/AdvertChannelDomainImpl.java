package com.outmao.ebs.portal.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.portal.dao.AdvertChannelDao;
import com.outmao.ebs.portal.domain.AdvertChannelDomain;
import com.outmao.ebs.portal.domain.conver.AdvertChannelVOConver;
import com.outmao.ebs.portal.dto.AdvertChannelDTO;
import com.outmao.ebs.portal.dto.GetAdvertChannelListDTO;
import com.outmao.ebs.portal.entity.AdvertChannel;
import com.outmao.ebs.portal.entity.QAdvertChannel;
import com.outmao.ebs.portal.vo.AdvertChannelVO;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;


@Component
public class AdvertChannelDomainImpl extends BaseDomain implements AdvertChannelDomain {



    @Autowired
    private AdvertChannelDao advertChannelDao;

    private AdvertChannelVOConver advertChannelVOConver=new AdvertChannelVOConver();


    @Transactional
    @Override
    public AdvertChannel saveAdvertChannel(AdvertChannelDTO request) {
        AdvertChannel c=request.getId()==null?null:advertChannelDao.getOne(request.getId());
        if(c==null){
            c=new AdvertChannel();
            c.setCreateTime(new Date());
            c.setOrgId(request.getOrgId());
        }

        BeanUtils.copyProperties(request,c,"orgId");

        c.setUpdateTime(new Date());
        advertChannelDao.save(c);

        return c;
    }

    @Transactional
    @Override
    public void deleteAdvertChannelById(Long id) {
        AdvertChannel c=advertChannelDao.getOne(id);
        advertChannelDao.delete(c);
    }

    @Override
    public AdvertChannel getAdvertChannelById(Long id) {
        return advertChannelDao.findById(id).orElse(null);
    }

    @Override
    public AdvertChannel getAdvertChannelByCode(String code) {
        return advertChannelDao.findByCode(code);
    }

    @Override
    public List<AdvertChannel> getAdvertChannelList(GetAdvertChannelListDTO request) {
        QAdvertChannel e=QAdvertChannel.advertChannel;

        Predicate p=getPredicate(request);

        if(p==null)
            return advertChannelDao.findAll();

        return QF.select(e).from(e).where(p).fetch();
    }

    @Override
    public Page<AdvertChannel> getAdvertChannelPage(GetAdvertChannelListDTO request, Pageable pageable) {
        QAdvertChannel e=QAdvertChannel.advertChannel;

        Predicate p=getPredicate(request);

        if(p==null)
            return advertChannelDao.findAll(pageable);

        return advertChannelDao.findAll(p,pageable);
    }

    private Predicate getPredicate(GetAdvertChannelListDTO request){
        QAdvertChannel e=QAdvertChannel.advertChannel;
        Predicate p=null;

        if(request.getOrgId()!=null){
            p=e.orgId.eq(request.getOrgId());
        }

        if(request.getType()!=null){
            p=e.type.eq(request.getType()).and(p);
        }
        return p;
    }

    @Override
    public AdvertChannelVO getAdvertChannelVOById(Long id) {
        QAdvertChannel e=QAdvertChannel.advertChannel;
        AdvertChannelVO vo=queryOne(e,e.id.eq(id),advertChannelVOConver);
        return vo;
    }

    @Override
    public AdvertChannelVO getAdvertChannelVOByCode(String code) {
        QAdvertChannel e=QAdvertChannel.advertChannel;
        AdvertChannelVO vo=queryOne(e,e.code.eq(code),advertChannelVOConver);
        return vo;
    }

    @Override
    public List<AdvertChannelVO> getAdvertChannelVOList(GetAdvertChannelListDTO request) {
        QAdvertChannel e=QAdvertChannel.advertChannel;
        Predicate p=getPredicate(request);
        List<AdvertChannelVO> list=queryList(e,p,advertChannelVOConver);
        return list;
    }

    @Override
    public Page<AdvertChannelVO> getAdvertChannelVOPage(GetAdvertChannelListDTO request, Pageable pageable) {
        QAdvertChannel e=QAdvertChannel.advertChannel;
        Predicate p=getPredicate(request);
        Page<AdvertChannelVO> page=queryPage(e,p,advertChannelVOConver,pageable);
        return page;
    }


}
