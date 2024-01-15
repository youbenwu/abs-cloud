package com.outmao.ebs.studio.service;

import com.outmao.ebs.studio.dto.GetStudioListDTO;
import com.outmao.ebs.studio.dto.StudioDTO;
import com.outmao.ebs.studio.entity.Studio;
import com.outmao.ebs.studio.vo.StudioVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudioService {


    public Studio saveStudio(StudioDTO request);

    public void deleteStudioById(Long id);

    public StudioVO getStudioVOById(Long id);

    public StudioVO getStudioVOByUserId(Long userId);

    public Page<StudioVO> getStudioVOPage(GetStudioListDTO request, Pageable pageable);



}
