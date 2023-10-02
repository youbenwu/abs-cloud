package com.outmao.ebs.mall.shop.domain;

import com.outmao.ebs.mall.shop.dto.GetShopListDTO;
import com.outmao.ebs.mall.shop.dto.SetShopStatusDTO;
import com.outmao.ebs.mall.shop.dto.ShopDTO;
import com.outmao.ebs.mall.shop.entity.Shop;
import com.outmao.ebs.mall.shop.vo.ShopVO;
import com.outmao.ebs.mall.shop.vo.SimpleShopVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

public interface ShopDomain {



    public Shop saveShop(ShopDTO request);


    public Shop setShopStatus(SetShopStatusDTO request);


    public ShopVO getShopVOById(Long id);


    public ShopVO getShopVOByUserId(Long userId);


    public Page<ShopVO> getShopVOPage(GetShopListDTO request, Pageable pageable);


    public List<SimpleShopVO> getSimpleShopVOListByIdIn(Collection<Long> idIn);


}
