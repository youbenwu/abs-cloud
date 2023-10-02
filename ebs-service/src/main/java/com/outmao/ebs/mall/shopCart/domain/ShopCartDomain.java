package com.outmao.ebs.mall.shopCart.domain;

import com.outmao.ebs.mall.shopCart.dto.DeleteShopCartProductListDTO;
import com.outmao.ebs.mall.shopCart.dto.ShopCartProductDTO;
import com.outmao.ebs.mall.shopCart.entity.ShopCartProduct;
import com.outmao.ebs.mall.shopCart.vo.ShopCartVO;


public interface ShopCartDomain {

    public ShopCartProduct saveShopCartProduct(ShopCartProductDTO request);

    public void deleteShopCartProductList(DeleteShopCartProductListDTO request);

    public ShopCartVO getShopCartVOByUserId(Long userId);

}
