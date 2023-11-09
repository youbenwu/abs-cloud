package com.outmao.ebs.mall.product.domain;

import com.outmao.ebs.mall.product.dto.GetProductCategoryListDTO;
import com.outmao.ebs.mall.product.dto.ProductCategoryDTO;
import com.outmao.ebs.mall.product.entity.ProductCategory;
import com.outmao.ebs.mall.product.vo.ProductCategoryVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductCategoryDomain {

    public ProductCategory saveProductCategory(ProductCategoryDTO request);

    public void deleteProductCategoryById(Long id);

    public List<ProductCategoryVO> getProductCategoryVOList();

    public Page<ProductCategoryVO> getProductCategoryVOPage(GetProductCategoryListDTO request, Pageable pageable);


    public void sort(List<Long> ids);


}
