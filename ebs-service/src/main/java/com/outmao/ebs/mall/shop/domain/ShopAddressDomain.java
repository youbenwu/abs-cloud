package com.outmao.ebs.mall.shop.domain;

import com.outmao.ebs.mall.shop.dto.GetShopAddressListDTO;
import com.outmao.ebs.mall.shop.dto.ShopAddressDTO;
import com.outmao.ebs.mall.shop.entity.ShopAddress;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ShopAddressDomain {

    public ShopAddress saveShopAddress(ShopAddressDTO request);

    public void deleteShopAddressById(Long id);

    public ShopAddress getDefaultShopAddress(Long shopId, int type);

    public Page<ShopAddress> getShopAddressPage(GetShopAddressListDTO request, Pageable pageable);

}
