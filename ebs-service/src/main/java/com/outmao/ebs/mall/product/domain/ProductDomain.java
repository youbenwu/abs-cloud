package com.outmao.ebs.mall.product.domain;

import com.outmao.ebs.mall.product.dto.*;
import com.outmao.ebs.mall.product.entity.Product;
import com.outmao.ebs.mall.product.entity.ProductSku;
import com.outmao.ebs.mall.product.vo.MiniProductSkuVO;
import com.outmao.ebs.mall.product.vo.MiniProductVO;
import com.outmao.ebs.mall.product.vo.ProductVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Collection;
import java.util.List;

public interface ProductDomain {


      public Product saveProduct(ProductDTO request);

      public Product getProductById(Long id);

      public void deleteProductById(Long id);

      public void deleteProductList(Collection<Long> ids);

      public Product setProductOnSell(SetProductOnSellDTO request);

      public Product setProductStatus(SetProductStatusDTO request);

      public Product setProductStock(SetProductStockDTO request);

      public ProductSku saveProductSku(SaveProductSkuDTO request);

      public ProductSku getProductSku(Long productId,String skuNo);

      public void skuStockOut(List<ProductSkuStockOutDTO> request);

      public long getProductCount();

      public ProductVO getProductVOById(Long id);

      public ProductVO getProductVOByCode(String code);

      public ProductVO getProductVO(Long id, Long skuId);

      public Page<ProductVO> getProductVOPage(GetProductListDTO request, Pageable pageable);

      public List<ProductVO> getProductVOListByIdIn(Collection<Long> idIn);

      public List<ProductVO> getProductVOListByIdIn(Collection<Long> idIn, String[] attrs);


      public List<MiniProductVO> getMiniProductVOListByIdIn(Collection<Long> idIn);

      public List<MiniProductSkuVO> getMiniProductSkuVOListByIdIn(Collection<Long> idIn);



}
