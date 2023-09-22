package com.outmao.ebs.data.service;


import com.outmao.ebs.data.dto.BrandDTO;
import com.outmao.ebs.data.dto.GetBrandListDTO;
import com.outmao.ebs.data.entity.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface BrandService {


    public Brand saveBrand(BrandDTO request);
    public void deleteBrandById(Long id);
    public Brand setBrandStatus(Long id, int status, String statusRemark);
    public Brand getBrandById(Long id);
    public Page<Brand> getBrandPage(GetBrandListDTO request, Pageable pageable);



}
