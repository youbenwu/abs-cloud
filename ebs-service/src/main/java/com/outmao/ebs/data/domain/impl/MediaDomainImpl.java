package com.outmao.ebs.data.domain.impl;




import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.data.dao.MediaDao;
import com.outmao.ebs.data.domain.MediaDomain;
import com.outmao.ebs.data.domain.conver.MediaVOConver;
import com.outmao.ebs.data.dto.GetMediaListDTO;
import com.outmao.ebs.data.entity.Media;
import com.outmao.ebs.data.dto.MediaDTO;
import com.outmao.ebs.data.entity.QMedia;
import com.outmao.ebs.data.vo.MediaVO;
import com.querydsl.core.types.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class MediaDomainImpl extends BaseDomain implements MediaDomain {

	@Autowired
	private MediaDao mediaDao;

	private MediaVOConver mediaVOConver=new MediaVOConver();



	@Transactional
	@Override
	public Media saveMedia(MediaDTO request) {
		Media media=request.getId()==null?null:mediaDao.getOne(request.getId());
		if(media==null){
			media=new Media();
			media.setCreateTime(new Date());
		}

		BeanUtils.copyProperties(request,media);
		media.setUpdateTime(new Date());

		media=mediaDao.save(media);
		return media;
	}

	@Override
	public Media getMediaById(Long id) {
		return mediaDao.findById(id).orElse(null);
	}

	@Override
	public Media getMediaByUuid(String uuid) {
		return mediaDao.findByUuid(uuid);
	}

	@Transactional
	@Override
	public void deleteMediaById(Long id) {
		mediaDao.deleteById(id);
	}

	@Override
	public Page<Media> getMediaPage(GetMediaListDTO request, Pageable pageable) {

		QMedia e=QMedia.media;
		Predicate p=null;
		if(request.getOrgId()!=null){
           p=e.orgId.eq(request.getOrgId()).and(p);
		}
		if(request.getUserId()!=null){
			p=e.userId.eq(request.getUserId()).and(p);
		}

		Page<Media> page=mediaDao.findAll(p,pageable);

		return page;
	}


	@Override
	public List<MediaVO> getMediaVOList(Collection<Long> idIn) {
		QMedia e=QMedia.media;

		List<MediaVO> list=queryList(e,e.id.in(idIn),mediaVOConver);

		return list;
	}


}
