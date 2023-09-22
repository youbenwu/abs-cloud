package com.outmao.ebs.data.service;

import com.outmao.ebs.data.dto.GetMediaListDTO;
import com.outmao.ebs.data.entity.Media;
import com.outmao.ebs.data.dto.MediaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MediaService {

    public Media saveMedia(MediaDTO request);

    public void deleteMediaById(Long id);

    public Media getMediaById(Long id);

    public Media getMediaByUuid(String uuid);

    public Page<Media> getMediaPage(GetMediaListDTO request, Pageable pageable);


}
