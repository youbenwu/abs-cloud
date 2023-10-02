package com.outmao.ebs.mall.store.domain;

import com.outmao.ebs.mall.store.vo.StoreStatsVO;

import java.util.Collection;
import java.util.List;

public interface StoreStatsDomain {

    public StoreStatsVO getStoreStatsVOByStoreId(Long storeId);

    public List<StoreStatsVO> getStoreStatsVOListByStoreIdIn(Collection<Long> storeIdIn);


}
