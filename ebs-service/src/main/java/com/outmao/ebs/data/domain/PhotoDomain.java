package com.outmao.ebs.data.domain;

import com.outmao.ebs.data.dto.GetPhotoListDTO;
import com.outmao.ebs.data.dto.PhotoDTO;
import com.outmao.ebs.data.entity.Photo;
import com.outmao.ebs.data.vo.PhotoVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PhotoDomain {

    public Photo savePhoto(PhotoDTO request);

    public void deletePhotoById(Long id);

    public void deleteAllByTargetType(String targetType);

    public void deleteAllByTargetTypeAndTargetId(String targetType,Long targetId);

    public PhotoVO getPhotoVOById(Long id);

    public Page<PhotoVO> getPhotoVOPage(GetPhotoListDTO request, Pageable pageable);


}
