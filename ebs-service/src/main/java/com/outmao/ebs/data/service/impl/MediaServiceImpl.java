package com.outmao.ebs.data.service.impl;




import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.data.domain.MediaDomain;
import com.outmao.ebs.data.dto.GetMediaListDTO;
import com.outmao.ebs.data.entity.Media;
import com.outmao.ebs.data.service.MediaService;
import com.outmao.ebs.data.dto.MediaDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MediaServiceImpl extends BaseService implements MediaService {

	@Autowired
	private MediaDomain mediaDomain;

	@Override
	public Media saveMedia(MediaDTO request) {
		return mediaDomain.saveMedia(request);
	}

	@Override
	public Media getMediaById(Long id) {
		return mediaDomain.getMediaById(id);
	}

	@Override
	public Media getMediaByUuid(String uuid) {
		return mediaDomain.getMediaByUuid(uuid);
	}

	@Override
	public void deleteMediaById(Long id) {
        mediaDomain.deleteMediaById(id);
	}

	@Override
	public Page<Media> getMediaPage(GetMediaListDTO request, Pageable pageable) {
		return mediaDomain.getMediaPage(request,pageable);
	}



}
