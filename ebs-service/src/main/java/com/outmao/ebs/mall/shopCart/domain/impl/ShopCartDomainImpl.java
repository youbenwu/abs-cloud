package com.outmao.ebs.mall.shopCart.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.mall.product.dao.ProductDao;
import com.outmao.ebs.mall.product.dao.ProductSkuDao;
import com.outmao.ebs.mall.product.entity.Product;
import com.outmao.ebs.mall.product.entity.ProductSku;
import com.outmao.ebs.mall.shop.dao.ShopDao;
import com.outmao.ebs.mall.shop.entity.Shop;
import com.outmao.ebs.mall.shopCart.common.constant.ShopCartProductStatus;
import com.outmao.ebs.mall.shopCart.dao.ShopCartProductDao;
import com.outmao.ebs.mall.shopCart.domain.ShopCartDomain;
import com.outmao.ebs.mall.shopCart.dto.DeleteShopCartProductListDTO;
import com.outmao.ebs.mall.shopCart.dto.ShopCartProductDTO;
import com.outmao.ebs.mall.shopCart.entity.ShopCartProduct;
import com.outmao.ebs.mall.shopCart.vo.ShopCartProductVO;
import com.outmao.ebs.mall.shopCart.vo.ShopCartShopVO;
import com.outmao.ebs.mall.shopCart.vo.ShopCartVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class ShopCartDomainImpl extends BaseDomain implements ShopCartDomain {


    @Autowired
    private ShopCartProductDao shopCartProductDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ProductSkuDao productSkuDao;

    @Autowired
    private ShopDao shopDao;

    @Transactional
    @Override
    public ShopCartProduct saveShopCartProduct(ShopCartProductDTO request) {
        ShopCartProduct product=request.getId()==null?null:shopCartProductDao.getOne(request.getId());
        if(product!=null&&!product.getSkuId().equals(request.getSkuId())){
            shopCartProductDao.delete(product);
            product=shopCartProductDao.findByUserIdAndSkuId(request.getUserId(),request.getSkuId());
            if(product!=null){
                request.setQuantity(request.getQuantity()+product.getQuantity());
            }
        }

        if(product==null){
            product=new ShopCartProduct();
            product.setUserId(request.getUserId());
            product.setSkuId(request.getSkuId());
            product.setCreateTime(new Date());
            updateProduct(product);
        }
        product.setQuantity(request.getQuantity());
        product.setUpdateTime(new Date());
        shopCartProductDao.save(product);
        return product;
    }


    private void updateProduct(ShopCartProduct p){
        ProductSku sku=productSkuDao.getOne(p.getSkuId());
        p.setSkuName(sku.getName());
        p.setPrice(sku.getPrice());
        p.setProductId(sku.getProduct().getId());
        p.setProductTitle(sku.getProduct().getTitle());
        p.setProductImage(sku.getImage());

        if(p.getProductImage()==null){
            p.setProductImage(sku.getProduct().getImage());
        }

        Shop shop=shopDao.getOne(sku.getProduct().getShopId());

        p.setShopId(shop.getId());
        p.setShopTitle(shop.getTitle());

    }


    @Transactional
    @Override
    public void deleteShopCartProductList(DeleteShopCartProductListDTO request) {
        if(request.getSkuIdIn()!=null&&request.getSkuIdIn().size()>0){
            shopCartProductDao.deleteAllByUserIdAndSkuIdIn(request.getUserId(),request.getSkuIdIn());
        }else{
            shopCartProductDao.deleteAllByUserId(request.getUserId());
        }
    }

    @Override
    public ShopCartVO getShopCartVOByUserId(Long userId) {

        List<ShopCartProduct> list=shopCartProductDao.findAllByUserId(userId);

        ShopCartVO vo=new ShopCartVO();
        vo.setUserId(userId);

        if(list.isEmpty()){
            vo.setShops(new ArrayList<>());
            return vo;
        }

        Map<Long, ShopCartShopVO> shopCartShopVOMap=new HashMap<>();

        List<ProductSku> skus=productSkuDao.findAllByIdIn(list.stream().map(t->t.getSkuId()).collect(Collectors.toList()));
        Map<Long,ProductSku> skuMap=skus.stream().collect(Collectors.toMap(t->t.getId(),t->t));

        List<Product> products=productDao.findAllByIdIn(list.stream().map(t->t.getProductId()).collect(Collectors.toList()));
        Map<Long,Product> productMap=products.stream().collect(Collectors.toMap(t->t.getId(),t->t));

        List<Shop> shops=shopDao.findAllByIdIn(list.stream().map(t->t.getShopId()).collect(Collectors.toList()));
        Map<Long,Shop> shopMap=shops.stream().collect(Collectors.toMap(t->t.getId(),t->t));


        list.forEach(t->{
            ShopCartProductVO p=new ShopCartProductVO();
            BeanUtils.copyProperties(t,p);

            ProductSku sku=skuMap.get(p.getSkuId());
            if(sku==null){
                p.setStatus(ShopCartProductStatus.SKU.getStatus());
            }else{
                p.setProductImage(sku.getImage());
                p.setSkuName(sku.getName());
                p.setPrice(sku.getPrice());
                p.setStore(sku.getStock());
            }

            Product product=productMap.get(p.getProductId());

            if(product==null){
                p.setStatus(ShopCartProductStatus.PRODUCT.getStatus());
            }else{
                p.setProductTitle(product.getTitle());
                if(p.getProductImage()==null){
                    p.setProductImage(product.getImage());
                }
            }

            ShopCartShopVO s=shopCartShopVOMap.get(t.getShopId());
            if(s==null){
                s=new ShopCartShopVO();
                s.setShopId(t.getShopId());
                s.setShopTitle(t.getShopTitle());
                Shop shop=shopMap.get(s.getShopId());
                if(shop!=null){
                    s.setShopTitle(shop.getTitle());
                }
                s.setProducts(new ArrayList<>());
                shopCartShopVOMap.put(s.getShopId(),s);
            }
            s.getProducts().add(p);
        });


        vo.setShops(shopCartShopVOMap.values().stream().collect(Collectors.toList()));

        return vo;
    }




}
