package com.outmao.ebs.mall.product.web.api;


import com.outmao.ebs.bbs.vo.SubjectBrowseVO;
import com.outmao.ebs.bbs.vo.SubjectCollectionVO;
import com.outmao.ebs.portal.dto.GetRecommendListDTO;
import com.outmao.ebs.portal.vo.RecommendVO;
import com.outmao.ebs.mall.product.dto.GetHouseListDTO;
import com.outmao.ebs.mall.product.dto.GetProductListDTO;
import com.outmao.ebs.mall.product.service.ProductService;
import com.outmao.ebs.mall.product.vo.HouseVO;
import com.outmao.ebs.mall.product.vo.ProductCategoryVO;
import com.outmao.ebs.mall.product.vo.ProductTypeVO;
import com.outmao.ebs.mall.product.vo.ProductVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;



@Api(value = "mall-product", tags = "电商-商品")
@RestController
@RequestMapping("/api/mall/product")
public class ProductAction {

	@Autowired
    private ProductService productService;



    @PreAuthorize("permitAll")
    @ApiOperation(value = "获取商品信息", notes = "获取商品信息")
    @PostMapping("/get")
    public ProductVO getProductVOById(Long id){
        return productService.getProductVOById(id);
    }

    @PreAuthorize("permitAll")
    @ApiOperation(value = "获取商品信息", notes = "获取商品信息")
    @PostMapping("/getByCode")
    public ProductVO getProductVOByCode(String code){
        return productService.getProductVOByCode(code);
    }

    @PreAuthorize("permitAll")
    @ApiOperation(value = "获取商品信息列表", notes = "获取商品信息列表")
    @PostMapping("/page")
    public Page<ProductVO> getProductVOPage(@RequestBody GetProductListDTO request, Pageable pageable){
        return productService.getProductVOPage(request,request.getPageable(pageable));
    }


    @PreAuthorize("principal.id.equals(#userId)")
    @ApiOperation(value = "获取商品浏览列表", notes = "获取商品浏览列表")
    @PostMapping("/browse/page")
    public Page<SubjectBrowseVO<ProductVO>> getProductBrowseVOPage(Long userId, Pageable pageable){
        return productService.getProductBrowseVOPage(userId,pageable);
    }

    @PreAuthorize("principal.id.equals(#userId)")
    @ApiOperation(value = "获取商品收藏列表", notes = "获取商品收藏列表")
    @PostMapping("/collection/page")
    public Page<SubjectCollectionVO<ProductVO>> getProductCollectionVOPage(Long userId, Pageable pageable){
        return productService.getProductCollectionVOPage(userId,pageable);
    }

    //获取首页商品推荐
    @PreAuthorize("permitAll")
    @ApiOperation(value = "获取首页商品推荐列表", notes = "获取首页商品推荐列表")
    @PostMapping("/recommend/page")
    public Page<RecommendVO<ProductVO>> getProductRecommendVOPage(GetRecommendListDTO request,Pageable pageable){
        return productService.getProductRecommendVOPage(request,pageable);
    }



}
