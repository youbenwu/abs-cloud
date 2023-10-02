package com.outmao.ebs.mall.product.domain;

import com.outmao.ebs.mall.product.dto.ProductCategoryDTO;
import com.outmao.ebs.mall.product.entity.ProductCategory;
import com.outmao.ebs.mall.product.vo.ProductCategoryVO;
import java.util.List;

public interface ProductCategoryDomain {

    public ProductCategory saveProductCategory(ProductCategoryDTO request);

    public void deleteProductCategoryById(Long id);

    public List<ProductCategoryVO> getProductCategoryVOList();

    //不分级
    public List<ProductCategoryVO> getAllProductCategoryVO();


}
