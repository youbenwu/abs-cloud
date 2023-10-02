package com.outmao.ebs.mall.promotion.service;


import com.outmao.ebs.mall.promotion.dto.GetGiftsListDTO;
import com.outmao.ebs.mall.promotion.dto.GiftsDTO;
import com.outmao.ebs.mall.promotion.dto.SetGiftsStatusDTO;
import com.outmao.ebs.mall.promotion.entity.Gifts;
import com.outmao.ebs.mall.promotion.vo.GiftsVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface GiftsService {

    public Gifts saveGifts(GiftsDTO request);

    public Gifts setGiftsStatus(SetGiftsStatusDTO request);


    public GiftsVO getGiftsVOById(Long id);

    public Page<GiftsVO> getGiftsVOPage(GetGiftsListDTO request, Pageable pageable);



}
