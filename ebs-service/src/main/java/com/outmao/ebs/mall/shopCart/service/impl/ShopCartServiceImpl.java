package com.outmao.ebs.mall.shopCart.service.impl;

import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.mall.shopCart.domain.ShopCartDomain;
import com.outmao.ebs.mall.shopCart.dto.DeleteShopCartProductListDTO;
import com.outmao.ebs.mall.shopCart.dto.ShopCartProductDTO;
import com.outmao.ebs.mall.shopCart.entity.ShopCartProduct;
import com.outmao.ebs.mall.shopCart.service.ShopCartService;
import com.outmao.ebs.mall.shopCart.vo.ShopCartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ShopCartServiceImpl extends BaseService implements ShopCartService {


    @Autowired
    private ShopCartDomain shopCartDomain;

    @Override
    public ShopCartProduct saveShopCartProduct(ShopCartProductDTO request) {
        return shopCartDomain.saveShopCartProduct(request);
    }

    @Override
    public void deleteShopCartProductList(DeleteShopCartProductListDTO request) {
        shopCartDomain.deleteShopCartProductList(request);
    }

    @Override
    public ShopCartVO getShopCartVOByUserId(Long userId) {
        return shopCartDomain.getShopCartVOByUserId(userId);
    }
}
