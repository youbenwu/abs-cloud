package com.outmao.ebs.mall.promotion.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.mall.product.common.annotation.SetMiniProduct;
import com.outmao.ebs.mall.product.common.annotation.SetMiniProductSku;
import com.outmao.ebs.mall.product.dao.ProductSkuDao;
import com.outmao.ebs.mall.product.entity.ProductSku;
import com.outmao.ebs.mall.product.entity.QProduct;
import com.outmao.ebs.mall.promotion.dao.GiftsDao;
import com.outmao.ebs.mall.promotion.dao.GiftsProductSkuDao;
import com.outmao.ebs.mall.promotion.domain.GiftsDomain;
import com.outmao.ebs.mall.promotion.domain.GiftsProductSkuDomain;
import com.outmao.ebs.mall.promotion.domain.conver.GiftsProductSkuVOConver;
import com.outmao.ebs.mall.promotion.domain.conver.GiftsVOConver;
import com.outmao.ebs.mall.promotion.dto.GetGiftsListDTO;
import com.outmao.ebs.mall.promotion.dto.GiftsDTO;
import com.outmao.ebs.mall.promotion.dto.GiftsProductSkuDTO;
import com.outmao.ebs.mall.promotion.dto.SetGiftsStatusDTO;
import com.outmao.ebs.mall.promotion.entity.Gifts;
import com.outmao.ebs.mall.promotion.entity.GiftsProductSku;
import com.outmao.ebs.mall.promotion.entity.QGifts;
import com.outmao.ebs.mall.promotion.entity.QGiftsProductSku;
import com.outmao.ebs.mall.promotion.vo.GiftsProductSkuVO;
import com.outmao.ebs.mall.promotion.vo.GiftsVO;
import com.outmao.ebs.mall.shop.entity.QShop;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class GiftsDomainImpl extends BaseDomain implements GiftsDomain {

    @Autowired
    private GiftsDao giftsDao;

    @Autowired
    private ProductSkuDao productSkuDao;

    @Autowired
    private GiftsProductSkuDao giftsProductSkuDao;

    @Autowired
    private GiftsProductSkuDomain giftsProductSkuDomain;

    @Autowired
    private GiftsVOConver giftsVOConver;



    @Transactional
    @Override
    public Gifts saveGifts(GiftsDTO request) {


        if(request.getProductId()==null){
            throw new BusinessException("主商品ID不能为空");
        }

        if(request.getSkus()==null||request.getSkus().isEmpty()){
            throw new BusinessException("赠品SKU不能为空");
        }

        if(!request.isForEver()){
            if(request.getStartTime()==null||request.getEndTime()==null){
                throw new BusinessException("开始时间和结束时间不能为空");
            }
        }

        if(request.getShopId()==null){
            Long shopId=QF.select(QShop.shop.id).from(QShop.shop).fetchFirst();
            request.setShopId(shopId);
        }

        if(!request.getShopId().equals(getProductShopId(request.getProductId()))){
            throw new BusinessException("只能设置同店商品");
        }


        Gifts gifts=request.getId()==null?null:giftsDao.getOne(request.getId());


        if(gifts==null){
            if(giftsDao.findByProductId(request.getProductId())!=null){
                throw new BusinessException("该商品已经设置了赠品，不能重复设置");
            }
            gifts=new Gifts();
            gifts.setCreateTime(new Date());
        }

        BeanUtils.copyProperties(request,gifts);

        giftsDao.save(gifts);

        saveGiftsProductSkuList(gifts,request.getSkus());

        return gifts;
    }

    private Long getProductShopId(Long productId){
        Long shopId=QF.select(QProduct.product.shopId).from(QProduct.product).where(QProduct.product.id.eq(productId)).fetchOne();
        return shopId;
    }


    private void saveGiftsProductSkuList(Gifts gifts, List<GiftsProductSkuDTO> data){
        List<GiftsProductSku> skus=new ArrayList<>(data.size());
        for (GiftsProductSkuDTO dto:data){
            if(dto.getSkuId()==null){
                throw new BusinessException("赠品SKU不能为空");
            }
            if(dto.getQuantity()<1){
                throw new BusinessException("赠品数量不能少于1");
            }

            GiftsProductSku sku=giftsProductSkuDao.findByGiftsIdAndSkuId(gifts.getId(),dto.getSkuId());
            if(sku==null){
                sku=new GiftsProductSku();
                sku.setGiftsId(gifts.getId());
                sku.setCreateTime(new Date());
                ProductSku productSku=productSkuDao.getOne(dto.getSkuId());
                if(!gifts.getShopId().equals(getProductShopId(productSku.getProduct().getId()))){
                    throw new BusinessException("只能设置同店商品");
                }
                sku.setProductId(productSku.getProduct().getId());
            }
            BeanUtils.copyProperties(dto,sku);

            skus.add(sku);

        }

        giftsProductSkuDao.saveAll(skus);
        giftsProductSkuDao.deleteAllByGiftsIdAndIdNotIn(gifts.getId(),skus.stream().map(t->t.getId()).collect(Collectors.toList()));


    }

    @Transactional
    @Override
    public Gifts setGiftsStatus(SetGiftsStatusDTO request) {
        Gifts gifts=giftsDao.getOne(request.getId());
        BeanUtils.copyProperties(request,gifts);

        giftsDao.save(gifts);

        return gifts;
    }



    @SetMiniProduct
    @Override
    public GiftsVO getGiftsVOById(Long id) {
        QGifts e=QGifts.gifts;
        GiftsVO vo=queryOne(e,e.id.eq(id),giftsVOConver);
        if(vo!=null) {
            vo.setSkus(giftsProductSkuDomain.getGiftsProductSkuVOListByGiftsId(id));
        }
        return vo;
    }

    @Override
    public GiftsVO getGiftsVOByProductId(Long productId) {
        QGifts e=QGifts.gifts;
        GiftsVO vo=queryOne(e,e.productId.eq(productId),giftsVOConver);
        if(vo!=null) {
            vo.setSkus(giftsProductSkuDomain.getGiftsProductSkuVOListByGiftsId(vo.getId()));
        }
        return vo;
    }

    @SetMiniProduct
    @Override
    public Page<GiftsVO> getGiftsVOPage(GetGiftsListDTO request, Pageable pageable) {
        QGifts e=QGifts.gifts;
        Predicate p=null;
        if(request.getKeyword()!=null){
            p=e.title.like("%"+request.getKeyword()+"%");
        }
        if(request.getStatus()!=null){
            p=e.status.eq(request.getStatus()).and(p);
        }

        Page<GiftsVO> page=queryPage(e,p,giftsVOConver,pageable);

        return page;
    }




}
