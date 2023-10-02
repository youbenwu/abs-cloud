package com.outmao.ebs.mall.promotion.domain;

import com.outmao.ebs.mall.promotion.vo.GiftsProductSkuVO;

import java.util.List;

public interface GiftsProductSkuDomain {

    public List<GiftsProductSkuVO> getGiftsProductSkuVOListByGiftsId(Long giftsId);
}
