package com.outmao.ebs.mall.takeLook.service;

import com.outmao.ebs.mall.takeLook.vo.TakeLookVO;
import com.outmao.ebs.mall.takeLook.dto.SetTakeLookStatusDTO;
import com.outmao.ebs.mall.takeLook.dto.GetTakeLookListDTO;
import com.outmao.ebs.mall.takeLook.dto.TakeLookDTO;
import com.outmao.ebs.mall.takeLook.entity.TakeLook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TakeLookService {


    public TakeLook saveTakeLook(TakeLookDTO request);

    public void deleteTakeLookById(Long id);

    public TakeLook setTakeLookStatus(SetTakeLookStatusDTO request);

    public TakeLookVO getTakeLookVOById(Long id);

    public Page<TakeLookVO> getTakeLookVOPage(GetTakeLookListDTO request, Pageable pageable);


}
