package com.outmao.ebs.mall.product.web.api;


import com.outmao.ebs.mall.product.dto.GetProductCategoryListDTO;
import com.outmao.ebs.mall.product.service.ProductCategoryService;
import com.outmao.ebs.mall.product.vo.ProductCategoryVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;





@Api(value = "mall-product-category", tags = "电商-商品-类别")
@RestController
@RequestMapping("/api/mall/product/category")
public class ProductCategoryAction {

	@Autowired
    private ProductCategoryService productCategoryService;


    @ApiOperation(value = "获取商品类别列表", notes = "获取商品类别列表")
    @PostMapping("/list")
    public List<ProductCategoryVO> getProductCategoryVOList(){
        return productCategoryService.getProductCategoryVOList();
    }


    @ApiOperation(value = "获取商品类别列表(不分级)", notes = "获取商品类别列表(不分级)")
    @PostMapping("/page")
    public Page<ProductCategoryVO> getProductCategoryVOPage(GetProductCategoryListDTO request, Pageable pageable) {
        return productCategoryService.getProductCategoryVOPage(request,pageable);
    }


}
