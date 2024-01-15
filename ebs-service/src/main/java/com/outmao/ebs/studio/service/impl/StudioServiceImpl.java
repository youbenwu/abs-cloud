package com.outmao.ebs.studio.service.impl;

import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.studio.domain.MovieDomain;
import com.outmao.ebs.studio.domain.StudioDomain;
import com.outmao.ebs.studio.dto.GetStudioListDTO;
import com.outmao.ebs.studio.dto.StudioDTO;
import com.outmao.ebs.studio.entity.Studio;
import com.outmao.ebs.studio.service.StudioService;
import com.outmao.ebs.studio.vo.StudioVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class StudioServiceImpl extends BaseService implements StudioService {

    @Autowired
    private StudioDomain studioDomain;

    @Autowired
    private MovieDomain movieDomain;


    @Override
    public Studio saveStudio(StudioDTO request) {
        return studioDomain.saveStudio(request);
    }


    @Transactional
    @Override
    public void deleteStudioById(Long id) {
        Studio studio=studioDomain.getStudioById(id);
        movieDomain.deleteAllByOrgId(studio.getOrgId());
        studioDomain.deleteStudioById(id);
    }

    @Override
    public StudioVO getStudioVOById(Long id) {
        return studioDomain.getStudioVOById(id);
    }

    @Override
    public StudioVO getStudioVOByUserId(Long userId) {
        return studioDomain.getStudioVOByUserId(userId);
    }

    @Override
    public Page<StudioVO> getStudioVOPage(GetStudioListDTO request, Pageable pageable) {
        return studioDomain.getStudioVOPage(request,pageable);
    }



}
