package com.outmao.ebs.mall.product.web.api;


import com.outmao.ebs.mall.product.dto.GetProductTypeListDTO;
import com.outmao.ebs.mall.product.service.ProductTypeService;
import com.outmao.ebs.mall.product.vo.ProductTypeVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;



@Api(value = "mall-product-type", tags = "电商-商品-类型")
@RestController
@RequestMapping("/api/mall/product/type")
public class ProductTypeAction {

	@Autowired
    private ProductTypeService productTypeService;


    @ApiOperation(value = "获取商品类型", notes = "获取商品类型")
    @PostMapping("/get")
    public ProductTypeVO getProductTypeVOById(Long id){
        return productTypeService.getProductTypeVOById(id);
    }


    @ApiOperation(value = "获取商品类型列表", notes = "获取商品类型列表")
    @PostMapping("/list")
    public List<ProductTypeVO> getProductTypeVOList(){
        return productTypeService.getProductTypeVOList();
    }


    @ApiOperation(value = "获取商品类型列表", notes = "获取商品类型列表")
    @PostMapping("/page")
    public Page<ProductTypeVO> getProductTypeVOPage(GetProductTypeListDTO request, Pageable pageable){
        return productTypeService.getProductTypeVOPage(request,pageable);
    }


}
