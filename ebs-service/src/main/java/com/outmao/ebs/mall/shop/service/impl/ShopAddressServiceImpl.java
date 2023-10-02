package com.outmao.ebs.mall.shop.service.impl;

import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.mall.shop.domain.ShopAddressDomain;
import com.outmao.ebs.mall.shop.dto.GetShopAddressListDTO;
import com.outmao.ebs.mall.shop.dto.ShopAddressDTO;
import com.outmao.ebs.mall.shop.entity.ShopAddress;
import com.outmao.ebs.mall.shop.service.ShopAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class ShopAddressServiceImpl extends BaseService implements ShopAddressService {

    @Autowired
    private ShopAddressDomain shopAddressDomain;

    @Override
    public ShopAddress saveShopAddress(ShopAddressDTO request) {
        return shopAddressDomain.saveShopAddress(request);
    }

    @Override
    public void deleteShopAddressById(Long id) {
        shopAddressDomain.deleteShopAddressById(id);
    }

    @Override
    public ShopAddress getDefaultShopAddress(Long shopId, int type) {
        return shopAddressDomain.getDefaultShopAddress(shopId,type);
    }

    @Override
    public Page<ShopAddress> getShopAddressPage(GetShopAddressListDTO request, Pageable pageable) {
        return shopAddressDomain.getShopAddressPage(request,pageable);
    }

}
