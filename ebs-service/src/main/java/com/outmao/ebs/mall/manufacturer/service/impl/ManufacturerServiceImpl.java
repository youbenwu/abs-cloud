package com.outmao.ebs.mall.manufacturer.service.impl;

import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.mall.manufacturer.domain.CounselorDomain;
import com.outmao.ebs.mall.manufacturer.domain.ManufacturerDomain;
import com.outmao.ebs.mall.manufacturer.dto.CounselorDTO;
import com.outmao.ebs.mall.manufacturer.dto.GetCounselorDTO;
import com.outmao.ebs.mall.manufacturer.dto.ManufacturerDTO;
import com.outmao.ebs.mall.manufacturer.entity.Counselor;
import com.outmao.ebs.mall.manufacturer.entity.Manufacturer;
import com.outmao.ebs.mall.manufacturer.service.ManufacturerService;
import com.outmao.ebs.mall.manufacturer.vo.CounselorVO;
import com.outmao.ebs.mall.manufacturer.vo.ManufacturerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ManufacturerServiceImpl extends BaseService implements ManufacturerService {

    @Autowired
    private CounselorDomain counselorDomain;

    @Autowired
    private ManufacturerDomain manufacturerDomain;


    @Override
    public Manufacturer saveManufacturer(ManufacturerDTO request) {

        return manufacturerDomain.saveManufacturer(request);
    }

    @Override
    public void deleteManufacturerById(Long id) {
       manufacturerDomain.deleteManufacturerById(id);
    }

    @Override
    public ManufacturerVO getManufacturerVOById(Long id) {
        return manufacturerDomain.getManufacturerVOById(id);
    }

    @Override
    public Page<ManufacturerVO> getManufacturerVOPage(String keyword, Pageable pageable) {
        return manufacturerDomain.getManufacturerVOPage(keyword,pageable);
    }

    @Override
    public Counselor saveCounselor(CounselorDTO request) {
        return counselorDomain.saveCounselor(request);
    }

    @Override
    public void deleteCounselorById(Long id) {
        counselorDomain.deleteCounselorById(id);
    }

    @Override
    public CounselorVO getCounselorVOById(Long id) {
        return counselorDomain.getCounselorVOById(id);
    }

    @Override
    public Page<CounselorVO> getCounselorVOPage(GetCounselorDTO request, Pageable pageable) {
        return counselorDomain.getCounselorVOPage(request,pageable);
    }


}
