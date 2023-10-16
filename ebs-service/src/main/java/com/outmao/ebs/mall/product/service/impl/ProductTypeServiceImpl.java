package com.outmao.ebs.mall.product.service.impl;

import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.mall.product.domain.ProductTypeDomain;
import com.outmao.ebs.mall.product.dto.*;
import com.outmao.ebs.mall.product.entity.ProductType;
import com.outmao.ebs.mall.product.entity.ProductTypeAttribute;
import com.outmao.ebs.mall.product.entity.ProductTypeAttributeGroup;
import com.outmao.ebs.mall.product.entity.ProductTypeProperty;
import com.outmao.ebs.mall.product.service.ProductTypeService;
import com.outmao.ebs.mall.product.vo.ProductTypeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductTypeServiceImpl extends BaseService implements ProductTypeService {


    @Autowired
    private ProductTypeDomain productTypeDomain;


    @Override
    public ProductType saveProductType(ProductTypeDTO request) {
        return productTypeDomain.saveProductType(request);
    }

    @Override
    public void deleteProductTypeById(Long id) {
        productTypeDomain.deleteProductTypeById(id);
    }

    @Override
    public ProductTypeVO getProductTypeVOById(Long id) {
        return productTypeDomain.getProductTypeVOById(id);
    }

    @Override
    public List<ProductTypeVO> getProductTypeVOList() {
        return productTypeDomain.getProductTypeVOList();
    }

    @Override
    public Page<ProductTypeVO> getProductTypeVOPage(GetProductTypeListDTO request, Pageable pageable) {
        return productTypeDomain.getProductTypeVOPage(request,pageable);
    }

    @Override
    public ProductTypeAttribute saveProductTypeAttribute(ProductTypeAttributeDTO request) {
        return productTypeDomain.saveProductTypeAttribute(request);
    }

    @Override
    public void deleteProductTypeAttributeById(Long id) {
        productTypeDomain.deleteProductTypeAttributeById(id);
    }

    @Override
    public ProductTypeAttributeGroup saveProductTypeAttributeGroup(ProductTypeAttributeGroupDTO request) {
        return productTypeDomain.saveProductTypeAttributeGroup(request);
    }

    @Override
    public void deleteProductTypeAttributeGroupById(Long id) {
        productTypeDomain.deleteProductTypeAttributeGroupById(id);
    }

    @Override
    public ProductTypeProperty saveProductTypeProperty(ProductTypePropertyDTO request) {
        return productTypeDomain.saveProductTypeProperty(request);
    }

    @Override
    public void deleteProductTypePropertyById(Long id) {
        productTypeDomain.deleteProductTypePropertyById(id);
    }
}
