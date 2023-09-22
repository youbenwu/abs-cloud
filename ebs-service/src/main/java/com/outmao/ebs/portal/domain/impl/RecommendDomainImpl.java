package com.outmao.ebs.portal.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.vo.DataItemGetter;
import com.outmao.ebs.common.vo.IItem;
import com.outmao.ebs.portal.dao.RecommendDao;
import com.outmao.ebs.portal.domain.RecommendDomain;
import com.outmao.ebs.portal.domain.conver.RecommendVOConver;
import com.outmao.ebs.portal.dto.GetRecommendListDTO;
import com.outmao.ebs.portal.dto.RecommendDTO;
import com.outmao.ebs.portal.entity.QRecommend;
import com.outmao.ebs.portal.entity.Recommend;
import com.outmao.ebs.portal.vo.RecommendVO;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Component
public class RecommendDomainImpl extends BaseDomain implements RecommendDomain {


    @Autowired
    private RecommendDao recommendDao;

    private RecommendVOConver recommendVOConver=new RecommendVOConver();

    @Transactional
    @Override
    public Recommend saveRecommend(RecommendDTO request) {
        Recommend recommend=request.getId()==null?null:recommendDao.getOne(request.getId());

        if(recommend==null&&request.getItem()!=null){
            recommend=recommendDao.findByChannelIdAndTypeAndItemId(request.getChannelId(),request.getType(),request.getItem().getId());
        }

        if(recommend==null){
            recommend=new Recommend();
            recommend.setCreateTime(new Date());
        }

        request.setId(recommend.getId());
        BeanUtils.copyProperties(request,recommend);
        recommend.setUpdateTime(new Date());

        recommendDao.save(recommend);

        return recommend;
    }

    @Transactional
    @Override
    public void deleteRecommendById(Long id) {
        recommendDao.deleteById(id);
    }


    @Override
    public Page<Recommend> getRecommendPage(GetRecommendListDTO request, Pageable pageable) {
        QRecommend e=QRecommend.recommend;
        Predicate p=null;
        if(request.getChannelId()!=null){
            p=e.channelId.eq(request.getChannelId());
        }
        if(request.getType()!=null){
            p=e.type.eq(request.getType()).and(p);
        }
        if(request.getItemType()!=null){
            p=e.item.type.eq(request.getItemType()).and(p);
        }
        return recommendDao.findAll(p,pageable);
    }

    @Override
    public <T extends IItem> Page<RecommendVO<T>> getRecommendVOPage(GetRecommendListDTO request, DataItemGetter<T> dataItemGetter, Pageable pageable) {


        QRecommend e=QRecommend.recommend;
        Predicate p=null;
        if(request.getChannelId()!=null){
            p=e.channelId.eq(request.getChannelId());
        }
        if(request.getType()!=null){
            p=e.type.eq(request.getType()).and(p);
        }
        if(request.getItemType()!=null){
            p=e.item.type.eq(request.getItemType()).and(p);
        }

        Page<RecommendVO<T>> page=queryPage(e,p,recommendVOConver,pageable);

        if(page.getContent().size()>0){
            List<T> list= dataItemGetter.getDataItemListByIdIn(page.getContent().stream().map(t->t.getItem().getId()).collect(Collectors.toList()));
            Map<Long,T> map=list.stream().collect(Collectors.toMap(t->t.getId(),t->t));
            page.getContent().forEach(t->{
                t.setData(map.get(t.getItem().getId()));
            });
        }

        return page;

    }


}
