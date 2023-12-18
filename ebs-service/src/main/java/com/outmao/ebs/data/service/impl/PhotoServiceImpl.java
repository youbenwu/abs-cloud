package com.outmao.ebs.data.service.impl;

import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.data.domain.PhotoDomain;
import com.outmao.ebs.data.dto.GetPhotoListDTO;
import com.outmao.ebs.data.dto.PhotoDTO;
import com.outmao.ebs.data.entity.Photo;
import com.outmao.ebs.data.service.PhotoService;
import com.outmao.ebs.data.vo.PhotoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PhotoServiceImpl extends BaseService implements PhotoService {

    @Autowired
    private PhotoDomain photoDomain;

    @Override
    public Photo savePhoto(PhotoDTO request) {
        return photoDomain.savePhoto(request);
    }

    @Override
    public void deletePhotoById(Long id) {
        photoDomain.deletePhotoById(id);
    }

    @Override
    public void deleteAllByTargetType(String targetType) {
        photoDomain.deleteAllByTargetType(targetType);
    }

    @Override
    public void deleteAllByTargetTypeAndTargetId(String targetType, Long targetId) {
        photoDomain.deleteAllByTargetTypeAndTargetId(targetType,targetId);
    }

    @Override
    public PhotoVO getPhotoVOById(Long id) {
        return photoDomain.getPhotoVOById(id);
    }

    @Override
    public Page<PhotoVO> getPhotoVOPage(GetPhotoListDTO request, Pageable pageable) {
        return photoDomain.getPhotoVOPage(request,pageable);
    }


}
