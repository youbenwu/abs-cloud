package com.outmao.ebs.mall.product.service.impl;

import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.mall.product.domain.ProductCategoryDomain;
import com.outmao.ebs.mall.product.dto.GetProductCategoryListDTO;
import com.outmao.ebs.mall.product.dto.ProductCategoryDTO;
import com.outmao.ebs.mall.product.entity.ProductCategory;
import com.outmao.ebs.mall.product.service.ProductCategoryService;
import com.outmao.ebs.mall.product.vo.ProductCategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductCategoryServiceImpl extends BaseService implements ProductCategoryService {


    @Autowired
    private ProductCategoryDomain productCategoryDomain;

    @Override
    public ProductCategory saveProductCategory(ProductCategoryDTO request) {
        return productCategoryDomain.saveProductCategory(request);
    }

    @Override
    public void deleteProductCategoryById(Long id) {
        productCategoryDomain.deleteProductCategoryById(id);
    }

    @Override
    public List<ProductCategoryVO> getProductCategoryVOList() {
        return productCategoryDomain.getProductCategoryVOList();
    }


    @Override
    public Page<ProductCategoryVO> getProductCategoryVOPage(GetProductCategoryListDTO request, Pageable pageable) {
        return productCategoryDomain.getProductCategoryVOPage(request,pageable);
    }


}
