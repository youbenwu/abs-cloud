package com.outmao.ebs.data.service.impl;



import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.data.domain.BrandDomain;
import com.outmao.ebs.data.dto.BrandDTO;
import com.outmao.ebs.data.dto.GetBrandListDTO;
import com.outmao.ebs.data.entity.Brand;
import com.outmao.ebs.data.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BrandServiceImpl extends BaseService implements BrandService {



    @Autowired
    private BrandDomain brandDomain;



    @Override
    public Brand saveBrand(BrandDTO request) {
        return brandDomain.saveBrand(request);
    }


    @Override
    public void deleteBrandById(Long id) {
        brandDomain.deleteBrandById(id);
    }

    @Override
    public Brand setBrandStatus(Long id, int status, String statusRemark) {
        return brandDomain.setBrandStatus(id,status,statusRemark);
    }

    @Override
    public Brand getBrandById(Long id) {
        return brandDomain.getBrandById(id);
    }

    @Override
    public Page<Brand> getBrandPage(GetBrandListDTO request, Pageable pageable) {
        return brandDomain.getBrandPage(request,pageable);
    }

}
