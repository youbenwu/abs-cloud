package com.outmao.ebs.portal.service.impl;

import com.outmao.ebs.common.base.BaseService;
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
import com.outmao.ebs.portal.vo.AdvertChannelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
        if(channel.getProductId()==null){
            bindProduct(channel);
        }


        return channel;
    }


    private void bindProduct(AdvertChannel channel){
        //0--图文 1--图文视频 2--图文视频链接 3--图文视频二维码 4--图文视频链接二维码 5--图文链接 6--图文二维码

        ProductDTO productDTO=new ProductDTO();
        productDTO.setCategoryId(0L);
        productDTO.setTitle(channel.getTitle());
        productDTO.setSubtitle(channel.getDescription());
        productDTO.setImage("http://");

        productDTO.setSkus(new ArrayList<>());
        productDTO.getSkus().add(new ProductSkuDTO("1000PV图文",0.01,Long.MAX_VALUE));
        productDTO.getSkus().add(new ProductSkuDTO("1000PV图文视频",0.01,Long.MAX_VALUE));
        productDTO.getSkus().add(new ProductSkuDTO("1000PV图文链接",0.01,Long.MAX_VALUE));
        productDTO.getSkus().add(new ProductSkuDTO("1000PV图文二维码",0.01,Long.MAX_VALUE));
        productDTO.getSkus().add(new ProductSkuDTO("1000PV图文视频链接",0.01,Long.MAX_VALUE));
        productDTO.getSkus().add(new ProductSkuDTO("1000PV图文视频二维码",0.01,Long.MAX_VALUE));
        productDTO.getSkus().add(new ProductSkuDTO("1000PV图文视频链接二维码",0.01,Long.MAX_VALUE));

        Product product=productService.saveProduct(productDTO);
        product.setType(ProductType.HOTEL_ADVERT_CHANNEL.getType());
        product.setSellerFinish(true);
        product.setNoDelivery(true);
        channel.setProductId(product.getId());

    }

    @Override
    public void deleteAdvertChannelById(Long id) {
        advertChannelDomain.deleteAdvertChannelById(id);
    }


    @Override
    public AdvertChannel getAdvertChannelById(Long id) {
        return advertChannelDomain.getAdvertChannelById(id);
    }

    @Override
    public AdvertChannel getAdvertChannelByCode(String code) {
        return advertChannelDomain.getAdvertChannelByCode(code);
    }

    @Override
    public List<AdvertChannel> getAdvertChannelList(GetAdvertChannelListDTO request) {
        return advertChannelDomain.getAdvertChannelList(request);
    }

    @Override
    public Page<AdvertChannel> getAdvertChannelPage(GetAdvertChannelListDTO request, Pageable pageable) {
        return advertChannelDomain.getAdvertChannelPage(request,pageable);
    }

    @Override
    public AdvertChannelVO getAdvertChannelVOById(Long id) {
        return advertChannelDomain.getAdvertChannelVOById(id);
    }

    @Override
    public AdvertChannelVO getAdvertChannelVOByCode(String code) {
        return advertChannelDomain.getAdvertChannelVOByCode(code);
    }

    @Override
    public List<AdvertChannelVO> getAdvertChannelVOList(GetAdvertChannelListDTO request) {
        return advertChannelDomain.getAdvertChannelVOList(request);
    }

    @Override
    public Page<AdvertChannelVO> getAdvertChannelVOPage(GetAdvertChannelListDTO request, Pageable pageable) {
        return advertChannelDomain.getAdvertChannelVOPage(request,pageable);
    }



}
