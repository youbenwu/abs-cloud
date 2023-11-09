package com.outmao.ebs.mall.product.service;

import com.outmao.ebs.bbs.vo.SubjectBrowseVO;
import com.outmao.ebs.bbs.vo.SubjectCollectionVO;
import com.outmao.ebs.mall.product.entity.ProductBsType;
import com.outmao.ebs.portal.dto.GetRecommendListDTO;
import com.outmao.ebs.portal.vo.RecommendVO;
import com.outmao.ebs.mall.product.dto.*;
import com.outmao.ebs.mall.product.entity.Product;
import com.outmao.ebs.mall.product.vo.ProductVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {


    public ProductBsType saveProductBsType(ProductBsType request);

    public void deleteProductBsType(Integer type);

    public List<ProductBsType> getProductBsTypeList();


    public Product saveProduct(ProductDTO request);

    public void deleteProductById(Long id);

    public Product getProductById(Long id);

    public Product setProductOnSell(SetProductOnSellDTO request);

    public Product setProductStatus(SetProductStatusDTO request);

    public Product setProductStock(SetProductStockDTO request);

    public void skuStockOut(List<ProductSkuStockOutDTO> request);

    public ProductVO getProductVOById(Long id);

    public Page<ProductVO> getProductVOPage(GetProductListDTO request, Pageable pageable);

    public Page<SubjectBrowseVO<ProductVO>> getProductBrowseVOPage(Long userId, Pageable pageable);

    public Page<SubjectCollectionVO<ProductVO>> getProductCollectionVOPage(Long userId, Pageable pageable);

    //获取首页商品推荐
    public Page<RecommendVO<ProductVO>> getProductRecommendVOPage(GetRecommendListDTO request, Pageable pageable);



}
