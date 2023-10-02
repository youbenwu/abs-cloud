package com.outmao.ebs.mall.shopCart.web.api;



import com.outmao.ebs.mall.shopCart.dto.DeleteShopCartProductListDTO;
import com.outmao.ebs.mall.shopCart.dto.ShopCartProductDTO;
import com.outmao.ebs.mall.shopCart.service.ShopCartService;
import com.outmao.ebs.mall.shopCart.vo.ShopCartVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(value = "mall-shopCart", tags = "电商-购物车")
@RestController
@RequestMapping("/api/mall/shopCart")
public class ShopCartAction {


    @Autowired
    private ShopCartService shopCartService;


    @PreAuthorize("principal.id.equals(#request.userId)")
    @ApiOperation(value = "添加购物车商品", notes = "添加购物车商品")
    @PostMapping("/product/save")
    public void saveShopCartProduct(ShopCartProductDTO request) {
        shopCartService.saveShopCartProduct(request);
    }

    @PreAuthorize("principal.id.equals(#request.userId)")
    @ApiOperation(value = "删除购物车商品", notes = "删除购物车商品")
    @PostMapping("/product/delete")
    public void deleteShopCartProductList(@RequestBody DeleteShopCartProductListDTO request) {
        shopCartService.deleteShopCartProductList(request);
    }

    @PreAuthorize("principal.id.equals(#userId)")
    @ApiOperation(value = "获取购物车信息", notes = "获取购物车信息")
    @PostMapping("/get")
    public ShopCartVO getShopCartVOByUserId(Long userId) {
        return shopCartService.getShopCartVOByUserId(userId);
    }


}
