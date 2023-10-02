package com.outmao.ebs.mall.manufacturer.domain;

import com.outmao.ebs.mall.manufacturer.dto.ManufacturerDTO;
import com.outmao.ebs.mall.manufacturer.entity.Manufacturer;
import com.outmao.ebs.mall.manufacturer.vo.ManufacturerVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ManufacturerDomain  {


    public Manufacturer saveManufacturer(ManufacturerDTO request);


    public void deleteManufacturerById(Long id);


    public ManufacturerVO getManufacturerVOById(Long id);


    public Page<ManufacturerVO> getManufacturerVOPage(String keyword, Pageable pageable);


}
