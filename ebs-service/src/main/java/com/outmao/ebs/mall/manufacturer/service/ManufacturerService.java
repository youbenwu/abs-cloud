package com.outmao.ebs.mall.manufacturer.service;

import com.outmao.ebs.mall.manufacturer.dto.CounselorDTO;
import com.outmao.ebs.mall.manufacturer.dto.GetCounselorDTO;
import com.outmao.ebs.mall.manufacturer.dto.ManufacturerDTO;
import com.outmao.ebs.mall.manufacturer.entity.Counselor;
import com.outmao.ebs.mall.manufacturer.entity.Manufacturer;
import com.outmao.ebs.mall.manufacturer.vo.CounselorVO;
import com.outmao.ebs.mall.manufacturer.vo.ManufacturerVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ManufacturerService {


    public Manufacturer saveManufacturer(ManufacturerDTO request);


    public void deleteManufacturerById(Long id);


    public ManufacturerVO getManufacturerVOById(Long id);


    public Page<ManufacturerVO> getManufacturerVOPage(String keyword, Pageable pageable);


    public Counselor saveCounselor(CounselorDTO request);

    public void deleteCounselorById(Long id);


    public CounselorVO getCounselorVOById(Long id);


    public Page<CounselorVO> getCounselorVOPage(GetCounselorDTO request, Pageable pageable);


}
