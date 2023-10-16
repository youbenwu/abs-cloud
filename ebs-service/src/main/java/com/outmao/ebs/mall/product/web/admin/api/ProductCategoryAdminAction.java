package com.outmao.ebs.mall.product.web.admin.api;


import com.outmao.ebs.mall.product.dto.*;
import com.outmao.ebs.mall.product.entity.ProductCategory;
import com.outmao.ebs.mall.product.service.ProductCategoryService;
import com.outmao.ebs.mall.product.service.ProductService;
import com.outmao.ebs.mall.product.vo.HouseVO;
import com.outmao.ebs.mall.product.vo.ProductCategoryVO;
import com.outmao.ebs.mall.product.vo.ProductVO;
import com.outmao.ebs.org.common.annotation.AccessPermission;
import com.outmao.ebs.org.common.annotation.AccessPermissionGroup;
import com.outmao.ebs.org.common.annotation.AccessPermissionParent;
import com.outmao.ebs.portal.dto.GetRecommendListDTO;
import com.outmao.ebs.portal.vo.RecommendVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;


@AccessPermissionGroup(title="电商",url="/mall",name="",children = {
        @AccessPermissionParent(title = "商品类别管理",url = "/mall/product/category",name = "",children = {
                @AccessPermission(title = "保存商品类别",url = "/mall/product/category",name = "save"),
                @AccessPermission(title = "删除商品类别",url = "/mall/product/category",name = "delete"),
                @AccessPermission(title = "读取商品类别",url = "/mall/product/category",name = "read"),
        }),
})


@Api(value = "admin-mall-product-category", tags = "后台-电商-商品-类别")
@RestController
@RequestMapping("/api/admin/mall/product/category")
public class ProductCategoryAdminAction {

	@Autowired
    private ProductCategoryService productCategoryService;


    @PreAuthorize("hasPermission(null,'/mall/product/category','save')")
    @ApiOperation(value = "保存商品类别", notes = "保存商品类别")
    @PostMapping("/save")
    public ProductCategory saveProductCategory(ProductCategoryDTO request){
        return productCategoryService.saveProductCategory(request);
    }

    @PreAuthorize("hasPermission(null,'/mall/product/category','delete')")
    @ApiOperation(value = "删除商品类别", notes = "删除商品类别")
    @PostMapping("/delete")
    public void deleteProductCategoryById(Long id){
        productCategoryService.deleteProductCategoryById(id);
    }

    @PreAuthorize("hasPermission(null,'/mall/product/category','read')")
    @ApiOperation(value = "获取商品类别列表", notes = "获取商品类别列表")
    @PostMapping("/list")
    public List<ProductCategoryVO> getProductCategoryVOList(){
        return productCategoryService.getProductCategoryVOList();
    }

    @PreAuthorize("hasPermission(null,'/mall/product/category','read')")
    @ApiOperation(value = "获取商品类别列表(不分级)", notes = "获取商品类别列表(不分级)")
    @PostMapping("/page")
    public Page<ProductCategoryVO> getProductCategoryVOPage(GetProductCategoryListDTO request, Pageable pageable) {
        return productCategoryService.getProductCategoryVOPage(request,pageable);
    }



}
