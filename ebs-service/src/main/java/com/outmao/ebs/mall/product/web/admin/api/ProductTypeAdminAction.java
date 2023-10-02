package com.outmao.ebs.mall.product.web.admin.api;


import com.outmao.ebs.mall.product.dto.*;
import com.outmao.ebs.mall.product.entity.ProductCategory;
import com.outmao.ebs.mall.product.entity.ProductTypeAttribute;
import com.outmao.ebs.mall.product.entity.ProductTypeAttributeGroup;
import com.outmao.ebs.mall.product.entity.ProductTypeProperty;
import com.outmao.ebs.mall.product.service.ProductService;
import com.outmao.ebs.mall.product.service.ProductTypeService;
import com.outmao.ebs.mall.product.vo.HouseVO;
import com.outmao.ebs.mall.product.vo.ProductCategoryVO;
import com.outmao.ebs.mall.product.vo.ProductTypeVO;
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
        @AccessPermissionParent(title = "商品类型管理",url = "/mall/product/type",name = "",children = {
                @AccessPermission(title = "保存商品类型",url = "/mall/product/type",name = "save"),
                @AccessPermission(title = "删除商品类型",url = "/mall/product/type",name = "delete"),
                @AccessPermission(title = "读取商品类型",url = "/mall/product/type",name = "read"),
        }),
})


@Api(value = "account-mall-product-type", tags = "后台-电商-商品-类型")
@RestController
@RequestMapping("/api/admin/mall/product/type")
public class ProductTypeAdminAction {

	@Autowired
    private ProductTypeService productTypeService;



    @PreAuthorize("hasPermission('/mall/product/type','save')")
    @ApiOperation(value = "保存商品类型", notes = "保存商品类型")
    @PostMapping("/save")
    public void saveProductType(@RequestBody ProductTypeDTO request){
        productTypeService.saveProductType(request);
    }

    @PreAuthorize("hasPermission('/mall/product/type','delete')")
    @ApiOperation(value = "删除商品类型", notes = "删除商品类型")
    @PostMapping("/delete")
    public void deleteProductTypeById(Long id){
        productTypeService.deleteProductTypeById(id);
    }

    @PreAuthorize("hasPermission('/mall/product/type','read')")
    @ApiOperation(value = "获取商品类型", notes = "获取商品类型")
    @PostMapping("/get")
    public ProductTypeVO getProductTypeVOById(Long id){
        return productTypeService.getProductTypeVOById(id);
    }

    @PreAuthorize("hasPermission('/mall/product/type','read')")
    @ApiOperation(value = "获取商品类型列表", notes = "获取商品类型列表")
    @PostMapping("/list")
    public List<ProductTypeVO> getProductTypeVOList(){
        return productTypeService.getProductTypeVOList();
    }


    @PreAuthorize("hasPermission('/mall/product/type','save')")
    @ApiOperation(value = "保存商品类型参数", notes = "保存商品类型参数")
    @PostMapping("/attribute/save")
    public void saveProductTypeAttribute(ProductTypeAttributeDTO request) {
        productTypeService.saveProductTypeAttribute(request);
    }

    @PreAuthorize("hasPermission('/mall/product/type','save')")
    @ApiOperation(value = "删除商品类型参数", notes = "删除商品类型参数")
    @PostMapping("/attribute/delete")
    public void deleteProductTypeAttributeById(Long id) {
        productTypeService.deleteProductTypeAttributeById(id);
    }

    @PreAuthorize("hasPermission('/mall/product/type','save')")
    @ApiOperation(value = "保存商品类型参数分组", notes = "保存商品类型参数分组")
    @PostMapping("/attribute/group/save")
    public void saveProductTypeAttributeGroup(ProductTypeAttributeGroupDTO request) {
        productTypeService.saveProductTypeAttributeGroup(request);
    }


    @PreAuthorize("hasPermission('/mall/product/type','save')")
    @ApiOperation(value = "删除商品类型参数分组", notes = "删除商品类型参数分组")
    @PostMapping("/attribute/group/delete")
    public void deleteProductTypeAttributeGroupById(Long id) {
        productTypeService.deleteProductTypeAttributeGroupById(id);
    }

    @PreAuthorize("hasPermission('/mall/product/type','save')")
    @ApiOperation(value = "保存商品类型属性", notes = "保存商品类型属性")
    @PostMapping("/property/save")
    public void saveProductTypeProperty(ProductTypePropertyDTO request) {
        productTypeService.saveProductTypeProperty(request);
    }

    @PreAuthorize("hasPermission('/mall/product/type','save')")
    @ApiOperation(value = "删除商品类型属性", notes = "删除商品类型属性")
    @PostMapping("/property/delete")
    public void deleteProductTypePropertyById(Long id) {
        productTypeService.deleteProductTypePropertyById(id);
    }


}
