package com.outmao.ebs.mall.product.service.impl;

import com.outmao.ebs.bbs.common.annotation.BindingSubjectId;
import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.common.vo.ItemListGetter;
import com.outmao.ebs.bbs.common.data.GetSubjectItemList;
import com.outmao.ebs.bbs.domain.SubjectDomain;
import com.outmao.ebs.bbs.vo.SubjectBrowseVO;
import com.outmao.ebs.bbs.vo.SubjectCollectionVO;
import com.outmao.ebs.mall.merchant.entity.Merchant;
import com.outmao.ebs.mall.merchant.service.MerchantService;
import com.outmao.ebs.mall.product.entity.ProductBsType;
import com.outmao.ebs.mall.product.entity.ProductSku;
import com.outmao.ebs.portal.dto.GetRecommendListDTO;
import com.outmao.ebs.portal.service.RecommendService;
import com.outmao.ebs.portal.vo.RecommendVO;
import com.outmao.ebs.mall.product.domain.*;
import com.outmao.ebs.mall.product.dto.*;
import com.outmao.ebs.mall.product.entity.Product;
import com.outmao.ebs.mall.product.service.ProductService;
import com.outmao.ebs.mall.product.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;


@Service
public class ProductServiceImpl extends BaseService implements ProductService {

    @Autowired
    private ProductBsTypeDomain productBsTypeDomain;

    @Autowired
    private ProductDomain productDomain;


    @Autowired
    private SubjectDomain subjectDomain;


    @Autowired
    private RecommendService recommendService;


    @Autowired
    private MerchantService merchantService;


    @Override
    public ProductBsType saveProductBsType(ProductBsType request) {
        return productBsTypeDomain.saveProductBsType(request);
    }

    @Override
    public void deleteProductBsType(Integer type) {
        productBsTypeDomain.deleteProductBsType(type);
    }

    @Override
    public List<ProductBsType> getProductBsTypeList() {
        return productBsTypeDomain.getProductBsTypeList();
    }


    @Transactional
    @BindingSubjectId
    @Override
    public Product saveProduct(ProductDTO request) {
        if(request.getShopId()==null){
            Merchant merchant=merchantService.getMerchant();
            request.setShopId(merchant.getShopId());
        }if(request.getCategoryId()==null){
            request.setCategoryId(0L);
        }
        return productDomain.saveProduct(request);
    }

    @Override
    public void deleteProductById(Long id) {
        productDomain.deleteProductById(id);
    }

    @Override
    public void deleteProductList(Collection<Long> ids) {
        productDomain.deleteProductList(ids);
    }

    @Override
    public Product setProductStatus(SetProductStatusDTO request) {

        return productDomain.setProductStatus(request);
    }

    @Override
    public Product setProductOnSell(SetProductOnSellDTO request) {
        return productDomain.setProductOnSell(request);
    }

    @Override
    public Product setProductStock(SetProductStockDTO request) {
        return productDomain.setProductStock(request);
    }

    @Override
    public ProductSku saveProductSku(SaveProductSkuDTO request) {
        return productDomain.saveProductSku(request);
    }

    @Override
    public ProductSku getProductSku(Long productId, String skuNo) {
        return productDomain.getProductSku(productId,skuNo);
    }

    @Override
    public void skuStockOut(List<ProductSkuStockOutDTO> request) {
        productDomain.skuStockOut(request);
    }

    @Override
    public Product getProductById(Long id) {
        return productDomain.getProductById(id);
    }

    @Override
    public ProductVO getProductVOById(Long id) {
        return productDomain.getProductVOById(id);
    }

    @Override
    public ProductVO getProductVOByCode(String code) {
        return productDomain.getProductVOByCode(code);
    }

    @Override
    public Page<ProductVO> getProductVOPage(GetProductListDTO request, Pageable pageable) {
        return productDomain.getProductVOPage(request,pageable);
    }



    @Override
    public Page<SubjectBrowseVO<ProductVO>> getProductBrowseVOPage(Long userId, Pageable pageable) {

        Page<SubjectBrowseVO<ProductVO>> page=subjectDomain.getSubjectBrowseVOPage(userId, "Product", new GetSubjectItemList<ProductVO>() {
            @Override
            public List<ProductVO> getSubjectItemList(Collection<Long> idIn) {
                return productDomain.getProductVOListByIdIn(idIn,null);
            }
        },pageable);

        return page;
    }

    @Override
    public Page<SubjectCollectionVO<ProductVO>> getProductCollectionVOPage(Long userId, Pageable pageable) {

        Page<SubjectCollectionVO<ProductVO>> page=subjectDomain.getSubjectCollectionVOPage(userId, "Product", new GetSubjectItemList<ProductVO>() {
            @Override
            public List<ProductVO> getSubjectItemList(Collection<Long> idIn) {
                return productDomain.getProductVOListByIdIn(idIn,null);
            }
        },pageable);

        return page;
    }



    @Override
    public Page<RecommendVO<ProductVO>> getProductRecommendVOPage(GetRecommendListDTO request,Pageable pageable) {
        return recommendService.getRecommendVOPage(request,new ItemListGetter<ProductVO>() {
            @Override
            public List<ProductVO> getItemListByIdIn(Collection<Long> idIn) {
                return productDomain.getProductVOListByIdIn(idIn,null);
            }
        },pageable);
    }





}
