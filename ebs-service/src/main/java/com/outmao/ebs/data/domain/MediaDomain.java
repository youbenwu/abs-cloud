package com.outmao.ebs.data.domain;


import com.outmao.ebs.data.dto.GetMediaListDTO;
import com.outmao.ebs.data.entity.Media;
import com.outmao.ebs.data.dto.MediaDTO;
import com.outmao.ebs.data.vo.MediaVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

public interface MediaDomain {

    public Media saveMedia(MediaDTO request);

    public Media getMediaById(Long id);

    public Media getMediaByUuid(String uuid);

    public void deleteMediaById(Long id);

    public Page<Media> getMediaPage(GetMediaListDTO request, Pageable pageable);

    public List<MediaVO> getMediaVOList(Collection<Long> idIn);


}
