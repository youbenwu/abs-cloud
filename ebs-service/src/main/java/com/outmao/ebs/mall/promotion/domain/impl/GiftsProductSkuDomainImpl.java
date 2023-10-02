package com.outmao.ebs.mall.promotion.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.mall.product.common.annotation.SetMiniProductSku;
import com.outmao.ebs.mall.promotion.domain.GiftsProductSkuDomain;
import com.outmao.ebs.mall.promotion.domain.conver.GiftsProductSkuVOConver;
import com.outmao.ebs.mall.promotion.entity.QGiftsProductSku;
import com.outmao.ebs.mall.promotion.vo.GiftsProductSkuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class GiftsProductSkuDomainImpl extends BaseDomain implements GiftsProductSkuDomain {

    @Autowired
    private GiftsProductSkuVOConver giftsProductSkuVOConver;

    @SetMiniProductSku
    @Override
    public List<GiftsProductSkuVO> getGiftsProductSkuVOListByGiftsId(Long giftsId) {
        QGiftsProductSku e=QGiftsProductSku.giftsProductSku;
        return queryList(e,e.giftsId.eq(giftsId),giftsProductSkuVOConver);
    }

}
