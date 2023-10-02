package com.outmao.ebs.mall.product.domain;


import com.outmao.ebs.mall.product.dto.ProductTypeAttributeDTO;
import com.outmao.ebs.mall.product.dto.ProductTypeAttributeGroupDTO;
import com.outmao.ebs.mall.product.dto.ProductTypeDTO;
import com.outmao.ebs.mall.product.dto.ProductTypePropertyDTO;
import com.outmao.ebs.mall.product.entity.ProductType;
import com.outmao.ebs.mall.product.entity.ProductTypeAttribute;
import com.outmao.ebs.mall.product.entity.ProductTypeAttributeGroup;
import com.outmao.ebs.mall.product.entity.ProductTypeProperty;
import com.outmao.ebs.mall.product.vo.ProductTypeVO;
import java.util.List;

public interface ProductTypeDomain {

    public ProductType saveProductType(ProductTypeDTO request);

    public void deleteProductTypeById(Long id);

    public ProductTypeVO getProductTypeVOById(Long id);

    public List<ProductTypeVO> getProductTypeVOList();


    public ProductTypeAttribute saveProductTypeAttribute(ProductTypeAttributeDTO request);
    public void deleteProductTypeAttributeById(Long id);

    public ProductTypeAttributeGroup saveProductTypeAttributeGroup(ProductTypeAttributeGroupDTO request);
    public void deleteProductTypeAttributeGroupById(Long id);

    public ProductTypeProperty saveProductTypeProperty(ProductTypePropertyDTO request);
    public void deleteProductTypePropertyById(Long id);


}
