package com.outmao.ebs.data.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.data.dao.PhotoDao;
import com.outmao.ebs.data.domain.PhotoDomain;
import com.outmao.ebs.data.domain.conver.PhotoVOConvert;
import com.outmao.ebs.data.dto.GetPhotoListDTO;
import com.outmao.ebs.data.dto.PhotoDTO;
import com.outmao.ebs.data.entity.Photo;
import com.outmao.ebs.data.entity.QPhoto;
import com.outmao.ebs.data.vo.PhotoVO;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Component
public class PhotoDomainImpl extends BaseDomain implements PhotoDomain {

    @Autowired
    private PhotoDao photoDao;

    private PhotoVOConvert photoVOConvert=new PhotoVOConvert();


    @Transactional
    @Override
    public Photo savePhoto(PhotoDTO request) {
        Photo photo=request.getId()==null?null:photoDao.getOne(request.getId());

        if(photo==null){
            photo=new Photo();
            photo.setCreateTime(new Date());
        }

        BeanUtils.copyProperties(request,photo);


        photoDao.save(photo);


        return photo;
    }


    @Transactional
    @Override
    public void deletePhotoById(Long id) {
        photoDao.deleteById(id);
    }

    @Override
    public void deleteAllByTargetType(String targetType) {
        photoDao.deleteAllByTargetType(targetType);
    }

    @Override
    public void deleteAllByTargetTypeAndTargetId(String targetType, Long targetId) {
        photoDao.deleteAllByTargetTypeAndTargetId(targetType,targetId);
    }

    @Override
    public PhotoVO getPhotoVOById(Long id) {
        QPhoto e=QPhoto.photo;
        return queryOne(e,e.id.eq(id),photoVOConvert);
    }

    @Override
    public Page<PhotoVO> getPhotoVOPage(GetPhotoListDTO request, Pageable pageable) {
        QPhoto e=QPhoto.photo;

        Predicate p=null;

        if(request.getUserId()!=null){
            p=e.userId.eq(request.getUserId()).and(p);
        }

        if(request.getTargetId()!=null){
            p=e.target.id.eq(request.getTargetId()).and(p);
        }

        if(request.getTargetType()!=null){
            p=e.target.type.eq(request.getTargetType()).and(p);
        }

        Page<PhotoVO> page=queryPage(e,p,photoVOConvert,pageable);

        return page;
    }
}
