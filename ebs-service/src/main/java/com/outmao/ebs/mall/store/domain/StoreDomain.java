package com.outmao.ebs.mall.store.domain;

import com.outmao.ebs.mall.store.dto.GetStoreListDTO;
import com.outmao.ebs.mall.store.dto.SetStoreStatusDTO;
import com.outmao.ebs.mall.store.dto.StoreDTO;
import com.outmao.ebs.mall.store.entity.Store;
import com.outmao.ebs.mall.store.vo.SimpleStoreVO;
import com.outmao.ebs.mall.store.vo.StoreVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Collection;
import java.util.List;

public interface StoreDomain {

    public Store saveStore(StoreDTO request);

    public Store setStoreStatus(SetStoreStatusDTO request);

    public Store getStoreById(Long id);

    public StoreVO getStoreVOById(Long id);

    public Page<StoreVO> getStoreVOPage(GetStoreListDTO request, Pageable pageable);

    public List<SimpleStoreVO> getSimpleStoreVOListByIdIn(Collection<Long> idIn);


}
