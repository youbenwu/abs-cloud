package com.outmao.ebs.mall.product.service;

import com.outmao.ebs.mall.product.dto.*;
import com.outmao.ebs.mall.product.entity.ProductType;
import com.outmao.ebs.mall.product.entity.ProductTypeAttribute;
import com.outmao.ebs.mall.product.entity.ProductTypeAttributeGroup;
import com.outmao.ebs.mall.product.entity.ProductTypeProperty;
import com.outmao.ebs.mall.product.vo.ProductTypeVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductTypeService {

    public ProductType saveProductType(ProductTypeDTO request);

    public void deleteProductTypeById(Long id);

    public ProductTypeVO getProductTypeVOById(Long id);

    public List<ProductTypeVO> getProductTypeVOList();

    public Page<ProductTypeVO> getProductTypeVOPage(GetProductTypeListDTO request, Pageable pageable);


    public ProductTypeAttribute saveProductTypeAttribute(ProductTypeAttributeDTO request);
    public void deleteProductTypeAttributeById(Long id);

    public ProductTypeAttributeGroup saveProductTypeAttributeGroup(ProductTypeAttributeGroupDTO request);
    public void deleteProductTypeAttributeGroupById(Long id);

    public ProductTypeProperty saveProductTypeProperty(ProductTypePropertyDTO request);
    public void deleteProductTypePropertyById(Long id);

}
