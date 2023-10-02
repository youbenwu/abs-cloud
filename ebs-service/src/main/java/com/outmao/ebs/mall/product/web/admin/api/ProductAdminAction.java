package com.outmao.ebs.mall.product.web.admin.api;


import com.outmao.ebs.common.util.StringUtil;
import com.outmao.ebs.mall.product.entity.Product;
import com.outmao.ebs.org.common.annotation.AccessPermission;
import com.outmao.ebs.org.common.annotation.AccessPermissionGroup;
import com.outmao.ebs.org.common.annotation.AccessPermissionParent;
import com.outmao.ebs.portal.dto.GetRecommendListDTO;
import com.outmao.ebs.portal.vo.RecommendVO;
import com.outmao.ebs.mall.product.dto.*;
import com.outmao.ebs.mall.product.service.ProductService;
import com.outmao.ebs.mall.product.vo.ProductVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@AccessPermissionGroup(title="电商",url="/mall",name="",children = {
        @AccessPermissionParent(title = "商品管理",url = "/mall/product",name = "",children = {
                @AccessPermission(title = "保存商品信息",url = "/mall/product",name = "save"),
                @AccessPermission(title = "删除商品信息",url = "/mall/product",name = "delete"),
                @AccessPermission(title = "读取商品信息",url = "/mall/product",name = "read"),
                @AccessPermission(title = "设置商品状态",url = "/mall/product",name = "status"),
                @AccessPermission(title = "审核商品信息",url = "/mall/product",name = "hasPermission"),
        }),
        @AccessPermissionParent(title = "商品类别管理",url = "/mall/product/category",name = "",children = {
                @AccessPermission(title = "保存商品类别",url = "/mall/product/category",name = "save"),
                @AccessPermission(title = "删除商品类别",url = "/mall/product/category",name = "delete"),
                @AccessPermission(title = "读取商品类别",url = "/mall/product/category",name = "read"),
        }),

})


@Api(value = "account-mall-product", tags = "后台-电商-商品")
@RestController
@RequestMapping("/api/admin/mall/product")
public class ProductAdminAction {

	@Autowired
    private ProductService productService;


    @PreAuthorize("hasPermission('/mall/product','save')")
    @ApiOperation(value = "保存商品信息", notes = "保存商品信息")
    @PostMapping("/save")
    public void saveProduct(@RequestBody ProductDTO request){
         productService.saveProduct(request);
    }

    @PreAuthorize("hasPermission('/mall/product','delete')")
    @ApiOperation(value = "删除商品信息", notes = "删除商品信息")
    @PostMapping("/delete")
    public void deleteProductById(Long id) {
        productService.deleteProductById(id);
    }

    @PreAuthorize("hasPermission('/mall/product','status')")
    @ApiOperation(value = "设置商品状态", notes = "设置商品状态")
    @PostMapping("/setStatus")
    public void setProductStatus(SetProductStatusDTO request){
         productService.setProductStatus(request);
    }

    @PreAuthorize("hasPermission('/mall/product','check')")
    @ApiOperation(value = "设置商品审核状态", notes = "设置商品审核状态")
    @PostMapping("/setCheckStatus")
    public void setProductCheckStatus(SetProductAuditStatusDTO request) {
         productService.setProductAuditStatus(request);
    }

    @PreAuthorize("hasPermission('/mall/product','save')")
    @ApiOperation(value = "设置商品库存", notes = "设置商品库存")
    @PostMapping("/setStock")
    public void setProductStock(SetProductStockDTO request) {
        productService.setProductStock(request);
    }

    @PreAuthorize("hasPermission('/mall/product','read')")
    @ApiOperation(value = "获取商品信息", notes = "获取商品信息")
    @PostMapping("/get")
    public ProductVO getProductVOById(Long id){
        return productService.getProductVOById(id);
    }

    @PreAuthorize("hasPermission('/mall/product','read')")
    @ApiOperation(value = "获取商品信息列表", notes = "获取商品信息列表")
    @PostMapping("/page")
    public Page<ProductVO> getProductVOPage(@RequestBody GetProductListDTO request, Pageable pageable){
        return productService.getProductVOPage(request,request.getPageable(pageable));
    }

    //获取首页商品推荐
    @ApiOperation(value = "获取首页商品推荐列表", notes = "获取首页商品推荐列表")
    @PostMapping("/recommend/page")
    public Page<RecommendVO<ProductVO>> getProductRecommendVOPage(GetRecommendListDTO request,Pageable pageable){
        return productService.getProductRecommendVOPage(request,pageable);
    }




}
