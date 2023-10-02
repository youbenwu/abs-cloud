package com.outmao.ebs.mall.promotion.service.impl;

import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.mall.promotion.domain.GiftsDomain;
import com.outmao.ebs.mall.promotion.dto.GetGiftsListDTO;
import com.outmao.ebs.mall.promotion.dto.GiftsDTO;
import com.outmao.ebs.mall.promotion.dto.SetGiftsStatusDTO;
import com.outmao.ebs.mall.promotion.entity.Gifts;
import com.outmao.ebs.mall.promotion.service.GiftsService;
import com.outmao.ebs.mall.promotion.vo.GiftsProductSkuVO;
import com.outmao.ebs.mall.promotion.vo.GiftsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GiftsServiceImpl extends BaseService implements GiftsService {

    @Autowired
    private GiftsDomain giftsDomain;

    @Override
    public Gifts saveGifts(GiftsDTO request) {
        return giftsDomain.saveGifts(request);
    }

    @Override
    public Gifts setGiftsStatus(SetGiftsStatusDTO request) {
        return giftsDomain.setGiftsStatus(request);
    }

    @Override
    public GiftsVO getGiftsVOById(Long id) {
        return giftsDomain.getGiftsVOById(id);
    }

    @Override
    public Page<GiftsVO> getGiftsVOPage(GetGiftsListDTO request, Pageable pageable) {
        return giftsDomain.getGiftsVOPage(request,pageable);
    }



}
