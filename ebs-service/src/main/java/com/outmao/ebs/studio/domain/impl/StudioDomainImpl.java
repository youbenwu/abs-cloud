package com.outmao.ebs.studio.domain.impl;

import cn.jiguang.common.utils.StringUtils;
import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.mall.merchant.common.annotation.BindingMerchant;
import com.outmao.ebs.studio.dao.StudioDao;
import com.outmao.ebs.studio.domain.StudioDomain;
import com.outmao.ebs.studio.domain.conver.StudioVOConver;
import com.outmao.ebs.studio.dto.GetStudioListDTO;
import com.outmao.ebs.studio.dto.StudioDTO;
import com.outmao.ebs.studio.entity.QStudio;
import com.outmao.ebs.studio.entity.Studio;
import com.outmao.ebs.studio.vo.StudioVO;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;


@Component
public class StudioDomainImpl extends BaseDomain implements StudioDomain {


    @Autowired
    private StudioDao studioDao;


    private StudioVOConver studioVOConver=new StudioVOConver();


    @Transactional
    @BindingMerchant
    @Override
    public Studio saveStudio(StudioDTO request) {
        Studio studio=request.getId()==null?null:studioDao.getOne(request.getId());

        if(studio==null){
            studio=new Studio();
            studio.setCreateTime(new Date());
        }

        BeanUtils.copyProperties(request,studio);
        studio.setUpdateTime(new Date());

        studio.setKeyword(getKeyword(studio));

        studioDao.save(studio);

        return studio;
    }

    private String getKeyword(Studio data){
        StringBuffer s=new StringBuffer();
        s.append(data.getName());
        if(!StringUtils.isEmpty(data.getIntro())){
            s.append(" "+data.getIntro());
        }

        return s.toString();
    }



    @Transactional
    @Override
    public void deleteStudioById(Long id) {
        studioDao.deleteById(id);
    }

    @Override
    public Studio getStudioById(Long id) {
        return studioDao.findById(id).orElse(null);
    }

    @Override
    public StudioVO getStudioVOById(Long id) {
        QStudio e=QStudio.studio;
        StudioVO vo=queryOne(e,e.id.eq(id),studioVOConver);
        return vo;
    }

    @Override
    public StudioVO getStudioVOByUserId(Long userId) {
        QStudio e=QStudio.studio;
        StudioVO vo=queryOne(e,e.userId.eq(userId),studioVOConver);
        return vo;
    }

    @Override
    public Page<StudioVO> getStudioVOPage(GetStudioListDTO request, Pageable pageable) {
        QStudio e=QStudio.studio;

        Predicate p=null;

        if(!StringUtils.isEmpty(request.getKeyword())){
            p=e.keyword.like("%"+request.getKeyword()+"%").and(p);
        }

        Page<StudioVO> page=queryPage(e,p,studioVOConver,pageable);

        return page;
    }


}
