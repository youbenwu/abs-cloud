package com.outmao.ebs.mall.takeLook.service.impl;

import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.mall.takeLook.service.TakeLookService;
import com.outmao.ebs.mall.takeLook.vo.TakeLookVO;
import com.outmao.ebs.mall.takeLook.dto.SetTakeLookStatusDTO;
import com.outmao.ebs.mall.takeLook.domain.TakeLookDomain;
import com.outmao.ebs.mall.takeLook.dto.GetTakeLookListDTO;
import com.outmao.ebs.mall.takeLook.dto.TakeLookDTO;
import com.outmao.ebs.mall.takeLook.entity.TakeLook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



@Service
public class TakeLookServiceImpl extends BaseService implements TakeLookService {


    @Autowired
    private TakeLookDomain takeLookDomain;


    @Override
    public TakeLook saveTakeLook(TakeLookDTO request) {
        return takeLookDomain.saveTakeLook(request);
    }

    @Override
    public void deleteTakeLookById(Long id) {
        takeLookDomain.deleteTakeLookById(id);
    }

    @Override
    public TakeLook setTakeLookStatus(SetTakeLookStatusDTO request) {
        return takeLookDomain.setTakeLookStatus(request);
    }

    @Override
    public TakeLookVO getTakeLookVOById(Long id) {
        return takeLookDomain.getTakeLookVOById(id);
    }

    @Override
    public Page<TakeLookVO> getTakeLookVOPage(GetTakeLookListDTO request, Pageable pageable) {
        return takeLookDomain.getTakeLookVOPage(request,pageable);
    }


}



