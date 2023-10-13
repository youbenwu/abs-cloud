package com.outmao.ebs.portal.service.impl;

import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.mall.merchant.service.MerchantService;
import com.outmao.ebs.mall.product.common.constant.ProductType;
import com.outmao.ebs.mall.product.dto.ProductDTO;
import com.outmao.ebs.mall.product.dto.ProductSkuDTO;
import com.outmao.ebs.mall.product.entity.Product;
import com.outmao.ebs.mall.product.service.ProductService;
import com.outmao.ebs.portal.domain.AdvertChannelDomain;
import com.outmao.ebs.portal.dto.AdvertChannelDTO;
import com.outmao.ebs.portal.dto.GetAdvertChannelListDTO;
import com.outmao.ebs.portal.entity.AdvertChannel;
import com.outmao.ebs.portal.service.AdvertChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class AdvertChannelServiceImpl extends BaseService implements AdvertChannelService {

    @Autowired
    private AdvertChannelDomain advertChannelDomain;

    @Autowired
    private ProductService productService;


    @Transactional
    @Override
    public AdvertChannel saveAdvertChannel(AdvertChannelDTO request) {
        AdvertChannel channel= advertChannelDomain.saveAdvertChannel(request);
        Product product=channel.getProductId()!=null?productService.getProductById(channel.getProductId()):null;
        if(product==null){
            ProductDTO productDTO=new ProductDTO();
            productDTO.setType(ProductType.ADVERT_CHANNEL.getType());
            productDTO.setTitle(request.getTitle());
            productDTO.setSubtitle("广告位置商品，用于购买投放广告位置");
            productDTO.setImage("abc");
            ProductSkuDTO skuDTO=new ProductSkuDTO();
            skuDTO.setPrice(request.getPrice());
            skuDTO.setName(channel.getTitle());
            skuDTO.setStock(channel.getMaxNum());
            productDTO.setSkus(new ArrayList<>());
            productDTO.getSkus().add(skuDTO);
            product=productService.saveProduct(productDTO);
            channel.setProductId(product.getId());
        }

        return channel;
    }

    @Override
    public void deleteAdvertChannelById(Long id) {
        advertChannelDomain.deleteAdvertChannelById(id);
    }

    @Override
    public AdvertChannel getAdvertChannelByCode(String code) {
        return advertChannelDomain.getAdvertChannelByCode(code);
    }

    @Override
    public Page<AdvertChannel> getAdvertChannelPage(GetAdvertChannelListDTO request, Pageable pageable) {
        return advertChannelDomain.getAdvertChannelPage(request,pageable);
    }

}
